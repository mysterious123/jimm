/*
 * Copyright (C) Unpublished JiuDaoTech Software, Inc. All rights reserved.
 * JiuDaoTech Software, Inc., Confidential and Proprietary.
 *
 * This software is subject to copyright protection
 *   under the laws of the Public of China and other countries.
 *
 * Unless otherwise explicitly stated, this software is provided
 *   by JiuDaoTech "AS IS".
 */

package core.plugin.redis;

import common.exception.app.AppRuntimeException;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Redis 客户端建造
 *
 * @author CSJ
 */
public class RedisClientBuilder {
    private static final Logger L = LoggerFactory.getLogger(RedisClientBuilder.class);
    private static final String REDIS_CLIENT_CONF = "REDIS_CLIENT_CONF";
    private static Config config;
    private static RedissonClient client;

    static {
        try {
            // 读取redis客户端配置
            URI cfgPath = RedisClientBuilder.class.getResource("/redis.yaml").toURI();
            // 设置默认配置
            setConfig(Config.fromYAML(new File(cfgPath)));

            // 启动daemon线程监控redis客户端配置文件更新
            final WatchService listener = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(new File(cfgPath).getParent());
            path.register(listener, StandardWatchEventKinds.ENTRY_MODIFY);

            Thread updateConfThread = new Thread(() -> {
                try {
                    while (true) {
                        WatchKey watchKey = listener.take();
                        List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
                        for (WatchEvent<?> event : watchEvents) {
                            L.info("[{}]文件发生了[{}]事件", event.context(), event.kind());
                            if ("redis.yaml".equals(event.context().toString())) {
                                Config cfg = Config.fromYAML(new File(cfgPath));
                                setConfig(cfg);
                                if (client != null && !client.isShutdown()) {
                                    client.shutdown();
                                    client = Redisson.create(getConfig());
                                    L.info("Redis连接配置文件已重新加载, 创建连接池成功!");
                                }
                            }
                        }
                        watchKey.reset();
                        TimeUnit.SECONDS.sleep(1);
                    }
                } catch (InterruptedException | IOException e) {
                    throw new AppRuntimeException("Redis config scan error", e);
                } finally {
                    try {
                        if (listener != null) listener.close();
                    } catch (IOException e) {
                        L.error(e.getMessage(), e);
                    }
                }

            });
            updateConfThread.setName("updateRedisClientConfigThread");
            updateConfThread.setDaemon(true);
            updateConfThread.start();

            // 启动默认连接池
            client = Redisson.create(getConfig());
            L.info("Redis连接配置文件已加载, 创建连接池成功!");
        } catch (URISyntaxException | IOException e) {
            throw new AppRuntimeException("Load redis config error", e);
        }
    }

    public static Config getConfig() {
        return config;
    }

    public static void setConfig(Config config) {
        RedisClientBuilder.config = config;
    }

    public RedissonClient build() {
        return client;
    }

}

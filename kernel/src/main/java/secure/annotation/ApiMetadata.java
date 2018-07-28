package secure.annotation;

/**
 * rest api 元数据
 *
 * @author yx
 * @since 2017/12/12 10:06
 */

import java.lang.annotation.*;

/**
 * 可以用在方法以及类上面
 * desc: 类或者方法功能的描述
 * permission: 访问该方法所需的权限
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiMetadata {
    String desc();

    String permission();
}

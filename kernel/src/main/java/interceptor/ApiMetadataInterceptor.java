package interceptor;

import common.exception.http.UnauthorizedException;
import model.superuser.SuperUser;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import secure.annotation.ApiMetadata;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ApiMetadataInterceptor extends HandlerInterceptorAdapter {
    private static final Logger L = LoggerFactory.getLogger(ApiMetadataInterceptor.class);

    private static final int SESSION_INVALID_CODE = 999;

    //不需要教研是否登陆的url
    public List<String> anonUrl = new ArrayList<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SuperUser currentUser = (SuperUser)SecurityUtils.getSubject().getPrincipal();
        L.debug("发起请求: {}", request.getRequestURI());
        if (handler instanceof HandlerMethod) {
            if (!checkAnonUrl(request.getServletPath()) && !SecurityUtils.getSubject().isAuthenticated()) {
                response.setStatus(SESSION_INVALID_CODE);
                return false;
            }else {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                ApiMetadata apiMetadata = handlerMethod.getMethodAnnotation(ApiMetadata.class);
                if(apiMetadata != null){
                    ApiMetadata clazzMetadata = handlerMethod.getBeanType().getAnnotation(ApiMetadata.class);
                    String permission = apiMetadata.permission();
                    if(clazzMetadata != null){
                        permission = clazzMetadata.permission() + "/" + apiMetadata.permission() ;
                    }
                    if(!SecurityUtils.getSubject().isPermitted(permission)){
                        L.warn("当前用户{}没有{}访问权限", currentUser.getUserName(), apiMetadata.desc());
                        throw new UnauthorizedException("当前用户" + currentUser.getUserName() + "没有" + apiMetadata.permission() + "访问权限");

                    }
                }
            }
        }
        return super.preHandle(request, response, handler);
    }

    private boolean checkAnonUrl(String targetUrl){
        return anonUrl.contains(targetUrl);
    }

    public List<String> getAnonUrl() {
        return anonUrl;
    }

    public void setAnonUrl(List<String> anonUrl) {
        this.anonUrl = anonUrl;
    }
}

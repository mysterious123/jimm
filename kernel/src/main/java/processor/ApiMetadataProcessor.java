package processor;

import model.superpermission.SuperPermission;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ClassUtils;
import secure.annotation.ApiMetadata;
import service.superpermission.SuperPermissionService;
import javax.annotation.Resource;
import java.lang.reflect.Method;

public class ApiMetadataProcessor implements BeanPostProcessor {

    private static Boolean DELETE_FLAG = false;

    @Resource
    private SuperPermissionService superPermissionService;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //此时有可能会是代理类所以需要取得真正的原始类
        Class<?> originClazz = ClassUtils.getUserClass(bean.getClass());

        ApiMetadata classMetadata = originClazz.getAnnotation(ApiMetadata.class);
        ApiMetadata methodMetadata;
        SuperPermission superPermission = new SuperPermission();
        String parentId;
        if(!DELETE_FLAG){
            superPermission.setType("1");
            superPermissionService.delete(superPermission);
            DELETE_FLAG = true;
        }
        if(classMetadata != null){
            superPermission = new SuperPermission();
            superPermission.setDescription(classMetadata.desc());
            superPermission.setPermission(classMetadata.permission());
            superPermission.setType("1");
            superPermissionService.insert(superPermission);
            parentId = superPermission.getId();
            for(Method method : originClazz.getDeclaredMethods()){
                methodMetadata = method.getAnnotation(ApiMetadata.class);
                if(methodMetadata != null){
                    superPermission = new SuperPermission();
                    superPermission.setDescription(methodMetadata.desc());
                    superPermission.setPermission(classMetadata.permission()+ "/" + methodMetadata.permission());
                    superPermission.setType("1");
                    superPermission.setParent(parentId);
                    superPermissionService.insert(superPermission);
                }
            }
        }
        return bean;
    }
}

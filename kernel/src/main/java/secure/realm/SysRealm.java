package secure.realm;

import model.superuser.SuperUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import secure.authc.AccountLoginToken;
import service.superuser.SuperUserService;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

public class SysRealm extends AuthorizingRealm {

    @Resource
    SuperUserService superUserService;

    @Value("${shiro.applicationSalt}")
    private String applicationSalt;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        SuperUser currentUser = (SuperUser) principals.fromRealm(getName()).iterator().next();

        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();

        currentUser.getSuperRoles().forEach(superRole -> roles.add(superRole.getId()));
        currentUser.getSuperPermissions().forEach(superPermission -> permissions.add(superPermission.getPermission()));
        info.setRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        SimpleAuthenticationInfo info;
        try {
            AccountLoginToken authcToken = (AccountLoginToken) token;
            SuperUser sysUser = authcToken.getUser();

            SuperUser param  = new SuperUser();
            param.setUserName(sysUser.getUserName());
            param = superUserService.selectOneInfo(param);

            if (param == null) {
                throw new UnknownAccountException("未发现用户 [" + authcToken.getUsername() + "]");
            }
            info = new SimpleAuthenticationInfo(param, param.getPassword().toCharArray(), getName());
            param.setPassword(null);
            info.setCredentialsSalt(ByteSource.Util.bytes(applicationSalt + ":" + param.getUserName()));
        } catch (Exception e) {
            throw new AuthenticationException(e.getMessage(), e);
        }
        return info;
    }
}

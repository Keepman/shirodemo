package ymt.zyj.shirodemo.Realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import ymt.zyj.shirodemo.entity.Role;
import ymt.zyj.shirodemo.service.ShiroService;

import java.util.HashSet;
import java.util.Set;


/**
 * @Author mayn
 * @Date 2019/10/29 9:01
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private ShiroService shiroService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Role primaryPrincipal = (Role) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> stringSet = new HashSet<>();
        stringSet.addAll(primaryPrincipal.getPermissionList());
        info.setStringPermissions(stringSet);
        return info;
    }

    /**
     * 这里可以注入userService,为了方便演示，我就写死了帐号了密码
     * private UserService userService;
     * <p>
     * 获取即将需要认证的信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------身份认证方法--------");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = (String) authenticationToken.getPrincipal();
        String userPwd = new String((char[]) authenticationToken.getCredentials());
        Role role = null;
        //根据用户名从数据库获取密码
        try {
            role = shiroService.login(userName, userPwd);
        } catch (NullPointerException e) {
            throw new AccountException("用户名或者密码不正确");
        }
        return new SimpleAuthenticationInfo(role, role.getPassword(), getName());
    }
}

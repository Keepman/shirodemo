package ymt.zyj.shirodemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

/**
 * @Author mayn
 * @Date 2019/10/29 9:02
 */
@Slf4j
@RestController
public class ShiroController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String defaultLogin() {
        return "首页";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,@RequestParam("rememberMe") boolean rememberMe) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
//        try {
            token.setRememberMe(rememberMe);
            subject.login(token);
//        } catch (UnknownAccountException uae) {
//            return "未知账户";
//        } catch (IncorrectCredentialsException ice) {
//            return "密码不正确";
//        } catch (LockedAccountException lae) {
//            return "账户已锁定";
//        } catch (ExcessiveAttemptsException eae) {
//            return "用户名或密码错误次数过多";
//        } catch (AuthenticationException ae) {
//            return "用户名或密码不正确！";
//        }
        if (subject.isAuthenticated()) {
            return "登录成功";
        } else {
            token.clear();
            return "登录失败";
        }
    }

    // 需要有a:show 权利的用户才可以执行该方法
    @RequiresPermissions("select")
    @RequestMapping("/show")
    public String showUser() {
        return "这是学生信息";
    }
}

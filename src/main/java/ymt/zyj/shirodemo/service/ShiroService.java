package ymt.zyj.shirodemo.service;

import ymt.zyj.shirodemo.entity.Role;

/**
 * @Author mayn
 * @Date 2019/10/30 9:53
 */
public interface ShiroService {
    Role login(String username,String password);

}

package ymt.zyj.shirodemo.service.Impl;

import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ymt.zyj.shirodemo.dao.ShiroDao;
import ymt.zyj.shirodemo.entity.Role;
import ymt.zyj.shirodemo.service.ShiroService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author mayn
 * @Date 2019/10/30 9:53
 */
@Service
public class ShiroServiceimpl implements ShiroService {
    @Autowired
    private ShiroDao shiroDao;
    @Override
    public Role login(String username,String password) throws NullPointerException {
        Role role = shiroDao.login(username, password);
        String[] split = role.getPermission().split(",");
        List<String> permissionList = new ArrayList<>();
        for (String s : split) {
            permissionList.add(s);
        }
        role.setPermissionList(permissionList);
        return role;
    }
}

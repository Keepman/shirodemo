package ymt.zyj.shirodemo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ymt.zyj.shirodemo.entity.Role;

/**
 * @Author mayn
 * @Date 2019/10/30 9:55
 */
@Mapper
public interface ShiroDao {

    Role login(@Param("username") String username, @Param("password") String password);
}

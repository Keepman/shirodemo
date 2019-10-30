package ymt.zyj.shirodemo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author mayn
 * @Date 2019/10/30 9:32
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 9174561496694034804L;
    private String username;
    private String permission;
    private String password;
    private List<String> permissionList;
}

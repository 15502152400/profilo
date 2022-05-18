package per.tom.clientview.pojo;


import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class UserPo implements Serializable {

    private Integer userId;
    private String userTel;
    private Integer userStatus;
    private String userName;
    private String userHeaderPath;
    private Integer userGender;
    private String userSalt ;
    private String userPassword;
    private Integer roleId;
    private Timestamp createTime;
    private Timestamp updateTime;

}

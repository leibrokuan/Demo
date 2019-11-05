package dao;

import pojo.User;

import java.util.List;

public interface UserDao {
    //根据用户名和密码查询用户信息
    User checkuserLoginDao(String uname,String pwd);

    int userChangePwdDao(String newPwd, int uid);

    List<User> userShowDao();

    int userRegDao(User u);
}

package service;

import pojo.User;

import java.util.List;

public interface UserService {
    //校验用户登录
    User checkUserLoginService(String uname,String pwd);

    int userChangePwdService(String newPwd, int uid);

    List<User> userShowService();

    int userRegService(User u);
}

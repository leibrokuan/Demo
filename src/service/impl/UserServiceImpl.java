package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import org.apache.log4j.Logger;
import pojo.User;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    //声明Dao层对象
    UserDao ud = new UserDaoImpl();
    //声明日志对象：
    Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public User checkUserLoginService(String uname, String pwd) {
        //打印日志
        logger.debug(uname+"发起了登录请求");
        User u = ud.checkuserLoginDao(uname,pwd);
        if (u!=null){
            logger.debug(uname+"登录成功");
        }else{
            logger.debug(uname+"登录失败");
        }
        return   u;

    }

    @Override
    public int userChangePwdService(String newPwd, int uid) {
        logger.debug(uid+":发起修改密码请求");
        int index = ud.userChangePwdDao(newPwd,uid);
        if(index>0){
            logger.debug(uid+":修改密码成功");
        }else {
            logger.debug(uid+":修改密码失败");
        }
        return index;
    }

    @Override
    public List<User> userShowService() {
        List<User> lu = ud.userShowDao();
        logger.debug("显示所有用户信息:"+lu);
        return ud.userShowDao();
    }

    @Override//用户注册
    public int userRegService(User u) {
        int index = ud.userRegDao(u);
        if(index!=-1)
            logger.debug(u.getUname()+"注册成功");
        else
            logger.debug(u.getUname()+"注册失败");
        return index;
    }
}

package dao.impl;

import dao.UserDao;
import pojo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    //根据用户名密码查询用户信息
    @Override
    public User checkuserLoginDao(String uname, String pwd) {
        //声明jdbc对象
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //声明变量
        User u = null;
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://www.leikuan.fun:3306/test","root","19990908");
            //创建sql命令
            String sql = "select * from t_user2 where uname=? and pwd=?";
            //创建SQL命令对象
            ps = conn.prepareStatement(sql);
            //给占位符赋值
            ps.setString(1,uname);
            ps.setString(2,pwd);
            //执行sql
            rs = ps.executeQuery();
            //遍历结果
            while (rs.next()){
                u = new User();
                u.setUid(rs.getInt("uid"));
                u.setUname(rs.getString("uname"));
                u.setPwd(rs.getString("pwd"));
                u.setSex(rs.getString("sex"));
                u.setAge(rs.getInt("age"));
                u.setBirth(rs.getDate("birth"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //关闭资源
         return u;
    }

    @Override
    public int userChangePwdDao(String newPwd, int uid) {
        int index = -1;
        //声明jdbc对象
        Connection conn = null;
        PreparedStatement ps = null;
        //声明变量
        User u = null;
       try {


            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://www.leikuan.fun:3306/test","root","19990908");
            //创建sql命令
            String sql = "update t_user2 set pwd=?where uid =?";
            //创建SQL命令对象
            ps = conn.prepareStatement(sql);
            //给占位符赋值
            ps.setString(1,newPwd);
            ps.setInt(2,uid);
            //执行sql
            index = ps.executeUpdate();
       }catch (Exception e){
            e.printStackTrace();
       }finally {
           try {
               ps.close();
               conn.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }

        return index;
    }

    @Override
    public List<User> userShowDao() {
        //声明jdbc对象
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //声明变量
        List<User> lu = null;
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://www.leikuan.fun:3306/test","root","19990908");
            //创建sql命令
            String sql = "select * from t_user2 ";
            //创建SQL命令对象
            ps = conn.prepareStatement(sql);
            //执行sql
            rs = ps.executeQuery();
            //遍历结果
            lu = new ArrayList<User>();
            while (rs.next()){
                User u = new User();
                u.setUid(rs.getInt("uid"));
                u.setUname(rs.getString("uname"));
                u.setPwd(rs.getString("pwd"));
                u.setSex(rs.getString("sex"));
                u.setAge(rs.getInt("age"));
                u.setBirth(rs.getDate("birth"));
                lu.add(u);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //关闭资源
        return lu;
    }
     //用户注册
    @Override
    public int userRegDao(User u) {
        int index = -1;
        //声明jdbc对象
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //声明变量
        List<User> lu = null;
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://www.leikuan.fun:3306/test","root","19990908");
            //创建sql命令
            String sql = "insert into t_user2 values(default,?,?,?,?,?)";
            //创建SQL命令对象
            ps = conn.prepareStatement(sql);
            //给占位符赋值
            ps.setString(1,u.getUname());
            ps.setString(2,u.getPwd());
            ps.setString(3,u.getSex());
            ps.setInt(4,u.getAge());
            ps.setDate(5,u.getBirth());
            //执行sql
            index = ps.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
        }finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
   return index;
    }
}

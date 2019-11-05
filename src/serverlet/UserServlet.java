package serverlet;

import com.sun.net.httpserver.HttpsServer;
import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class UserServlet extends HttpServlet {
    //获取service层对象
    UserService us = new UserServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码格式
        req.setCharacterEncoding("utf-8");
        //设置相应编码格式
        resp.setContentType("text/html;charset=utf-8");
        //获取操作符
        String oper = req.getParameter("oper");

        if("login".equals(oper)){
            //调用登录处理方法
            checkUserLogin(req,resp);
        }else if("pwd".equals(oper)){
            userChangePwd(req,resp);
        }
        else if ("reg".equals(oper)){
            //调用注册功能
            userReg(req,resp);
        }else if("out".equals(oper)){
            userOut(req,resp);
            
        } else if ("show".equals(oper)){
               //显示所有用户信息
            userShow(req,resp);
        }else{
            System.out.println("未找到操作符");
        }



    }

    private void userReg(HttpServletRequest req, HttpServletResponse resp)  {
        //获取请求信息
        //设置请求编码格式
        try {
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //设置相应编码格式
        resp.setContentType("text/html;charset=utf-8");
        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        String ssex="";
        int sex = (Integer)Integer.parseInt(req.getParameter("sex"));
        if(sex==1){
           ssex ="男";
        }else{
             ssex="女";
        }
        int age;
        if (req.getParameter("age").equals("")){
            age = 0;
        }else {
         age = Integer.parseInt(req.getParameter("age"));
        }
        String birth = req.getParameter("birth");
        System.out.println(birth);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date d = null;
        try {
            d = new Date((format.parse(birth)).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        User u = new User(0,uname,pwd,ssex,age,d);
        System.out.println(u);
        //处理请求信息
           //调用业务层处理
              int index = us.userRegService(u);
              if(index!=-1){
                  //获取session
                  HttpSession hs = req.getSession();

                  hs.setAttribute("flag",2);
                  //重定向
                  try {
                      resp.sendRedirect("/mg/login.jsp");
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
    }

    private void userShow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理请求
        //调用service

        List<User> lu = us.userShowService();
        //判断
        if(lu!=null){
            //将查询的数据存储到request对象
            req.setAttribute("lu",lu);
            //请求转发
             req.getRequestDispatcher("/user/showUser.jsp").forward(req,resp);
        }
    }

    private void userChangePwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取数据
        String newPwd = req.getParameter("newPwd");
       //从session获取用户信息
        User u = (User)req.getSession().getAttribute("user");
        int uid = u.getUid();
        //处理请求
            //调用service处理
            int index = us.userChangePwdService(newPwd,uid);
            if(index>0){
                //获取session对象
                HttpSession hs = req.getSession();
                hs.setAttribute("flag",1);
                //重定向到登录页面
                resp.sendRedirect("/mg/login.jsp");
            }
    }

    private void userOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取session对象
        HttpSession hs = req.getSession();
        //销毁session
        hs.invalidate();
        //重定向到登录页面
        resp.sendRedirect("/mg/login.jsp");
    }

    //处理登录
    private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //获取请求信息
        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        System.out.println(uname+":"+pwd);
        //处理请求信息

                //校验
                User u = us.checkUserLoginService(uname,pwd);
                if (u!=null){
                    //获取session对象
                    HttpSession hs = req.getSession();
                    //将用户数据存储到session当中
                    hs.setAttribute("user",u);
                    resp.sendRedirect("/mg/main/main.jsp");
                }else {
                    //添加标识符到request中
                    req.setAttribute("flag",0);

                    //请求转发 ("/资源路径")从项目根目录开始找
                    req.getRequestDispatcher("/login.jsp").forward(req,resp);

                    return;
                }
        //相应处理结果
        //直接响应
        //请求转发
        //重定向
    }


}

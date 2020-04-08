package com.item.controller;

import com.alibaba.fastjson.JSON;
import com.item.bean.Chat;
import com.item.bean.User;
import com.item.configure.PA;
import com.item.feign.ChatController;
import com.item.feign.UserController;
import com.item.tool.CookieConfig;
import com.item.tool.ServerUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequestMapping("user")
@Controller
public class UserControl {
    CookieConfig cookieConfig=new CookieConfig<User>();
    @Autowired
    UserController userController;

    @Autowired
    ChatController chatController;

    /**
     * version 2.0
     * 初始化最后一次聊天
     * @param user
     * @param session
     */
    public void initLastChat(User user,HttpSession session){
        String acceptAccount=chatController.getLastChatAcceptId(user);
        List<Chat> chats=chatController.getLastChatContent(user.getAccount(),acceptAccount);
        session.setAttribute("acceptAccount",acceptAccount);
        session.setAttribute("lastchat",chats);
    }
    /**
     * version 2.0
     * @param user
     * @param request
     * @param modelAndView
     * @return
     */
    @PostMapping("login")
    public ModelAndView userLogin(User user, HttpServletRequest request, ModelAndView modelAndView){
        // default view Name set as <login>
        modelAndView.setViewName("/login");
        if (PA.AS.get(user.getAccount())!=null){
            modelAndView.addObject("statusmsg","already exist");
            return modelAndView;
        }
        Map<String,String> map=userController.userLogin(user);
        //status code =404表示没有这个用户
        if (map.get("status").equals("404")){
            modelAndView.addObject("statusmsg","username or password error");
        }
        User us=JSON.parseObject(map.get("data").toString(),User.class);
        modelAndView.setViewName("/homePage");
        try {
            HttpSession session=request.getSession();
            session.setAttribute("user",us);
            PA.AS.put(us.getAccount(),session);
            initLastChat(us,session);
        } catch (Exception e) {
        }
        return modelAndView;
    }

    /**version 1.0
     * 登录前先和服务器进行握手 得到服务器的token 保存前后端session一直
     * @param user
     * @param request
     * @return
     */
//    @PostMapping("login")
//    public String userLogin(User user, HttpServletRequest request){
//        System.out.println("进入握手");
//        HttpEntity entity = null;
//        try {
//            request.getSession().setAttribute("user",user);
//            entity = cookieConfig.get_httpHeader(request,null);
//            template.postForEntity(ServerUrl.URL+"/system/handshake",entity,String.class);
//
//        } catch (Exception e) {
//        }
//        return "redirect:/user/Logins";
//    }


    /**
     * version 1.0
     * @param request
     * @param model
     * @return
     */
//    @RequestMapping("Logins")
//    public String userLogins(HttpServletRequest request, Model model){
//        User user=(User) request.getSession().getAttribute("user");
//        HttpEntity entity = null;
//        try {
//            Map<String,Object> map=new HashMap<>();
//            System.out.println(JSON.toJSONString(user));
//            map.put("user",JSON.toJSONString(user));
//            entity = cookieConfig.get_httpHeader(request,map);
//            HttpEntity response= template.postForEntity(ServerUrl.URL+"/user/login",entity,String.class);
//            Object object= response.getBody();
//            System.out.println(object);
//            Map<String,Object> result=JSON.parseObject(object.toString(),Map.class);
//            String i=result.get("status").toString();
//            System.out.println(i);
//            if (i.equals("already")){
//                model.addAttribute("hint","该用户已在别处登录");
//                return "redirect:/login";
//            }else if (i.equals("error account or password error")){
//                model.addAttribute("hint","账号或密码错误");
//                return "redirect:/login";
//            }
////            model.addAttribute("acceptAccount",result.get("acceptAccount").toString());
////            request.getSession().setAttribute("acceptAccount",result.get("acceptAccount").toString());
//        } catch (IllegalStateException e) {
//            System.out.println("request timeout");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return "redirect:/homePage";
//    }

    /**
     * 用户注册
     * @return
     */
    @PostMapping("signUp")
    public String signUp(){
        return "index";
    }


}

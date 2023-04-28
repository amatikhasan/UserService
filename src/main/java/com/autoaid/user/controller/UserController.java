package com.autoaid.user.controller;

import com.autoaid.user.model.UserInfo;
import com.autoaid.user.service.UserService;
import com.netflix.discovery.DiscoveryClient;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    private String getAllUser(Model model) {
        List<UserInfo> userInfos = userService.getAllUser();
        model.addAttribute("users", userInfos);

        return "user-details";
    }
    @GetMapping("/register")
    private String showRegistrationPage(Model model) {
        model.addAttribute("userInfo", new UserInfo());
        return "user-registration";
    }

    @PostMapping("/register")
    private String registerUser(@ModelAttribute("userInfo") UserInfo userInfo) {
        UserInfo registeredUser = userService.createUser(userInfo);
        if (registeredUser == null) {
            return "user-login";
        }else {
            return "dashboard";
        }
    }

    @GetMapping("/login")
    private String showLoginPage(Model model) {
        model.addAttribute("userInfo", new UserInfo());
        return "user-login";
    }

    @PostMapping("/login")
    private String loginUser(@ModelAttribute("userInfo") UserInfo userInfo, HttpServletRequest request, HttpServletResponse response) {
        UserInfo loggedInUser = userService.loginUser(userInfo);
        if (loggedInUser == null) {
            return "redirect:/user/login";
        }else {
            // authenticate the user and set session attributes
            HttpSession session = request.getSession();
            session.setAttribute("user", loggedInUser);

            return "redirect:http://localhost:8081/breakdown?userId=" + loggedInUser.getId();
        }
    }

    @GetMapping("/get/{id}")
    private UserInfo getUser(@PathVariable Long id) {
        return userService.getUser(id).get();
    }

    @PutMapping("/update")
    private UserInfo updateUser(UserInfo UserInfo) {
        return userService.updateUser(UserInfo);
    }

    @DeleteMapping("/delete/{id}")
    private void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}

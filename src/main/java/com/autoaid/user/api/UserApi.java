package com.autoaid.user.api;

import com.autoaid.user.model.UserInfo;
import com.autoaid.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userapi")
public class UserApi {

    private UserService userService;

    @Autowired
    public UserApi(UserService UserService) {
        this.userService = UserService;
    }

    @GetMapping("/all")
    private List<UserInfo> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("/create")
    private UserInfo createUser(UserInfo UserInfo) {
        return userService.createUser(UserInfo);
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

    @DeleteMapping("/delete/all")
    private void deleteAllUser() {
        userService.deleteAllUser();
    }

}

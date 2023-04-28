package com.autoaid.user.service;

import com.autoaid.user.model.UserInfo;
import com.autoaid.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository UserRepository) {
        this.userRepository = UserRepository;
    }

    public UserInfo createUser(UserInfo userInfo) {
        Optional<UserInfo> user = userRepository.findByEmailAndPassword(userInfo.getEmail(), userInfo.getPassword());
        if(user.isPresent()) {
            return null;
        }else {
            return userRepository.save(userInfo);
        }
    }

    public UserInfo loginUser(UserInfo userInfo) {
        Optional<UserInfo> user = userRepository.findByEmailAndPassword(userInfo.getEmail(), userInfo.getPassword());
        return user.orElse(null);
    }

    public Optional<UserInfo> getUser(Long id) {
        return userRepository.findById(id);
    }

    public List<UserInfo> getAllUser() {
        return userRepository.findAll();
    }

    public UserInfo updateUser(UserInfo UserInfo) {
        return userRepository.save(UserInfo);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteAllUser() {
        userRepository.deleteAll();
    }

}

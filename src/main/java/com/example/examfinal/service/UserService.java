package com.example.examfinal.service;

import com.example.examfinal.model.service.UserRegistrationServiceModel;
import com.example.examfinal.model.service.UserUpdateServiceModel;
import com.example.examfinal.model.view.UserDetailsView;
import com.example.examfinal.model.view.UserViewModel;

import java.util.List;

public interface UserService {


    void initializeUsersAndRoles();

    void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel);

    boolean isUserNameFree(String username);

    boolean isOwner(String userName, Long id);

    void updateUser(UserUpdateServiceModel userModel);

    List<UserViewModel> getAllUsers();

    UserDetailsView findById(Long id);
    UserDetailsView findByName(String name);
}

package com.example.examfinal.service.impl;
import com.example.examfinal.model.entity.UserEntity;
import com.example.examfinal.model.entity.UserRoleEntity;
import com.example.examfinal.model.entity.enums.UserRoleEnum;
import com.example.examfinal.model.service.UserRegistrationServiceModel;
import com.example.examfinal.model.service.UserUpdateServiceModel;
import com.example.examfinal.model.view.UserDetailsView;
import com.example.examfinal.model.view.UserViewModel;
import com.example.examfinal.repository.UserRepository;
import com.example.examfinal.repository.UserRoleRepository;
import com.example.examfinal.service.UserService;
import com.example.examfinal.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ProjectUserServiceImpl projectUserService;
    private final ModelMapper modelMapper;

    public UserServiceImpl(PasswordEncoder passwordEncoder,
                           UserRepository userRepository,
                           UserRoleRepository userRoleRepository
            , ProjectUserServiceImpl projectUserService, ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.projectUserService = projectUserService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initializeUsersAndRoles() {
        initializeRoles();
        initializeUsers();
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {

            UserRoleEntity adminRole = userRoleRepository.findByRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);

            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin@"));
            admin.setFirstName("Admin");
            admin.setLastName("Adminov");
            admin.setActive(true);

            admin.setRoles(Set.of(adminRole, userRole));


            UserEntity noit = new UserEntity();
            noit.setUsername("noit");
            noit.setPassword(passwordEncoder.encode("noit2022"));
            noit.setFirstName("НОИТ");
            noit.setLastName("Комисия");
            noit.setActive(true);

            noit.setRoles(Set.of(userRole));

            UserEntity valia = new UserEntity();
            valia.setUsername("valia");
            valia.setPassword(passwordEncoder.encode("1234"));
            valia.setFirstName("Valia");
            valia.setLastName("Maximova");
            valia.setActive(true);

            valia.setRoles(Set.of(userRole));
            userRepository.saveAll(List.of(admin, noit, valia));
        }
    }

    public void initializeRoles() {

        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setRole(UserRoleEnum.ADMIN);

            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setRole(UserRoleEnum.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));
        }
    }

    @Override
    public void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel) {

        UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);

        UserEntity newUser = new UserEntity();

        newUser.setUsername(userRegistrationServiceModel.getUsername());
        newUser.setFirstName(userRegistrationServiceModel.getFirstName());
        newUser.setLastName(userRegistrationServiceModel.getLastName());
        newUser.setActive(true);
        newUser.setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword()));
        newUser.setRoles(Set.of(userRole));

        newUser = userRepository.save(newUser);

        // this is the Spring representation of a user
        UserDetails principal = projectUserService.loadUserByUsername(newUser.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.
                getContext().
                setAuthentication(authentication);
    }

    public boolean isUserNameFree(String username) {
        return userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }


    @Override
    public boolean isOwner(String userName, Long id) {
        Optional<UserEntity> userOpt = userRepository.
                findByUsername(userName);
        Optional<UserEntity> caller = userRepository.
                findByUsername(userName);

        if (userOpt.isEmpty() || caller.isEmpty()) {
            return false;
        } else {
            UserEntity user = userOpt.get();

            return isAdmin(caller.get()) ||
                    user.getUsername().equals(userName);
        }
    }

    private boolean isAdmin(UserEntity user) {
        return user.
                getRoles().
                stream().
                map(UserRoleEntity::getRole).
                anyMatch(r -> r == UserRoleEnum.ADMIN);
    }

    @Override
    public void updateUser(UserUpdateServiceModel userModel) {
        UserEntity user =
                userRepository.findById(userModel.getId()).orElseThrow(() ->
                        new ObjectNotFoundException("Offer with id " + userModel.getId()
                                + " not found!"));

        user.setUsername(userModel.getUsername());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setPassword(userModel.getPassword());


        userRepository.save(user);
    }

    @Override
    public List<UserViewModel> getAllUsers() {
        return userRepository.findAll().stream().map(this::map).collect(Collectors.toList());
    }


    @Transactional
    @Override
    public UserDetailsView findById(Long id) {
       UserDetailsView userDetailsView = this.userRepository.findById(id).map(this::userDetailsView).get();
        return userDetailsView;
    }

    @Override
    public UserDetailsView findByName(String name) {
        UserDetailsView userDetailsView = this.userRepository.findByUsername(name).map(this::userDetailsView).get();
        return userDetailsView;
    }


    private UserDetailsView userDetailsView(UserEntity user) {
        UserDetailsView userDetailsView = this.modelMapper.map(user, UserDetailsView.class);

        return userDetailsView;
    }

    private UserViewModel map(UserEntity user) {

        return this.modelMapper.map(user, UserViewModel.class);
    }

}

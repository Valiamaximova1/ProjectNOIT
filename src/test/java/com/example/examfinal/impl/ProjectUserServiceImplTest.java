package com.example.examfinal.impl;

import com.example.examfinal.model.entity.UserEntity;
import com.example.examfinal.model.entity.UserRoleEntity;
import com.example.examfinal.model.entity.enums.UserRoleEnum;
import com.example.examfinal.repository.UserRepository;
import com.example.examfinal.service.impl.ProjectUserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class ProjectUserServiceImplTest {

  private UserEntity testUser;
  private UserRoleEntity adminRole, userRole;

  private ProjectUserServiceImpl serviceToTest;

  @Mock
  private UserRepository mockUserRepository;

  @BeforeEach
  void init() {

    //ARRANGE
    serviceToTest = new ProjectUserServiceImpl(mockUserRepository);

    adminRole = new UserRoleEntity();
    adminRole.setRole(UserRoleEnum.ADMIN);

    userRole = new UserRoleEntity();
    userRole.setRole(UserRoleEnum.USER);

    testUser = new UserEntity();
    testUser.setUsername("valia");
    testUser.setFirstName("Valentina");
    testUser.setLastName("Maximova");
    testUser.setPassword("topsecret");
    testUser.setRoles(Set.of(adminRole, userRole));
  }

  @Test
  void testUserNotFound() {
    Assertions.assertThrows(
        UsernameNotFoundException.class,
        () -> serviceToTest.loadUserByUsername("invalid_user_email@not-exist.com")
    );
  }

  @Test
  void testUserFound() {

    Mockito.when(mockUserRepository.findByUsername(testUser.getUsername())).
        thenReturn(Optional.of(testUser));

    var actual = serviceToTest.loadUserByUsername(testUser.getUsername());


    String expectedRoles = "ROLE_ADMIN, ROLE_USER";
    String actualRoles = actual.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(
        Collectors.joining(", "));

    Assertions.assertEquals(actual.getUsername(), testUser.getUsername());
    Assertions.assertEquals(expectedRoles, actualRoles);
  }

}

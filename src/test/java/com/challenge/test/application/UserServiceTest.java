package com.challenge.test.application;

import com.challenge.test.domain.User;
import com.challenge.test.domain.UserException;
import com.challenge.test.domain.UserMother;
import com.challenge.test.domain.UserRepository;
import com.challenge.test.infrastructure.database.UserEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public final class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void init() {
        initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void getUsers() {
        //GIVEN
        User user = UserMother.random();
        UserEntity entity = UserEntity.getFromDomain(user);
        //WHEN
        given(userRepository.findAll()).willReturn(List.of(entity));
        // WHEN
        then(userService.getUsers().get(0)).isEqualTo(user);
    }

    @Test
    public void getUserById() {
        //GIVEN
        User user = UserMother.random();
        UserEntity entity = UserEntity.getFromDomain(user);
        //WHEN
        given(userRepository.findById(user.getId())).willReturn(Optional.of(entity));
        // WHEN
        then(userService.getUserById(user.getId())).isEqualTo(user);
    }

    @Test(expected = UserException.class)
    public void getUserByIdUserNotFound() {
        //GIVEN
        String userId = "123456";
        //WHEN
        given(userRepository.findById(userId)).willReturn(Optional.empty());
        // WHEN
        userService.getUserById(userId);
    }

}
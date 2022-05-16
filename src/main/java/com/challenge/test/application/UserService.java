package com.challenge.test.application;

import com.challenge.test.domain.User;
import com.challenge.test.domain.UserException;
import com.challenge.test.domain.UserRepository;
import com.challenge.test.infrastructure.database.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll().stream()
                .map(UserEntity::getUserDomain)
                .collect(Collectors.toList());
    }

    public User getUserById(String id) throws UserException {
        UserEntity userEntity = userRepository
                .findById(id)
                .orElseThrow(() -> new UserException(
                                "User with Id: " + id + " not found",
                                null,
                                HttpStatus.NOT_FOUND.value()
                        )
                );

        return userEntity.getUserDomain();
    }

    public void createUser(User user) throws UserException {

        if (userRepository.existsById(user.getId())) {
            throw new UserException(
                    "User with Id: " + user.getId() + " already exists",
                    null,
                    HttpStatus.BAD_REQUEST.value());
        }

        UserEntity entity = UserEntity.getFromDomain(user);
        userRepository.save(entity);
    }

    public void updateUser(User user) throws UserException {
        UserEntity userEntity = userRepository
                .findById(user.getId())
                .orElseThrow(() -> new UserException(
                                "User can't be updated, user with Id: " + user.getId() + " not found",
                                null,
                                HttpStatus.BAD_REQUEST.value()
                        )
                );

        userEntity.setName(user.getName());
        userEntity.setPhone(user.getPhone());
        userRepository.save(userEntity);
    }

    public void deleteUser(String id) throws UserException {
        UserEntity userEntity = userRepository
                .findById(id)
                .orElseThrow(() -> new UserException(
                                "User can't be deleted, user with Id: " + id + " not found",
                                null,
                                HttpStatus.NOT_FOUND.value()
                        )
                );

        userRepository.delete(userEntity);
    }

}

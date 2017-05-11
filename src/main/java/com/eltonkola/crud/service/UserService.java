package com.eltonkola.crud.service;

import com.eltonkola.crud.domain.User;
import com.eltonkola.crud.form.UserCreateForm;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);

}
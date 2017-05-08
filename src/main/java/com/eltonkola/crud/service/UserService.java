package com.eltonkola.radioz.service;

import com.eltonkola.radioz.domain.User;
import com.eltonkola.radioz.form.UserCreateForm;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);

}
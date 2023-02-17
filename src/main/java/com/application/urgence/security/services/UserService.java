package com.application.urgence.security.services;


import com.application.urgence.models.User;

public interface UserService {

    public void updateUserPassword(User user, String newpassword);

    public void resetPassword(User user);
}

package com.eugene.security;

/**
 * Created by DCLab on 12/25/2015.
 */
public interface UserContext {
    User getCurrentUser();
    void setCurrentUser(User user);
}

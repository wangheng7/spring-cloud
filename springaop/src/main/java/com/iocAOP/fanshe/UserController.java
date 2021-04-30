package com.iocAOP.fanshe;

/**
 * ClassName:    UserController
 * Package:    com.iocAOP.fanshe
 * Description:
 * Datetime:    2021/4/27   20:44
 * Author:   XXXXX@XX.com
 */
public class UserController {

    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

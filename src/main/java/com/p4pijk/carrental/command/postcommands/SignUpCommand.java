package com.p4pijk.carrental.command.postcommands;

import com.p4pijk.carrental.command.ServletCommand;
import com.p4pijk.carrental.dao.user.UserDaoImpl;
import com.p4pijk.carrental.model.user.User;
import com.p4pijk.carrental.service.user.UserService;
import com.p4pijk.carrental.util.CarUtil;
import com.p4pijk.carrental.util.MappingProperties;
import com.p4pijk.carrental.util.UserUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(SignUpCommand.class);
    private static UserService userService;
    private final String signUpPage;
    private final String mainPage;

    public SignUpCommand() {
        userService = new UserService(UserDaoImpl.getInstance());

        MappingProperties prop = MappingProperties.getInstance();
        signUpPage = prop.getProperty("signupPagePost");
        mainPage = prop.getProperty("mainPagePost");
        //errorPage
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing signup command");

        String resultPage = signUpPage;
        String[] signupFields = UserUtil.getRegistrationFields(req);

        log.info("New user registration");

        if (isaNewUser(signupFields)) {
            User user = UserUtil.createUser(signupFields);
            if (userService.signUp(user)) {
                resultPage = mainPage;
                UserUtil.putToSession(req, user);
                CarUtil.setFilterToSession(req);
            }
        }
        return resultPage;
    }

    private boolean isaNewUser(String[] newUser) {
        return newUser[0] != null && newUser[1] != null
                && newUser[2] != null && newUser[3] != null
                && newUser[4] != null && newUser[5] != null
                && newUser[5].equals(newUser[4])
                && checkUserLogin(newUser[3]) == null;
    }

    private User checkUserLogin(String login) {
        log.info("Making verification for unique login");
        return userService.checkUserLogin(login);
    }


}

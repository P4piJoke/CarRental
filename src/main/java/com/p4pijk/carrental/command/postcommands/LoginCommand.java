package com.p4pijk.carrental.command.postcommands;

import com.p4pijk.carrental.command.ServletCommand;
import com.p4pijk.carrental.dao.car.CarDaoImpl;
import com.p4pijk.carrental.dao.user.UserDaoImpl;
import com.p4pijk.carrental.model.car.Car;
import com.p4pijk.carrental.model.car.CarClass;
import com.p4pijk.carrental.model.car.CarMark;
import com.p4pijk.carrental.model.user.User;
import com.p4pijk.carrental.service.car.CarService;
import com.p4pijk.carrental.service.user.UserService;
import com.p4pijk.carrental.util.CarUtil;
import com.p4pijk.carrental.util.MappingProperties;
import com.p4pijk.carrental.util.UserUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoginCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(LoginCommand.class);
    private static UserService userService;
    private static String loginPage;
    private static String mainPage;
    private static String adminPage;
    private static String managerPage;
    private final CarService carService;

    public LoginCommand() {
        userService = new UserService(UserDaoImpl.getInstance());
        carService = new CarService(CarDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        loginPage = properties.getProperty("loginPagePost");
        mainPage = properties.getProperty("mainPagePost");
        adminPage = properties.getProperty("adminPagePost");
        managerPage = properties.getProperty("managerPagePost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing logging command");
        String resultPage = loginPage;
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login != null && password != null) {
            User user = userService.getUserByCredentials(login, password);

            if (user != null) {
                UserUtil.putToSession(req, user);
                CarUtil.setFilterToSession(req);

                switch (user.getUserRole()) {
                    case USER:
                        log.info("Return all cars info");
                        List<Car> availableCars = carService.getAllAvailableCars();
                        req.setAttribute("cars", availableCars);
                        log.info("Redirecting to user page");
                        resultPage = mainPage;
                        break;
                    case MANAGER:
                        log.info("Redirecting to manager page");
                        resultPage = managerPage;
                        break;
                    case ADMIN:
                        log.info("Return all cars info");
                        List<Car> cars = carService.getAllCars();
                        req.setAttribute("cars", cars);
                        log.info("Redirecting to admin page");
                        resultPage = adminPage;
                        break;
                }
            }
        }
        return resultPage;
    }


}

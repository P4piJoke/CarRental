package com.p4pijk.carrental.command.getcommands;

import com.p4pijk.carrental.command.ServletCommand;
import com.p4pijk.carrental.dao.car.CarDaoImpl;
import com.p4pijk.carrental.dao.receipt.ReceiptDaoImpl;
import com.p4pijk.carrental.dao.user.UserDaoImpl;
import com.p4pijk.carrental.model.car.Car;
import com.p4pijk.carrental.model.user.User;
import com.p4pijk.carrental.service.car.CarService;
import com.p4pijk.carrental.service.receipt.ReceiptService;
import com.p4pijk.carrental.service.user.UserService;
import com.p4pijk.carrental.util.CarUtil;
import com.p4pijk.carrental.util.MappingProperties;
import com.p4pijk.carrental.util.ReceiptUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginGetCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(LoginGetCommand.class);
    private static String loginPage;
    private static String mainPage;
    private final String adminPage;
    private final String managerPage;
    private final UserService userService;
    private final CarService carService;
    private final ReceiptService receiptService;

    public LoginGetCommand() {
        userService = new UserService(UserDaoImpl.getInstance());
        carService = new CarService(CarDaoImpl.getInstance());
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        mainPage = properties.getProperty("mainPage");
        loginPage = properties.getProperty("loginPage");
        adminPage = properties.getProperty("adminPage");
        managerPage = properties.getProperty("managerPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing login page command");
        String resultPage = loginPage;
        HttpSession session = req.getSession();

        if (session.getAttribute("authorized") != null
                && session.getAttribute("authorized").equals(true)) {
            resultPage = mainPage;

            if (session.getAttribute("role") != null) {
                CarUtil.setFilterToSession(req);

                log.info("Return all cars info");
                List<Car> cars = carService.getAllAvailableCars();
                req.setAttribute("cars", cars);

                if (session.getAttribute("role").equals("admin")) {
                    List<User> users = userService.getAllUsers();
                    req.setAttribute("users", users);
                    resultPage = adminPage;

                } else if (session.getAttribute("role").equals("manager")) {
                    ReceiptUtil.setRecipesAttributes(req, receiptService);
                    resultPage = managerPage;
                }
            }
            return resultPage;
        }
        log.info("Returning to login page");
        return resultPage;
    }
}
package com.p4pijk.carrental.command.admin;

import com.p4pijk.carrental.command.ServletCommand;
import com.p4pijk.carrental.dao.car.CarDaoImpl;
import com.p4pijk.carrental.dao.user.UserDaoImpl;
import com.p4pijk.carrental.model.car.Car;
import com.p4pijk.carrental.model.user.User;
import com.p4pijk.carrental.service.car.CarService;
import com.p4pijk.carrental.service.user.UserService;
import com.p4pijk.carrental.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminPage implements ServletCommand {

    private static final Logger log = Logger.getLogger(AdminPage.class);
    private final CarService carService;
    private final UserService userService;
    private final String adminPage;

    public AdminPage() {
        userService = new UserService(UserDaoImpl.getInstance());
        carService = new CarService(CarDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        adminPage = properties.getProperty("adminPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing admin page get command");
        List<Car> cars = carService.getAllCars();
        req.setAttribute("cars", cars);
        List<User> users = userService.getAllUsers();
        req.setAttribute("users", users);
        return adminPage;
    }
}

package com.p4pijk.carrental.command.getcommands;

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

public class TakeOrderGetCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(TakeOrderGetCommand.class);
    private final CarService carService;
    private final UserService userService;
    private final String mainPage;
    private final String takeOrderPage;

    public TakeOrderGetCommand() {
        carService = new CarService(CarDaoImpl.getInstance());
        userService = new UserService(UserDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        mainPage = properties.getProperty("mainPage");
        takeOrderPage = properties.getProperty("takeOrder");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing take order GET command");
        String resultPage = mainPage;

        if (req.getParameter("carId") != null
                && req.getParameter("userId") != null){
            long carId = Long.parseLong(req.getParameter("carId"));
            long userId = Long.parseLong(req.getParameter("userId"));
            User user = userService.getUserById(userId);
            Car car = carService.getCarById(carId);
            if (user != null && car != null) {
                req.setAttribute("user", user);
                req.setAttribute("car", car);
                resultPage = takeOrderPage;
            }
        }
        return resultPage;
    }
}

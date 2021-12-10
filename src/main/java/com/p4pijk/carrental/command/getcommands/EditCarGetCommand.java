package com.p4pijk.carrental.command.getcommands;

import com.p4pijk.carrental.command.ServletCommand;
import com.p4pijk.carrental.dao.car.CarDaoImpl;
import com.p4pijk.carrental.model.car.Car;
import com.p4pijk.carrental.service.car.CarService;
import com.p4pijk.carrental.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditCarGetCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(EditCarGetCommand.class);
    private final CarService carService;
    private final String editCarPage;
    private final String adminPage;

    public EditCarGetCommand() {
        carService = new CarService(CarDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        editCarPage = properties.getProperty("editCar");
        adminPage = properties.getProperty("adminPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing edit car GET command");
        String resultPage = adminPage;
        long carId = Long.parseLong(req.getParameter("carId"));
        Car car = carService.getCarById(carId);
        if (car != null){
            req.setAttribute("car", car);
            resultPage = editCarPage;
        }
        return resultPage;
    }
}

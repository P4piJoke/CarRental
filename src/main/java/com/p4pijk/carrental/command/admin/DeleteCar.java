package com.p4pijk.carrental.command.admin;

import com.p4pijk.carrental.command.ServletCommand;
import com.p4pijk.carrental.dao.car.CarDaoImpl;
import com.p4pijk.carrental.model.car.Car;
import com.p4pijk.carrental.service.car.CarService;
import com.p4pijk.carrental.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCar implements ServletCommand {

    private static final Logger log = Logger.getLogger(DeleteCar.class);
    private final String deleteCar;
    private final CarService carService;

    public DeleteCar() {
        carService = new CarService(CarDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        deleteCar = properties.getProperty("deleteCarPost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing delete car command");
        long carId = Long.parseLong(req.getParameter("carId"));
        Car car = carService.getCarById(carId);
        carService.deleteCar(car);
        log.info("Car deleting was successful");
        return deleteCar;
    }
}

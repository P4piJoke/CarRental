package com.p4pijk.carrental.command.getcommands;

import com.p4pijk.carrental.command.ServletCommand;
import com.p4pijk.carrental.dao.car.CarDaoImpl;
import com.p4pijk.carrental.model.car.Car;
import com.p4pijk.carrental.service.car.CarService;
import com.p4pijk.carrental.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetCarByClass implements ServletCommand {

    private static final Logger log = Logger.getLogger(GetCarByClass.class);
    private static String resultPage;
    private CarService carService;

    public GetCarByClass() {
        carService = new CarService(CarDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        resultPage = properties.getProperty("carByClass");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String carClass = req.getParameter("car_class");
        log.info("Getting car by class");

        log.info("Cars by car was executed");
        List<Car> carsByClass = carService.getCarsByClass(carClass);
        req.setAttribute("carsByClassList", carsByClass);

        return resultPage;
    }
}

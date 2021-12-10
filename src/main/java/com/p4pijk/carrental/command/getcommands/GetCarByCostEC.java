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

public class GetCarByCostEC implements ServletCommand {

    private static final Logger log = Logger.getLogger(GetCarByCostEC.class);
    private final String resultPage;
    private final CarService carService;

    public GetCarByCostEC(){
        carService = new CarService(CarDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        resultPage = properties.getProperty("carByCost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing get car by cost E-c command");
        List<Car> carCostECList = carService.getCarsByCostEC();
        req.setAttribute("carByCostEC", carCostECList);
        return resultPage;
    }
}

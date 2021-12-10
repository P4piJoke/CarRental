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

public class GetCarByCostCE implements ServletCommand {

    private static final Logger log = Logger.getLogger(GetCarByCostCE.class);
    private final String resultPage;
    private final CarService carService;

    public GetCarByCostCE(){
        carService = new CarService(CarDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        resultPage = properties.getProperty("carByCost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing get car by cost c-E command");
        List<Car> carCostCEList = carService.getCarsByCostCE();
        req.setAttribute("carByCostCE", carCostCEList);
        return resultPage;
    }
}

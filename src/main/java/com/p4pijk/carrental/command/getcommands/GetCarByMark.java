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

public class GetCarByMark implements ServletCommand {

    private static final Logger log = Logger.getLogger(GetCarByMark.class);
    private static String resultPage;
    private CarService carService;

    public GetCarByMark() {
        carService = new CarService(CarDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        resultPage = properties.getProperty("carByMark");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String mark = req.getParameter("mark");
        log.info("Getting car by mark");

        log.info("Cars by mark was executed");
        List<Car> carsByMark = carService.getCarsByMark(mark);
        req.setAttribute("carsByMarkList", carsByMark);
        req.setAttribute("markName", mark);

        return resultPage;
    }
}

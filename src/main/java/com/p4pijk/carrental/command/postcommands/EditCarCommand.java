package com.p4pijk.carrental.command.postcommands;

import com.p4pijk.carrental.command.ServletCommand;
import com.p4pijk.carrental.dao.car.CarDaoImpl;
import com.p4pijk.carrental.model.car.Car;
import com.p4pijk.carrental.service.car.CarService;
import com.p4pijk.carrental.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditCarCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(EditCarCommand.class);
    private final CarService carService;
    private final String adminPage;
    private final String editCarPage;

    public EditCarCommand() {
        carService = new CarService(CarDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        adminPage = properties.getProperty("adminPagePost");
        editCarPage = properties.getProperty("editCarPost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing edit car POST command");
        String resultPage = editCarPage;

        String carName = req.getParameter("carName");
        String carClass = req.getParameter("carClass");
        String carMark = req.getParameter("carMark");
        String carCost = req.getParameter("carCost");
        long carId = Long.parseLong(req.getParameter("carId"));
        String[] params = {carName, carClass, carMark, carCost};
        Car carById = carService.getCarById(carId);
        log.info("Processing update car");
        if (carById != null) {
            log.info("Car update start");
            carService.editCar(carById, params);
            resultPage = adminPage;
        }
        return resultPage;
    }
}

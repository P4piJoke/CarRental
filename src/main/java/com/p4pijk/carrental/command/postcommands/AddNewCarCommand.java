package com.p4pijk.carrental.command.postcommands;

import com.p4pijk.carrental.command.ServletCommand;
import com.p4pijk.carrental.dao.car.CarDaoImpl;
import com.p4pijk.carrental.model.car.Car;
import com.p4pijk.carrental.service.car.CarService;
import com.p4pijk.carrental.util.CarUtil;
import com.p4pijk.carrental.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddNewCarCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(AddNewCarCommand.class);
    private CarService carService;
    private final String addCarPage;
    private final String adminPage;

    public AddNewCarCommand() {
        carService = new CarService(CarDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        addCarPage = properties.getProperty("addNewCarPost");
        adminPage = properties.getProperty("adminPagePost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing additional new car by post command");

        String resultPage = addCarPage;
        String carName = req.getParameter("carName");
        String carMark = req.getParameter("mark");
        String carClass = req.getParameter("car_class");
        String carCost = req.getParameter("carCost");
        String[] newCarInfo = {carName, carMark, carClass, carCost};

        if (isaNewCar(newCarInfo)) {
            log.info("Addition new car");
            Car newCar = CarUtil.createNewCar(newCarInfo);
            if (carService.addCar(newCar)) {
                log.info("Addition new car was successful");
                resultPage = adminPage;
            }
        }
        return resultPage;
    }

    private boolean isaNewCar(String[] newCarInfo) {
        return newCarInfo[0] != null &&
                newCarInfo[1] != null &&
                newCarInfo[2] != null &&
                newCarInfo[3] != null;
    }
}

package com.p4pijk.carrental.command.getcommands;

import com.p4pijk.carrental.command.ServletCommand;
import com.p4pijk.carrental.dao.car.CarDaoImpl;
import com.p4pijk.carrental.dao.receipt.ReceiptDaoImpl;
import com.p4pijk.carrental.dao.user.UserDaoImpl;
import com.p4pijk.carrental.model.car.Car;
import com.p4pijk.carrental.model.receipt.Receipt;
import com.p4pijk.carrental.model.user.User;
import com.p4pijk.carrental.service.car.CarService;
import com.p4pijk.carrental.service.receipt.ReceiptService;
import com.p4pijk.carrental.service.user.UserService;
import com.p4pijk.carrental.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyReceiptCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(VerifyReceiptCommand.class);
    private final ReceiptService receiptService;
    private final CarService carService;
    private final UserService userService;
    private final String managerPage;
    private final String verifyPage;

    public VerifyReceiptCommand(){
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());
        carService = new CarService(CarDaoImpl.getInstance());
        userService = new UserService(UserDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        managerPage = properties.getProperty("managerPage");
        verifyPage = properties.getProperty("verifyPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing verify receipt GET command");
        String resultPage = managerPage;
        if (req.getParameter("orderId") != null){
            log.info("Getting info about order to verify it");
            long orderId = Long.parseLong(req.getParameter("orderId"));
            Receipt receipt = receiptService.getReceiptById(orderId);
            Car car = carService.getCarById(receipt.getCarId());
            User user = userService.getUserById(receipt.getUserId());

            req.setAttribute("orderUser", user);
            req.setAttribute("orderCar", car);
            req.setAttribute("orderVerify", receipt);
            resultPage = verifyPage;
        }
        return resultPage;
    }
}

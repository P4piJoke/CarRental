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

public class CheckCommentCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(CheckCommentCommand.class);
    private final ReceiptService receiptService;
    private final CarService carService;
    private final UserService userService;
    private final String userCabinet;
    private final String commentPage;

    public CheckCommentCommand() {
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());
        carService = new CarService(CarDaoImpl.getInstance());
        userService = new UserService(UserDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        userCabinet = properties.getProperty("userCabinet");
        commentPage = properties.getProperty("commentPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing check comment GET command");
        String resultPage = userCabinet;
        if (req.getParameter("orderId") != null) {
            log.info("Getting info about user order");
            long orderId = Long.parseLong(req.getParameter("orderId"));
            Receipt receiptById = receiptService.getReceiptById(orderId);
            User userByReceipt = userService.getUserById(receiptById.getUserId());
            Car carByReceipt = carService.getCarById(receiptById.getCarId());

            if (userByReceipt != null && carByReceipt != null){
                req.setAttribute("userByReceipt", userByReceipt);
                req.setAttribute("carByReceipt", carByReceipt);
                req.setAttribute("order", receiptById);
                resultPage = commentPage;
            }
        }
        return resultPage;
    }
}

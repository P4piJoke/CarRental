package com.p4pijk.carrental.command.postcommands;

import com.p4pijk.carrental.command.ServletCommand;
import com.p4pijk.carrental.command.getcommands.TakeOrderGetCommand;
import com.p4pijk.carrental.dao.car.CarDaoImpl;
import com.p4pijk.carrental.dao.receipt.ReceiptDaoImpl;
import com.p4pijk.carrental.model.car.Car;
import com.p4pijk.carrental.model.receipt.Receipt;
import com.p4pijk.carrental.model.receipt.RentOption;
import com.p4pijk.carrental.service.car.CarService;
import com.p4pijk.carrental.service.receipt.ReceiptService;
import com.p4pijk.carrental.util.MappingProperties;
import com.p4pijk.carrental.util.ReceiptUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TakeOrderCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(TakeOrderCommand.class);
    private final ReceiptService receiptService;
    private final CarService carService;
    private final String mainPage;
    private final String takeOrderPage;

    public TakeOrderCommand() {
        carService = new CarService(CarDaoImpl.getInstance());
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        mainPage = properties.getProperty("mainPagePost");
        takeOrderPage = properties.getProperty("takeOrderPost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing take order POST command");
        String resultPage = takeOrderPage;

        String pass = req.getParameter("pass");
        String option = req.getParameter("option");
        String duration = req.getParameter("duration");
        String carId = req.getParameter("carId");
        String userId = req.getParameter("userId");
        if (isaNewOrder(pass, option, duration, carId, userId)) {
            log.info("Creating new order");
            Car car = carService.getCarById(Long.parseLong(carId));
            Receipt receipt =
                    ReceiptUtil.createNewReceipt(car.getCost(), pass, option, duration, carId, userId);
            if (receiptService.createRecipe(receipt)) {
                log.info("Creating new order was successful");
                resultPage = mainPage;
            }
        }
        return resultPage;
    }

    private boolean isaNewOrder(String... param) {
        int i = 0;
        for (String s : param) {
            if (s != null) {
                ++i;
            }
        }
        return i == param.length && checkDate(param[2]);
    }

    private boolean checkDate(String date) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate inputDate = LocalDate.parse(date, formatter);
        return inputDate.isAfter(now);
    }
}

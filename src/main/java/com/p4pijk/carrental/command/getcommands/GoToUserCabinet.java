package com.p4pijk.carrental.command.getcommands;

import com.p4pijk.carrental.command.ServletCommand;
import com.p4pijk.carrental.dao.receipt.ReceiptDaoImpl;
import com.p4pijk.carrental.dao.user.UserDaoImpl;
import com.p4pijk.carrental.model.receipt.Receipt;
import com.p4pijk.carrental.model.user.User;
import com.p4pijk.carrental.service.receipt.ReceiptService;
import com.p4pijk.carrental.service.user.UserService;
import com.p4pijk.carrental.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GoToUserCabinet implements ServletCommand {

    private static final Logger log = Logger.getLogger(GoToUserCabinet.class);
    private final UserService userService;
    private final ReceiptService receiptService;
    private final String mainPage;
    private final String userCabinet;

    public GoToUserCabinet() {
        userService = new UserService(UserDaoImpl.getInstance());
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        mainPage = properties.getProperty("mainPage");
        userCabinet = properties.getProperty("userCabinet");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Execute go to user cabinet command");
        String resultPage = mainPage;

        long userId = Long.parseLong(req.getParameter("userId"));
        User userById = userService.getUserById(userId);
        List<Receipt> receiptList = receiptService.getRecipesByUserId(userId);
        if (userById != null) {
            log.info("Redirecting to user cabinet");
            req.setAttribute("user", userById);
            req.setAttribute("recipes", receiptList);
            resultPage = userCabinet;
        }
        return resultPage;
    }
}

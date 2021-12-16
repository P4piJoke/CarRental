package com.p4pijk.carrental.command.getcommands;

import com.p4pijk.carrental.command.ServletCommand;
import com.p4pijk.carrental.dao.receipt.ReceiptDaoImpl;
import com.p4pijk.carrental.model.receipt.Receipt;
import com.p4pijk.carrental.service.receipt.ReceiptService;
import com.p4pijk.carrental.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RejectReceiptCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(RejectReceiptCommand.class);
    private final ReceiptService receiptService;
    private final String managerPage;
    private final String rejectPage;

    public RejectReceiptCommand() {
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        managerPage = properties.getProperty("managerPage");
        rejectPage = properties.getProperty("rejectPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing reject receipt GET command");
        String resultPage = managerPage;
        if (req.getParameter("orderId") != null) {
            long orderId = Long.parseLong(req.getParameter("orderId"));
            Receipt receipt = receiptService.getReceiptById(orderId);
            resultPage = rejectPage;
            req.setAttribute("order", receipt);
        }
        return resultPage;
    }
}
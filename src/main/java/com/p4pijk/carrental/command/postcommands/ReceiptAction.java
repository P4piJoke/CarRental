package com.p4pijk.carrental.command.postcommands;

import com.p4pijk.carrental.command.ServletCommand;
import com.p4pijk.carrental.dao.receipt.ReceiptDaoImpl;
import com.p4pijk.carrental.model.receipt.OrderStatus;
import com.p4pijk.carrental.model.receipt.Receipt;
import com.p4pijk.carrental.service.receipt.ReceiptService;
import com.p4pijk.carrental.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReceiptAction implements ServletCommand {

    private static final Logger log = Logger.getLogger(ReceiptAction.class);
    private final ReceiptService receiptService;
    private final String mainPagePost;

    public ReceiptAction() {
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        mainPagePost = properties.getProperty("mainPagePost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Execute receipt action POST command");
        if (req.getParameter("return") != null) {
            log.info("Returning ordered car");
            long returnId = Long.parseLong(req.getParameter("return"));
            returnCar(returnId);
        } else if (req.getParameter("repair") != null) {
            log.info("Do repair payment");
            long repairId = Long.parseLong(req.getParameter("repair"));
            repairPayment(repairId);
        } else {
            log.info("Receipt action was failed, returning to main page");
        }
        return mainPagePost;
    }

    private void returnCar(long returnId) {
        log.info("Returning ordered car by id");
        receiptService.returnCarById(returnId, OrderStatus.RETURNED.ordinal());
        log.info("Returning was successful");
    }

    private void repairPayment(long repairId) {
        log.info("Doing repair payment");
        Receipt broken = receiptService.getReceiptById(repairId);
        receiptService.doReceiptTransaction(repairId, broken.getUserId(),
                broken.getRepairBill(), OrderStatus.CLOSED.ordinal());
        log.debug("Repair payment was successful");
    }
}

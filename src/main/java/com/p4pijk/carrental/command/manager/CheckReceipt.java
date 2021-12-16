package com.p4pijk.carrental.command.manager;

import com.p4pijk.carrental.command.ServletCommand;
import com.p4pijk.carrental.dao.receipt.ReceiptDaoImpl;
import com.p4pijk.carrental.model.receipt.OrderStatus;
import com.p4pijk.carrental.model.receipt.Receipt;
import com.p4pijk.carrental.service.receipt.ReceiptService;
import com.p4pijk.carrental.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CheckReceipt implements ServletCommand {

    private static final Logger log = Logger.getLogger(CheckReceipt.class);
    private final ReceiptService receiptService;
    private final String managerPagePost;

    public CheckReceipt() {
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        managerPagePost = properties.getProperty("managerPagePost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Execute check receipt POST command");
        if (req.getParameter("approve") != null) {
            log.info("Approving order by id");
            long approveId = Long.parseLong(req.getParameter("approve"));
            approveReceipt(approveId);
        }
//        else if (req.getParameter("payRepair") != null){
//            log.info("Repair payment");
//            long paymentId = Long.parseLong(req.getParameter("payRepair"));
//            repairPayment(paymentId);
//        }
        else if (req.getParameter("closeOrder") != null) {
            log.info("Closing the order");
            long closeOrderId = Long.parseLong(req.getParameter("closeOrder"));
            closeOrder(closeOrderId);
        } else {
            log.info("Check receipt command was failed, returning to manager page");
        }
        return managerPagePost;
    }

    private void approveReceipt(long approveId) {
        log.info("Approving order");
        Receipt receipt = receiptService.getReceiptById(approveId);
        receiptService.approveReceiptById(approveId, receipt.getUserId(),
                receipt.getBillCost(), OrderStatus.ACTIVE.ordinal());
        log.info("Approving was successful");
    }

//    private void repairPayment(long paymentId) {
//        log.info("Make repair payment");
//        Receipt receipt = receiptService.getReceiptById(paymentId);
//        receiptService.payRepairById(paymentId, receipt.getUserId(),
//                receipt.getRepairBill(), OrderStatus.CLOSED.ordinal());
//        log.info("Repair payment was successful");
//    }

    private void closeOrder(long closeOrderId) {
        log.info("Closing the order");
        receiptService.closeOrderById(closeOrderId, OrderStatus.CLOSED.ordinal());
        log.info("The closure was successful");
    }
}

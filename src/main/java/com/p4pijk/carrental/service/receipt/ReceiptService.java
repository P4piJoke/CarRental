package com.p4pijk.carrental.service.receipt;

import com.p4pijk.carrental.dao.receipt.ReceiptDao;
import com.p4pijk.carrental.model.receipt.Receipt;
import org.apache.log4j.Logger;

import java.util.List;

public class ReceiptService {

    private static final Logger log = Logger.getLogger(ReceiptService.class);
    private final ReceiptDao receiptDao;

    public ReceiptService(ReceiptDao receiptDao) {
        this.receiptDao = receiptDao;
    }

    public boolean createRecipe(Receipt receipt) {
        return receipt != null && receiptDao.save(receipt).getId() != -1;
    }

    public List<Receipt> getRecipesByUserIdAndStatus(long userId, int statusId) {
        log.info("Getting recipes by user id and status");
        return receiptDao.getUserRecipes(userId, statusId);
    }

    public List<Receipt> getRecipesByStatus(int statusId) {
        log.info("Getting recipes by status");
        return receiptDao.getRecipesByStatus(statusId);
    }

    public void approveReceiptById(long approveId, long userId, double cost, long statusId) {
        log.info("Approving receipt by id");
        receiptDao.approveReceipt(approveId, userId, cost, statusId);
    }

//    public void payRepairById(long paymentId, long userId, double cost, long statusID) {
//        log.info("Pay for repair by order id");
//        receiptDao.repairPayment(paymentId, userId);
//    }

    public void closeOrderById(long closeOrderId, long statusID) {
        log.info("Closing order by id");
        receiptDao.closeOrder(closeOrderId, statusID);
    }

    public Receipt getReceiptById(long receiptId) {
        log.info("Getting receipt by id");
        return receiptDao.get(receiptId);
    }
}

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

    public List<Receipt> getRecipesByUserId(long userId) {
        log.info("Getting recipes by user id");
        return receiptDao.getRecipes(userId);
    }

    public List<Receipt> getRecipesByStatus(int statusId) {
        log.info("Getting recipes by status");
        return receiptDao.getRecipesByStatus(statusId);
    }
}

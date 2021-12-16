package com.p4pijk.carrental.dao.receipt;

import com.p4pijk.carrental.dao.DAO;
import com.p4pijk.carrental.model.receipt.Receipt;

import java.util.List;

public interface ReceiptDao extends DAO<Receipt> {

    List<Receipt> getUserRecipes(long userId, int statusId);

    List<Receipt> getRecipesByStatus(int statusId);

    void approveReceipt(long approveId, long userId, double cost, long statusId);

//    void repairPayment(long paymentId, long userId);

    void closeOrder(long closeOrderId, long userId);
}

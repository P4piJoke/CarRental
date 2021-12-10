package com.p4pijk.carrental.dao.receipt;

import com.p4pijk.carrental.dao.DAO;
import com.p4pijk.carrental.model.receipt.Receipt;

import java.util.List;

public interface ReceiptDao extends DAO<Receipt> {

    List<Receipt> getRecipes(long userId);

    List<Receipt> getRecipesByStatus(int statusId);
}

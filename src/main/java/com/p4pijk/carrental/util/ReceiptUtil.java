package com.p4pijk.carrental.util;

import com.p4pijk.carrental.model.receipt.OrderStatus;
import com.p4pijk.carrental.model.receipt.Receipt;
import com.p4pijk.carrental.model.receipt.RentOption;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceiptUtil {

    public static Receipt createNewReceipt(String... param) {
        return Receipt.builder()
                .passport(Integer.parseInt(param[0]))
                .rentOption(RentOption.valueOf(param[1]))
                .duration(param[2])
                .carId(Long.parseLong(param[3]))
                .userId(Long.parseLong(param[4]))
                .billCost(0)
                .repairBill(0)
                .comment("")
                .orderStatus(OrderStatus.PENDING)
                .build();
    }

    public static Receipt createReceiptFromResultSet(ResultSet resultSet) throws SQLException {
        return Receipt.builder()
                .id(resultSet.getInt("id"))
                .userId(resultSet.getInt("user_id"))
                .carId(resultSet.getInt("car_id"))
                .passport(resultSet.getInt("passport"))
                .rentOption(RentOption.values()[resultSet.getInt("option_id")])
                .duration(String.valueOf(resultSet.getDate("rent_duration"))) // TODO make useful variable type
                .orderStatus(OrderStatus.values()[resultSet.getInt("status_id")])
                .comment(resultSet.getString("receipt_comm"))
                .billCost(resultSet.getDouble("bill_cost"))
                .repairBill(resultSet.getDouble("remont_bill"))
                .build();
    }
}

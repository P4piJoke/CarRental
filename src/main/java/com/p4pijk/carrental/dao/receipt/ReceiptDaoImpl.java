package com.p4pijk.carrental.dao.receipt;

import com.p4pijk.carrental.connection.BasicConnectionPool;
import com.p4pijk.carrental.dao.QUERY;
import com.p4pijk.carrental.model.receipt.Receipt;
import com.p4pijk.carrental.util.ReceiptUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReceiptDaoImpl implements ReceiptDao {

    private static final Logger log = Logger.getLogger(ReceiptDaoImpl.class);
    private static ReceiptDaoImpl instance;
    private static BasicConnectionPool basicConnectionPool;
    private Connection connection;

    private ReceiptDaoImpl() {
        try {
            basicConnectionPool = BasicConnectionPool.create();
            connection = basicConnectionPool.getConnection();
        } catch (SQLException exception) {
            log.error(exception);
        }
    }

    public static ReceiptDaoImpl getInstance() {
        if (instance == null) {
            instance = new ReceiptDaoImpl();
        }
        return instance;
    }

    @Override
    public Receipt save(Receipt receipt) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.CREATE_RECEIPT.query(), Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, receipt.getUserId());
            statement.setLong(2, receipt.getCarId());
            statement.setInt(3, receipt.getPassport());
            statement.setLong(4, receipt.getRentOption().ordinal());
            statement.setDate(5, Date.valueOf(receipt.getDuration()));
            statement.setLong(6, receipt.getOrderStatus().ordinal());
            statement.setString(7, receipt.getComment());
            statement.setDouble(8, receipt.getBillCost());
            statement.setDouble(9, receipt.getRepairBill());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                log.error("Receipt creation is failed");
            } else {
                log.info("Receipt creation is successful");
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        Receipt.builder().id(generatedKeys.getLong(1));
                    } else {
                        log.error("Failed to create receipt, no obtained id");
                    }
                }
            }
        } catch (SQLException exception) {
            log.error(exception);
        }
        return receipt;
    }

    @Override
    public void update(Receipt receipt, String[] params) {

    }

    @Override
    public Receipt get(long id) {
        return null;
    }

    @Override
    public void delete(Receipt receipt) {

    }

    @Override
    public List<Receipt> getAll() {
        return null;
    }

    @Override
    public List<Receipt> getRecipes(long userId) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.GET_RECIPES.query())) {
            statement.setLong(1, userId);
            statement.execute();
            return getRecipesFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Receipt> getRecipesByStatus(int statusId) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.GET_RECIPES_BY_STATUS.query())) {
            statement.setLong(1, statusId);
            statement.execute();
            return getRecipesFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return Collections.emptyList();
    }

    private List<Receipt> getRecipesFromResultSet(ResultSet resultSet) {
        List<Receipt> receipts = new ArrayList<>();
        try {
            while (resultSet.next()) {
                receipts.add(buildReceiptFromResultSet(resultSet));
                log.info("Receipt was found and packed in object");
            }
        } catch (SQLException exception) {
            log.error(exception);
        }
        return receipts;
    }

    private Receipt getReceiptFromResultSet(ResultSet resultSet) {
        log.info("Getting receipt from result set");
        try {
            if (resultSet.next()) {
                return buildReceiptFromResultSet(resultSet);
            }
        } catch (SQLException exception) {
            log.error(exception);
        }
        return null;
    }

    private Receipt buildReceiptFromResultSet(ResultSet resultSet) throws SQLException {
        log.info("Building receipt object");
        return ReceiptUtil.createReceiptFromResultSet(resultSet);
    }
}

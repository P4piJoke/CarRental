package com.p4pijk.carrental.dao.receipt;

import com.p4pijk.carrental.connection.BasicConnectionPool;
import com.p4pijk.carrental.dao.QUERY;
import com.p4pijk.carrental.model.receipt.Receipt;
import com.p4pijk.carrental.util.ReceiptUtil;
import org.apache.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        throw new NotImplementedException();
    }

    @Override
    public Receipt get(long id) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.GET_RECEIPT.query())) {
            statement.setLong(1, id);
            statement.execute();
            return getReceiptFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return null;
    }

    @Override
    public void delete(Receipt receipt) {
        throw new NotImplementedException();
    }

    @Override
    public List<Receipt> getAll() {
        throw new NotImplementedException();
    }

    @Override
    public List<Receipt> getUserRecipes(long userId, int statusId) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.GET_USER_RECIPES.query())) {
            statement.setLong(1, userId);
            statement.setLong(2, statusId);
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

    @Override
    public void approveReceipt(long approveId, long userId, double cost, long statusId) {
        try (PreparedStatement paymentStatement =
                     connection.prepareStatement(QUERY.DO_PAYMENT.query());
             PreparedStatement statusStatement
                     = connection.prepareStatement(QUERY.SET_RECEIPT_STATUS.query())) {
            connection.setAutoCommit(false);
            paymentStatement.setDouble(1, cost);
            paymentStatement.setLong(2, userId);
            paymentStatement.execute();

            statusStatement.setLong(1, statusId);
            statusStatement.setLong(2, approveId);
            statusStatement.execute();
            connection.commit();
            log.info("Transaction was successful");
        } catch (SQLException exception) {
            try {
                log.error("Receipt approving was failed");
                log.error("Transaction was failed and did rollback");
                connection.rollback();
            } catch (SQLException e) {
                log.error("Transaction rollback was failed");
            }
        }
    }

//    @Override
//    public void repairPayment(long paymentId, long userId) {
//    }

    @Override
    public void closeOrder(long closeOrderId, long statusId) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.SET_RECEIPT_STATUS.query())) {
            statement.setLong(1, statusId);
            statement.setLong(2, closeOrderId);
            statement.execute();
            log.info("Order closing was successful");
        } catch (SQLException exception) {
            log.error("Closing was failed");
        }
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

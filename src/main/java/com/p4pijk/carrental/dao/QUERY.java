package com.p4pijk.carrental.dao;

public enum QUERY {

    // User queries
    GET_USER("SELECT * FROM user WHERE id=?"),
    GET_ALL_USERS("SELECT * FROM user WHERE role_id = 1"),
    CREATE_USER("INSERT INTO user SET user_name = ?," +
            "user_surname = ?," +
            "email = ?," +
            "login = ?," +
            "password = ?," +
            "user_status = ?," +
            "role_id = ?," +
            "user_balance = ?"),
    GET_USER_BY_LOGIN_AND_PASSWORD("SELECT * FROM user WHERE " +
            "login = ? " +
            "AND password = ? ;"),
    CHANGE_USER_STATUS("UPDATE user SET " +
            "user_status = ? " +
            "WHERE id = ?"),
    CHECK_USER("SELECT * FROM user " +
            "WHERE login = ?"),
    TOP_UP("UPDATE user " +
            "SET user_balance = user_balance + ? " +
            "WHERE id = ?"),

    // Car queries
    CREATE_CAR("INSERT INTO car SET " +
            "car_name = ?, " +
            "class_id = ?, " +
            "mark_id = ?, " +
            "car_status = ?, " +
            "car_cost = ?"),
    GET_CAR("SELECT * FROM car WHERE id = ?"),
    GET_ALL_CAR("SELECT * FROM car"),
    GET_ALL_AVAILABLE_CAR("SELECT * FROM car " +
            "WHERE car.id != ALL(SELECT car_id FROM receipt)"),
    GET_CAR_BY_MARK("SELECT * FROM car WHERE mark_id = ?"),
    GET_CAR_BY_CLASS("SELECT * FROM car WHERE class_id = ?"),
    GET_SORTED_CAR_BY_NAME_A_Z("SELECT * FROM car ORDER BY car_name"),
    GET_SORTED_CAR_BY_NAME_Z_A("SELECT * FROM car ORDER BY car_name DESC"),
    GET_SORTED_CAR_BY_COST_C_E("SELECT * FROM car ORDER BY car_cost"),
    GET_SORTED_CAR_BY_COST_E_C("SELECT * FROM car ORDER BY car_cost DESC"),
    UPDATE_CAR_INFO("UPDATE car SET " +
            "car_name = ?, " +
            "class_id = ?, " +
            "mark_id = ?, " +
            "car_cost = ? " +
            "WHERE id = ?"),
    DELETE_CAR("DELETE FROM car WHERE id = ?"),

    // Receipt queries
    GET_RECEIPT("SELECT * FROM receipt WHERE id = ?"),
    CREATE_RECEIPT("INSERT INTO receipt SET " +
            "user_id = ?, " +
            "car_id = ?, " +
            "passport = ?, " +
            "option_id = ?, " +
            "rent_duration = ?, " +
            "status_id = ?, " +
            "receipt_comm = ?, " +
            "bill_cost = ?, " +
            "remont_bill = ?"),
    GET_USER_RECIPES("SELECT * FROM receipt " +
            "WHERE user_id = ? " +
            "AND status_id = ?"),
    GET_RECIPES_BY_STATUS("SELECT * FROM receipt " +
            "WHERE status_id = ?"),
    DO_PAYMENT("UPDATE user SET " +
            "user_balance = user_balance - ? " +
            "WHERE id = ?"),
    SET_RECEIPT_STATUS("UPDATE receipt SET " +
            "status_id = ? " +
            "WHERE id = ?"),
    MAKE_ORDER_REJECTED("UPDATE receipt SET " +
            "status_id = ?, " +
            "receipt_comm = ? " +
            "WHERE id = ?"),
    MAKE_ORDER_REPAIR_ACTIVE("UPDATE receipt SET " +
            "status_id = ?, " +
            "remont_bill = ? " +
            "WHERE id = ?");


    private final String query;

    QUERY(String query) {
        this.query = query;
    }

    public String query() {
        return query;
    }
}

package com.p4pijk.carrental.util;

import com.p4pijk.carrental.model.car.Car;
import com.p4pijk.carrental.model.car.CarClass;
import com.p4pijk.carrental.model.car.CarMark;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarUtil {

    public static Car createCarFromResultSet(ResultSet resultSet) throws SQLException {
        return Car.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("car_name"))
                .cost(resultSet.getDouble("car_cost"))
                .carClass(CarClass.values()[resultSet.getInt("class_id")])
                .carMark(CarMark.values()[resultSet.getInt("mark_id")])
                .status(resultSet.getBoolean("car_status"))
                .build();
    }

    public static void setFilterToSession(HttpServletRequest req) {
        req.getSession().setAttribute("carMarks", CarMark.values());
        req.getSession().setAttribute("carClass", CarClass.values());
    }

    public static Car createNewCar(String[] newCarInfo) {
        return Car.builder()
                .name(newCarInfo[0])
                .carMark(CarMark.valueOf(newCarInfo[1]))
                .carClass(CarClass.valueOf(newCarInfo[2]))
                .cost(Double.parseDouble(newCarInfo[3]))
                .status(true)
                .build();
    }
}
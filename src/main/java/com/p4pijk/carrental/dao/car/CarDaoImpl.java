package com.p4pijk.carrental.dao.car;

import com.p4pijk.carrental.connection.BasicConnectionPool;
import com.p4pijk.carrental.dao.QUERY;
import com.p4pijk.carrental.model.car.Car;
import com.p4pijk.carrental.model.car.CarClass;
import com.p4pijk.carrental.model.car.CarMark;
import com.p4pijk.carrental.util.CarUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarDaoImpl implements CarDao {

    private static final Logger log = Logger.getLogger(CarDaoImpl.class);
    private static CarDaoImpl instance;
    private Connection connection;
    private BasicConnectionPool basicConnectionPool;

    private CarDaoImpl() {
        try {
            basicConnectionPool = BasicConnectionPool.create();
            connection = basicConnectionPool.getConnection();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    public static CarDaoImpl getInstance() {
        if (instance == null) {
            instance = new CarDaoImpl();
        }
        return instance;
    }

    @Override
    public Car save(Car car) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.CREATE_CAR.query()
                             , Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, car.getName());
            statement.setLong(2, car.getCarClass().ordinal());
            statement.setLong(3, car.getCarMark().ordinal());
            statement.setBoolean(4, car.isStatus());
            statement.setDouble(5, car.getCost());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                log.error("Car wasn't created");
            } else {
                log.info("Car creation was successful");
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        Car.builder().id(generatedKeys.getLong(1));
                    } else {
                        log.error("Failed to create car, no obtained id");
                    }
                }
            }
        } catch (SQLException exception) {
            log.error(exception);
        }
        return car;
    }

    @Override
    public void update(Car car, String[] params) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.UPDATE_CAR_INFO.query())) {

            statement.setString(1, params[0]);
            statement.setLong(2, CarClass.valueOf(params[1]).ordinal());
            statement.setLong(3, CarMark.valueOf(params[2]).ordinal());
            statement.setDouble(4, Double.parseDouble(params[3]));
            statement.setLong(5, car.getId());
            statement.execute();

        } catch (SQLException exception) {
            log.error(exception);
        }
    }

    @Override
    public Car get(long id) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.GET_CAR.query())) {
            statement.setLong(1, id);
            statement.execute();
            return getCarFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return null;
    }

    @Override
    public void delete(Car car) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.DELETE_CAR.query())) {
            statement.setLong(1, car.getId());
            statement.execute();
        } catch (SQLException exception) {
            log.error(exception);
        }
    }

    @Override
    public List<Car> getAll() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(QUERY.GET_ALL_CAR.query());
            return getCarsFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return null;
    }

    private List<Car> getCarsFromResultSet(ResultSet resultSet) {
        List<Car> carList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                carList.add(buildCarFromResultSet(resultSet));
                log.info("Car was found and packed in object");
            }
        } catch (SQLException exception) {
            log.error(exception);
        }
        return carList;
    }

    private Car getCarFromResultSet(ResultSet resultSet) {
        log.info("Getting car from result set");
        try {
            if (resultSet.next()) {
                return buildCarFromResultSet(resultSet);
            }
        } catch (SQLException exception) {
            log.error(exception);
        }
        log.info("No car was found");
        return null;
    }

    private Car buildCarFromResultSet(ResultSet resultSet) throws SQLException {
        log.info("Building car object");
        return CarUtil.createCarFromResultSet(resultSet);
    }

    @Override
    public List<Car> getCarsByMark(Long markId) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.GET_CAR_BY_MARK.query())) {
            statement.setLong(1, markId);
            statement.execute();
            return getCarsFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Car> getCarsByClass(long classId) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.GET_CAR_BY_CLASS.query())) {
            statement.setLong(1, classId);
            statement.execute();
            return getCarsFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Car> getCarsByNameAZ() {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.GET_SORTED_CAR_BY_NAME_A_Z.query())){
            statement.execute();
            return getCarsFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Car> getCarsByNameZA() {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.GET_SORTED_CAR_BY_NAME_Z_A.query())){
            statement.execute();
            return getCarsFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Car> getCarsByCostEC() {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.GET_SORTED_CAR_BY_COST_E_C.query())){
            statement.execute();
            return getCarsFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Car> getCarsByCostCE() {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.GET_SORTED_CAR_BY_COST_C_E.query())){
            statement.execute();
            return getCarsFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Car> getAllAvailableCars() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(QUERY.GET_ALL_AVAILABLE_CAR.query());
            return getCarsFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return Collections.emptyList();
    }
}

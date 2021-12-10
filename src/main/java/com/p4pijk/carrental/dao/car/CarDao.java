package com.p4pijk.carrental.dao.car;

import com.p4pijk.carrental.dao.DAO;
import com.p4pijk.carrental.model.car.Car;

import java.util.List;

public interface CarDao extends DAO<Car> {

    List<Car> getCarsByMark(Long markId);

    List<Car> getCarsByClass(long classId);

    List<Car> getCarsByNameAZ();

    List<Car> getCarsByNameZA();

    List<Car> getCarsByCostEC();

    List<Car> getCarsByCostCE();

    List<Car> getAllAvailableCars();
}

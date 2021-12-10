package com.p4pijk.carrental.service.car;

import com.p4pijk.carrental.dao.car.CarDao;
import com.p4pijk.carrental.model.car.Car;
import com.p4pijk.carrental.model.car.CarClass;
import com.p4pijk.carrental.model.car.CarMark;
import org.apache.log4j.Logger;

import java.util.List;

public class CarService {

    private static final Logger log = Logger.getLogger(CarService.class);
    private final CarDao carDao;

    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    public List<Car> getCarsByMark(String mark) {
        log.info("Getting cars by mark with car service");
        long markId = CarMark.valueOf(mark).ordinal();
        return carDao.getCarsByMark(markId);
    }

    public List<Car> getCarsByClass(String car_class) {
        log.info("Getting cars by class with car service");
        long classId = CarClass.valueOf(car_class).ordinal();
        return carDao.getCarsByClass(classId);
    }

    public boolean addCar(Car newCar) {
        log.info("Add new car");
        return newCar != null && carDao.save(newCar).getId() != -1;
    }

    public List<Car> getAllCars() {
        log.info("Getting all cars");
        return carDao.getAll();
    }

    public List<Car> getAllAvailableCars() {
        log.info("Getting all available cars");
        return carDao.getAllAvailableCars();
    }

    public Car getCarById(long carId) {
        log.info("Getting car by id");
        return carDao.get(carId);
    }

    public void editCar(Car carById, String[] params) {
        log.info("Edit car by id with some parameters");
        carDao.update(carById, params);
    }

    public void deleteCar(Car car) {
        log.info("Processing car delete");
        carDao.delete(car);
    }

    public List<Car> getCarsByNameAZ() {
        log.info("Sorting car by name A-z");
        return carDao.getCarsByNameAZ();
    }

    public List<Car> getCarsByNameZA() {
        log.info("Sorting car by name z-A");
        return carDao.getCarsByNameZA();
    }

    public List<Car> getCarsByCostEC(){
        log.info("Sorting car by cost E-c");
        return carDao.getCarsByCostEC();
    }

    public List<Car> getCarsByCostCE(){
        log.info("Sorting car by cost c-E");
        return carDao.getCarsByCostCE();
    }
}

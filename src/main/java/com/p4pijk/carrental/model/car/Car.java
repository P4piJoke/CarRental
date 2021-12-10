package com.p4pijk.carrental.model.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Car {

    private long id;
    private String name;
    private double cost;
    private CarClass carClass;
    private CarMark carMark;
    private boolean status;

    @Override
    public String toString() {
        return name + ' ' + carMark + ' ' + carClass + ' ' + cost + '$';
    }

}

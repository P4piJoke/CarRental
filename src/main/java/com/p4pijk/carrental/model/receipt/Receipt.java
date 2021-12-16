package com.p4pijk.carrental.model.receipt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Receipt {

    private long id;
    private long userId;
    private long carId;
    private int passport;
    private RentOption rentOption;
    private String duration;
    private OrderStatus orderStatus;
    private String comment;
    private double billCost;
    private double repairBill;

    @Override
    public String toString() {
        return "№" + id + " "
                + rentOption
                + " To: " + duration
                + " Cost: " + billCost;
    }
}

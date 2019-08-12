package com.louis.model;

import lombok.*;

/**
 * Created by louislopez on 8/11/19.
 */
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Account {
    private long employeeId;
    private String accountName;
    private long accountNumber;
    private double accountAmount;
}

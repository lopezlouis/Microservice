package com.louis.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

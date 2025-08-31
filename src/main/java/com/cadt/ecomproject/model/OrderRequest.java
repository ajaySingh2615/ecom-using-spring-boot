package com.cadt.ecomproject.model;

import java.util.Map;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class OrderRequest {

    private Map<Long, Integer> productQuantities;

    private double totalAmount;
}

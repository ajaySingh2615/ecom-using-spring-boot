package com.cadt.ecomproject.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class OrderItemDTO {

    private String productName;
    private double productPrice;
    private int quantity;
}

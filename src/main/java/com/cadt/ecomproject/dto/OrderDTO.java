package com.cadt.ecomproject.dto;

import java.util.Date;
import java.util.List;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class OrderDTO {

    private Long id;
    private double totalAmount;
    private String status;
    private Date orderDate;

    private List<OrderItemDTO> orderItems;  
}

package com.cadt.ecomproject.service;

import com.cadt.ecomproject.dto.OrderDTO;
import com.cadt.ecomproject.dto.OrderItemDTO;
import com.cadt.ecomproject.model.OrderItem;
import com.cadt.ecomproject.model.Orders;
import com.cadt.ecomproject.model.Product;
import com.cadt.ecomproject.model.User;
import com.cadt.ecomproject.repo.OrderRepository;
import com.cadt.ecomproject.repo.ProductRepository;
import com.cadt.ecomproject.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderService(UserRepository userRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public OrderDTO placeOrder(Long userId, Map<Long, Integer> productQuantities, double totalAmount) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Orders orders = new Orders();
        orders.setUser(user);
        orders.setOrderDate(new Date());
        orders.setStatus("Pending");
        orders.setTotalAmount(totalAmount);

        List<OrderItem> orderItems = new ArrayList<>();
        List<OrderItemDTO> orderItemDTOs = new ArrayList<>();

        for (Map.Entry<Long, Integer> entry : productQuantities.entrySet()) {
            Product product = productRepository.findById(entry.getKey())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrders(orders);
            orderItem.setProduct(product);
            orderItem.setQuantity(entry.getValue());
            orderItems.add(orderItem);

            orderItemDTOs.add(new OrderItemDTO(product.getName(), product.getPrice(), entry.getValue()));
        }

        orders.setOrderItems(orderItems);

        Orders savedOrder = orderRepository.save(orders);

        return new OrderDTO(savedOrder.getId(), savedOrder.getTotalAmount(), savedOrder.getStatus(), savedOrder.getOrderDate(), orderItemDTOs);
    }

    public List<OrderDTO> getAllOrders() {
        List<Orders> orders = orderRepository.findAllOrdersWithUsers();
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private OrderDTO convertToDTO(Orders orders) {
        List<OrderItemDTO> orderItems = orders.getOrderItems().stream().map(item -> new OrderItemDTO(
                item.getProduct().getName(),
                item.getProduct().getPrice(),
                item.getQuantity()
        )).toList();

        return new OrderDTO(
                orders.getId(),
                orders.getTotalAmount(),
                orders.getStatus(),
                orders.getOrderDate(),
                orders.getUser() != null ? orders.getUser().getName() : "unknown",
                orders.getUser() != null ? orders.getUser().getEmail() : "unknown",
                orderItems
        );
    }
}

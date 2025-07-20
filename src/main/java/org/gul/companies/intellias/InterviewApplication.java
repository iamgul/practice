package org.gul.companies.intellias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class InterviewApplication {

    public static void main(String[] args) {
    }

}

// Implement the OrderProcessor service, which processes the list of orders and returns
// statistics on users in the form of Map<String, UserOrderSummary>.


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class User {
    Long id;
    String name;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Order {
    Long id;
    Long userId;
    Double total;
    LocalDate orderDate;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class UserOrderSummary {
    String userName;
    int orderCount;
    double totalAmount;
    LocalDate lastOrderDate;
}

interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    Optional<User> findById(Long userId);
}

interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();
}

@Service
class OrderProcessor {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    public Map<String, UserOrderSummary> summarizeOrders() {

        Map<String, UserOrderSummary> map = new HashMap<>();


        List<User> users = userRepository.findAll();
        List<Order> orders = orderRepository.findAll();

        Map<Long, List<Order>> collect = orders.stream().collect(Collectors.groupingBy(o -> o.getUserId()));
        List<Long> collect1 = collect.keySet().stream().collect(Collectors.toList());
        Long l1 = collect1.get(0);

        Stream<List<Order>> listStream = collect.entrySet().stream().map(e -> e.getValue());
        Long userId = listStream.flatMap(l -> l.stream()).map(l -> l.getUserId()).collect(Collectors.toList()).get(0);
        //User user = userRepository.findById(userId);


        Double totalSum = listStream.flatMap(l -> l.stream()).map(o -> o.total).reduce(Double::sum).get();
        List<LocalDate> collect2 = listStream.flatMap(l -> l.stream()).map(o -> o.orderDate).collect(Collectors.toList());


//		UserOrderSummary userOrderSummary = UserOrderSummary.builder().userName(user.getName()).orderCount(l1.intValue()).totalAmount(totalSum).lastOrderDate(collect2.get(0)).build();
//
//		map.put(user.getName(),userOrderSummary);

        return map;


    }


    public Map<String, UserOrderSummary> summarizeOrdersNew() {

        List<User> users = userRepository.findAll();
        List<Order> orders = orderRepository.findAll();


        //Map<UserId,List<Orders>> --> // Map<101, [order1(id, user_id,total, date), Order2(id, user_id,total, date),..]
        Map<Long, List<Order>> mapOfOrders = orders.stream().collect(Collectors.groupingBy(Order::getUserId));
        Map<String, UserOrderSummary> map = new HashMap<>();

        mapOfOrders.forEach((k, v) -> {
            Optional<User> user = userRepository.findById(k);
            String userName = "";
            if(user.isPresent()){
                userName   = user.get().getName();
            }

            int orderCount = v.size();
            double totalAmount = v.stream().map(Order::getTotal).reduce(Double::sum).get();
            LocalDate lastOrderDate = v.stream().map(Order::getOrderDate).sorted(Comparator.reverseOrder()).limit(1).toList().get(0);
            UserOrderSummary userOrderSummary = UserOrderSummary.builder().userName(userName).orderCount(orderCount).totalAmount(totalAmount).lastOrderDate(lastOrderDate).build();
            map.put(userName, userOrderSummary);
        });
        return map;

    }

    //This also has test cases written in, however this is not in this repo as there is error, it's in github link or

/*

package com.intellias.intervview;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = InterviewApplicationTests.class)
class InterviewApplicationTests {

    @InjectMocks
    private OrderProcessor orderProcessor;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private OrderRepository orderRepositoryMock;


    @Test
    void shouldSummarizeOrdersPerUser() {
        List<User> users = List.of(
                new User(1L, "Alice"),
                new User(2L, "Bob")
        );

        List<Order> orders = List.of(
                new Order(101L, 1L, 100.0, LocalDate.of(2024, 1, 10)),
                new Order(102L, 1L, 150.0, LocalDate.of(2024, 2, 15)),
                new Order(103L, 2L, 300.0, LocalDate.of(2024, 3, 5))
        );

        when(userRepositoryMock.findAll()).thenReturn(users);
        when(orderRepositoryMock.findAll()).thenReturn(orders);
        when(userRepositoryMock.findById(1L)).thenReturn(Optional.ofNullable(User.builder().id(1L).name("Alice").build()));
        when(userRepositoryMock.findById(2L)).thenReturn(Optional.ofNullable(User.builder().id(2L).name("Bob").build()));



        Map<String, UserOrderSummary> summaries = orderProcessor.summarizeOrdersNew();
        UserOrderSummary userOrderSummary1 = summaries.get("Alice");
        UserOrderSummary userOrderSummary2 = summaries.get("Bob");
        UserOrderSummary userOrderSummaryOfAlice = UserOrderSummary.builder().userName("Alice").orderCount(2).totalAmount(250.0).lastOrderDate(LocalDate.of(2024, 2, 15)).build();
        UserOrderSummary userOrderSummaryOfBob = UserOrderSummary.builder().userName("Bob").orderCount(1).totalAmount(300.0).lastOrderDate(LocalDate.of(2024, 3, 5)).build();

        assertEquals(userOrderSummary1, userOrderSummaryOfAlice);
        assertEquals(userOrderSummary2, userOrderSummaryOfBob);
        verify(orderRepositoryMock, times(1)).findAll();
        verify(userRepositoryMock, times(1)).findAll();
        verify(userRepositoryMock, times(2)).findById(anyLong());


    }

}


*/

}



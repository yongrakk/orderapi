package com.example.orderapi.service;

import com.example.orderapi.dto.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 데이터 CRUD 담당
 */
@RequiredArgsConstructor
@Repository
public class OrderRepository {

    /**
     * order 데이터를 받아와 저장
     * @param order
     */
    public void insertOrder(Order order){

        //TODO: 주문 데이터 저장
    };

    public Order selectOrderById(String orderId){
        Order order = new Order();

        //TODO: 주문 데이터 조회 - 주문 ID

        return order;
    };

    public List<Order> selectOrderList(){
        List<Order> orderList = new ArrayList<>();

        //TODO: 주문 데이터 조회 - 리스트

        return orderList;
    }


}

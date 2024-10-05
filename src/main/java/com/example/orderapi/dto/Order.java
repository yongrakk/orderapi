package com.example.orderapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 주문 데이터 DTO
 */
@Getter
@Setter
public class Order {

    private String orderId;     //주문 ID
    private String userName;    //고객명
    private Date orderDate;     //주문 날짜
    private String orderState;  //주문 상태

}
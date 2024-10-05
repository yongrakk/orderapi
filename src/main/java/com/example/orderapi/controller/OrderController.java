package com.example.orderapi.controller;

import com.example.orderapi.dto.Order;
import com.example.orderapi.service.OrderRepository;
import com.example.orderapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    /**
     * 외부 시스템 주문 데이터 요청
     */
    @GetMapping("/callOrder")
    public void callOrder(){

        Map<String, Object> resultMap = orderService.getOrder();

        if(resultMap.get("state").equals("ok")){
            if(resultMap.get("order") != null ){
                Order order = (Order) resultMap.get("data");

                //주문 데이터 저장
                orderRepository.insertOrder(order);

                log.info("API 호출 결과 : 주문 내역을 저장했습니다.");
            } else {

                log.info("API 호출 결과 : 주문 내역이 없습니다.");
            }
        } else {

            log.info("API 호출 결과 : error " , resultMap.get("message"));
        }
    }

    /**
     * 주문 ID를 통해 저장되어 있는 주문 데이터를 조회
     *
     * @param orderId : 주문 ID
     * @return
     */
    @GetMapping("/getOrderById")
    public Order getOrderById(@RequestParam(value = "orderId",required = true) String orderId){
        //저장 되어 있는 주문 데이터를 주문번호로 조회
        Order order = orderRepository.selectOrderById(orderId);

        return order;
    }

    /**
     * 저장된 주문 데이터 리스트 조회
     * @return
     */
    @GetMapping("/getOrderList")
    public List<Order> getOrderList(){
        //저장 되어 있는 주문 데이터를 리스트 형식으로 반환
        List<Order> orderList = orderRepository.selectOrderList();

        return orderList;
    }

}

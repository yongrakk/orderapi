package com.example.orderapi.service;

import com.example.orderapi.dto.Order;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 외부 시스템 데이터 연동 담당 인터페이스
 */
@RequiredArgsConstructor
@Service
public class OrderService {

    private final RestTemplate restTemplate;

    /**
     * 주문 데이터 연동 처리
     * @return resultMap : 요청사항에 대한 결과값 반환
     * (state: 정상 결과 ok 비정상 ng )
     * (data: 결과 값)
     * (message: 예외처리를 위한 메시지)
     */
    public Map<String,Object> getOrder(){

        //응답 결과 저장 Map
        Map<String, Object> resultMap = new HashMap<>();

        try {

            //외부 요청 헤더 정보가 있는 경우
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            //외부 요청 파라미터가 있는 경우
            /*
                JSONObject parameter = new JSONObject();

                parameter.put("para1","sample1");
                parameter.put("para2","sample2");
            */


            HttpEntity request = new HttpEntity(headers);

            //요청 Uri
            URI uri = UriComponentsBuilder
                    .fromUriString("http://localhost:8080")
                    .path("/order")
                    .build()
                    .toUri();

            //주문 데이터 요청
            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);

            //응답 데이터 String 형태로 저장
            String receive = response.getBody().toString();

            //String 데이터 -> JSON 형태로 파싱 처리
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(receive);

            //JSON 데이터 -> Order 데이터 저장
            Order order = new Order();
            order.setOrderId(jsonObject.get("orderId").toString());
            order.setUserName(jsonObject.get("userName").toString());
            order.setOrderState(jsonObject.get("orderState").toString());

            //date 형식 파싱
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-mm HH:mm:ss");
            order.setOrderDate(sdf.parse(jsonObject.get("orderDate").toString()));

            //결과 저장
            resultMap.put("state","ok");
            resultMap.put("data",order);
            resultMap.put("message","ok");

        }
        catch (HttpClientErrorException hce){
            resultMap.put("state","ng");
            resultMap.put("data", null);
            resultMap.put("message","4xx 에러가 발생하였습니다. : " + hce.getMessage().toString());
        }
        catch (HttpServerErrorException hse){
            resultMap.put("state","ng");
            resultMap.put("data", null);
            resultMap.put("message","5xx 에러가 발생하였습니다. : " + hse.getMessage().toString());
        }
        catch (ParseException pe){
            resultMap.put("state","ng");
            resultMap.put("data", null);
            resultMap.put("message","데이터 형식 오류 에러가 발생하였습니다. : " + pe.getMessage().toString());
        }
        catch (Exception e){
            resultMap.put("state","ng");
            resultMap.put("data", null);
            resultMap.put("message","예상치 못한 에러가 발생하였습니다. : " + e.getMessage().toString());
        }
        finally {
            return resultMap;
        }

    }

}

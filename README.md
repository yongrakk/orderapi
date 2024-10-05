## 주문 API 샘플 프로젝트(Spring Boot)

### 지원자 : 최용락 (c1.yrkk@gmail.com)
### 프로젝트명
**humanson**

### ⚙개발 환경
- JAVA 17
- **IDE** : Intellij   
- **Framework** : Spring boot 3.3.4
  
### 📌주요 기능

* DTO
  Order : 주문ID, 고객명, 주문 날짜, 주문 상태 등 속성 데이터

* Controller
  OrderController : 외부 시스템에서 주문 수집, 주문 조회, 주문 리스트 조회 를 호출하여 사용
* Service
  OrderRepository : 주문 데이터 CRUD 구현을 위한 클래스
  OrderService : 외부 시스템 데이터 연동 담당 인터페이스
* Config
  RestTemplateConfig : RestTemplate를 사용한 Http 통신을 위한 설정 

![image](https://github.com/user-attachments/assets/547f87d6-7dcd-41aa-abb1-85221ebcd97d)


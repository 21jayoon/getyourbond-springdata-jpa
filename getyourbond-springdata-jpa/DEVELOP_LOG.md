# Getting Started

### 1. application.properties 설정
application.properties를 application.yml로 바꾼 후
chap06-spring-data-jpa에서 yml 설정 값을 복사해온다.

datasource: url: jdbc:mysql://localhost:3306/menudb 를
geturbonddb 로 바꾼다.


### 2. main.html을 만들고 거기에 All Read기능을 넣어준다.
#### /* 1. Read : All */ 해결
#### 2-1. resources>templates 하위에 main 폴더 만들고 main.html 생성

#### 2-2. getyourbonds..datajpa 패키지 아래에 main> controller 패키지와 main>bonds 패키지를 만든다.

#### 2-3. controller 패키지 하위에 class MainController 만든다
MainController 클래스 선언부 위에 @Controller 어노테이션 붙이고 /main 페이지와 매핑해주는 String main 메소드 작성

#### 2-4. controller package 하위에 BondsDTO생성. 
필드만 작성하고 @Getter @Setter @ToString 어노테이션 붙인다.
(이거 bonds폴더에도 어차피 만들건데 controller 밑에 하나, bonds 밑에 하나 이렇게 따로따로 만드는게 나은 건가?)

#### 2-5. MainController main 메소드에 내용(구현부) 추가.
매개변수 Model model로 하고 List형태로 <BondsDTO>를 받아온다 -> List<BondsDTO>

#### 2-6. controller package 하위에 BondsService 생성
@Service 어노테이션 붙이고 MainController에서 private final로 MenuService 의존성 주입
-> private final

#### 2-7. MainController에서 메소드 내부 코드 한 줄 완성
    List<BondsDTO> bondsDTOList = bondsService.findAllBonds();
ALT+ENTER로 BondsService에 findAllBonds라는 메소드 생성

#### 여기서 잠깐. controller였던 패키지 이름 main으로 바꾸고 
#### 그 아래에 controller 패키지, dto패키지, entity패키지, service패키지 생성.
#### 각 폴더에 맞는 클래스 넣어줌.
    model.addAttribute("bondsList", bondsList);
그 아랫줄 코드도 넣는다.

#### 2-8. entity 패키지 아래에 Bonds 클래스 생성
그리고 @Entity(name = "MainBondEntity") 어노테이션 달아줌

chapter06-spring-data-jpa 참고해서 어노테이션과 필드, 주석 작성해줌
BondsDTO에서 필드 복사 붙여넣기 함

#### 2-9. BondsService로 돌아가서 메소드 구현부 코드 추가
    List<Bonds>bondsList =

#### 2-10. main>main 하위에 repository package 생성, BondRepository Interface 만들기
@Repository annotation 달기

extends JpaRepository<다루려는 엔티티, (해당엔티티의)Id(PK)타입> 추가 

#### 2-11. BondsService 클래스 선언부 바로 아래에서 private final로 BondsRepository 생성
    List<Bonds>bondsList = bondRepository.findAll(Sort.by("bondCode"));
    return bondsList.stream().map(bonds ->
메소드 구현부 코드 마저 채우고 내림차순으로 정렬. (기본이 내림차순이라 Sort.by("bondCode"))뒤에 nothing)

반환되어야하는 값 List가 있으니 return 코드 작성

#### 2-12. BondsService 클래스 선언부 아래에 private final로 ModelMapper 의존성 주입
ModelMapper 얘는 library에 있는 거임

#### 2-13. ModelMapper 쓰기 위해 build.gradle에  implementation 'org.modelmapper:modelmapper:3.1.1' 추가
build gradle 동기화까지 하고 getyour..datajpa패키지 아래에 config 패키지 만들고 BeanConfig 클래스 생성
선언부 위에 @Configuration annotation, 선언부 아래에 @Bean 어노테이션 달기

@Bean 어노테이션 아래에 public ModelMapper modelMapper(){} method 생성

(Notion에서 복붙해왔던 거라 똑같이 chap06-spring-data에서 복붙해오고 ModelMapper ALT+ENTER)

#### 2-14. BondsService로 돌아가서 ModelMapper import class 하고 메소드 마저 채우기
    return bondsList.stream().map(bonds -> modelMapper.map(bonds, BondsDTO.class)).toList();

#### 2-15. main.html 작성
현재 상황상(250624 19:20) 일단 채권 목록을 출력할 수 있는 단순 <table\>만 만들고 
BondType 적용하기 위한 input, '수정'&'삭제' 버튼 등은 나중에 추가하도록 함. 

table 내용은 chap06-spring-data-jpa 내 resources->templates->menu->list.html에서 갖고 옴

#### 2-16. BondsService와 MainController에 기본생성자 만들어주는 어노테이션이 없어 의존성 주입 부분에 빨간 줄 오류가 계속 뜸
각 클래스 선언부 위에 @RequiredArgsConstructor 붙이고 import class 함. >>빨간 줄 해결 더 이상 뜨지 않음 19:27

#### 2-17. main에서 bond 전체 목록 잘 뜨는지 확인
h1 태그 붙은 건 뜨는데 table은 안 뜸

<img src="D:\lecture\12_jpa\getyourbond-springdata-jpa\스크린샷 2025-06-24 192908.png" title="1차 Bondslist 출력 시도 결과" alt="h1 TAG만 뜨고 table은 안 뜬다">

#### 2-18. MainController에 안 붙였던 나머지 annotiation 붙이기
@RequestMapping("/bonds"), @Slf4j

#### 2-19. 추정 원인 발견. main.html 내의 모든 menu 관련 키워드 전부 bonds로 바꿈
e.g. tr th:each="menu: \${menuList}"를 tr th:each="bonds: ${bondsList}"로.

2차 돌리기 그래도 화이트라벨 페이지 나옴.

#### 2-20. copilot에게 왜 자꾸 404 화이트레벨 페이지 뜨냐고 물어봄.
main.html 위치가 문제일 수도 있대서 resources->templates->bonds 아래에 main.html을 넣어줌

#### 2-21. MainController의 @RequestMapping("/bonds") 삭제
    (2) 요청 URL 확인
    브라우저에서 정확히 http://localhost:8080/bonds/ 또는 
    http://localhost:8080/bonds/main 으로 접근해야 함
라고 하길래 MainController에서 @RequestMapping("/bonds")를 지우고 main.html을 원래 자리 main 패키지 아래로 돌려놓음
-> 정상적으로 채권 목록 나왔음!!

#### 2-22. view 페이지에서 bondDuration을 Date로 해서 그런지 시간:분:초.밀리초(ms)까지 나옴
copilot에게 어떻게 해야 연-월-일만 나오게 하는지 물어봄 

-> td th:text="${#dates.format(bond.bondDuration, 'yyyy-MM-dd')}" 로 Thymeleaf 문법을 이용해 table 속성을 바꾸라고 함.

-> 안 됨. main.html에서 td th:text="${bonds.bondDuration}"로 바꾸고
그냥 BondsDTO와 Bonds (entity class)에서  private String bondDuration; 이렇게 String 값으로 변환함

-> String으로 바꾸니까 원하던 대로 yyyy-mm-dd 형식으로 bondDuration이 출력되긴 함.

근데 위에서 쓴 글 자세히 읽어보니 bonds.bondDuration이라고 해야하는데 bond.bondDuration이라고 적어줌.

다시 private Date bondDuration; 으로 바꾸고 main.html에서 {}안에 #dates.format(bonds.bondDuration, 'yyyy-MM-dd') 라고 쓰니
String으로 바꾸지 않아도 원하던 대로 연-월-일 형태로 리스트 출력됨.

#### 2-23. table border="1" 굵기1짜리 표 border 넣어줌 
!! 20250624 20:10 List of All bonds 출력 완료

### 3. Read All 표 왼쪽 상단에 BondType 1혹은2로 채권리스트 출력할 수 있게 input 박스 만든다
/* 2. Read : By BondType*/
    
### 4. Read All 표 오른쪽 하단에 몇 개인지 확인할 수 있게 "총 00개"라고 띄워준다.
/* 3. Read : Count the number of bonds in the list */

### 5. Real All 표 오른쪽 상단에 채권 추가 버튼을 만든다.
/* 4. Create */

### 6. 목록 내 각 채권 옆에 '수정' 버튼 나올 수 있게 만든다
/* 5. Update */

### 7. 목록 내 각 채권 옆에 (수정버튼 옆에) '삭제' 버튼 나올 수 있게 만든다.
/* 6. Delete */


* [Official Gradle documentation](https://docs.gradle.org)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.5.3/gradle-plugin/packaging-oci-image.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.5.3/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.5.3/reference/using/devtools.html)
* [Thymeleaf](https://docs.spring.io/spring-boot/3.5.3/reference/web/servlet.html#web.servlet.spring-mvc.template-engines)
* [Spring Web](https://docs.spring.io/spring-boot/3.5.3/reference/web/servlet.html)

### Guides
The following guides illustrate how to use some features concretely:
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links
These additional references should also help you:
* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)


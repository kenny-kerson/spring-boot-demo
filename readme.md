# Spring Boot Demo
- **개요**
   * Spring **Web MVC** / Spring Data **JPA** / Spring **Security** / Spring **Test** / Spring **Core** / Spring Boot 등을 학습하기 위한, 코드를 작성하는 프로젝트
- **목적**
   * 각 기술별 주요 코드들을 샘플로 참고할 수 있도록, **commit id를 리스트업** 하는것이 목적

# Index
- [Specification](#specification)
- [Project Structure](#project-structure)
- [Modules](#modules)
   * [spring-security](#spring-security)
   * [spring-junit5-mockito-test](#-spring-junit5-mockito-test)
   * [spring-web-mvc](#spring-web-mvc)
   * [spring-data-jpa](#spring-data-jpa)
   * [spring-core](#spring-core)
   * [spring-boot](#spring-boot)

# Specification
( 모든 모듈에서 공통적으로 사용하는 스펙 )
- Language : **Java**
- JDK : **OpenJDK 11**
- Framework : **Spring Boot 2.3.4**
   * spring-core : 5.2.9
   * spring-security-core : 5.3.4
- External Library
   * lombok : getter, setter, constructor, builder 등 반복적인 코드 제거를 위해 사용 

# Project Structure
1. **멀티 모듈** 프로젝트로 구성
2. 각 **주요 주제별**( WebMVC, Security 등 ) 코드를 하위 모듈로 구성
3. 모듈 리스트
    * spring-security
    * spring-junit5-mockito-test
    * spring-web-mvc
    * spring-data-jpa
    * spring-core
    * spring-boot

# Modules

## # spring-security
### Spec
- DBMS
  * RDB : **H2** inmemory DB
  * ORM : **JPA**
- Security : Spring Security
- Template Engine : **Thymeleaf**
- Test
   * spring-security-test
### Commit List
- init module : `a8ee4f23`
- 1.Form 인증
   * 인메모리 유저 추가 : `b4cb5eb7`
   * DAO 유저 추가 : `296f0985`
   * PasswordEncoder 적용( 디폴트 : BcryptPasswordEncoder ) : `91553216`
   * 테스트코드 추가 : `919df97c`
- 2.Spring Security 아키텍쳐
   * TBD 
   
## # spring-junit5-mockito-test
### Spec
- TBD
### Commit List
- init module : cdd37fa2
- 1.JUnit5
   * 시작 & 테스트이름 표시 : `7f949d58`
   * Assertion & AssertJ : `47a37934`
   * 조건에 따라 테스트실행(Assumption) : `7d29b67e`
   * 태깅과 필터링 : 
   * 커스텀 태그 : 
   * 테스트 반복하기() : 
   * ✔️ 테스트 반복하기( @ParameterizedTest ) : 
- 2.Mockito
   * TBD
- 3.TBD

## # spring-web-mvc
### Spec
- DBMS
  * RDB : H2 in-memory DB
  * ORM : JPA
- Template Engine : Thymeleaf

### Commit List
- init module : 0ea6fa57
- TBD

## # spring-data-jpa
### Spec
- TBD
### Commit List
- TBD

## # spring-core
### Spec
- TBD
### Commit List
- TBD

## # spring-boot
### Spec
- TBD
### Commit List
- TBD
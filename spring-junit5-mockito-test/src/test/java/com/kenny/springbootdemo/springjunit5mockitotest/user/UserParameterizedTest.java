package com.kenny.springbootdemo.springjunit5mockitotest.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class UserParameterizedTest {

    @ParameterizedTest(name = "{displayName} : {index} / {argumentsWithNames}")
    @ValueSource(strings = {
            "테스트를",
            "ValueSource로",
            "반복할 수 있다."
    })
    @DisplayName("테스트 반복하기 : @ParameterizedTest & @ValueSource")
    @Order(6)
    void parameterizedTest1( String value ) {
        System.out.println( "__KENNY__ value : " + value );
    }


    @ParameterizedTest(name = "{displayName} : {index} / {argumentsWithNames}")
    @NullAndEmptySource
    @ValueSource(strings = {"널과 공백을 포함해서 테스트"})
    @DisplayName("테스트 반복하기 : @ParameterizedTest & @NullAndEmptySource")
    @Order(5)
    void parameterizedTest2( String value ) {
        System.out.println( "__KENNY__ value : " + value );
    }


    @ParameterizedTest(name = "{displayName} : {index} / {argumentsWithNames}")
    @ValueSource(ints = {
            10,
            20,
            30
    })
    @DisplayName("테스트 반복하기 : 암묵적인 형변환")
    @Order(4)
    void parameterizedTest3( Integer value ) {
        System.out.println( "__KENNY__ value : " + value );
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "10",
            "20",
            "30"
    })
    @DisplayName("테스트 반복하기 : 커스텀 Converter 사용")
    @Order(3)
    void parameterizedTest4( @ConvertWith(UserCustomConverter.class) User user ) {
        System.out.println( "__KENNY__ user : " + user );
    }

    /**
     * String 입력값을 받아서, User 객체 생성시 Integer형의 age로 셋팅해주는 Converter
     * - 한개의 인자값을 받아서 하나의 객체로 리턴
     */
    static class UserCustomConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            return User.builder()
                    .age(Integer.parseInt((String) source))
                    .build();
        }
    }


    @ParameterizedTest
    @CsvSource(value = {
            "1, 'kenny'",
            "2, 'bella'",
            "3, 'dana'"
    })
    @DisplayName("테스트 반복하기 : @CsvSource")
    @Order(2)
    void parameterizedTest5( Integer value1, String value2 ) {
        System.out.println( "__KENNY__ value1 : " + value1 );
        System.out.println( "__KENNY__ value2 : " + value2 );
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 'kenny'", "2, 'bella'", "3, 'dana'"})
    @DisplayName("테스트 반복하기 : @CsvSource & Custom Aggregator 사용")
    @Order(1)
    void parameterizedTest6( @AggregateWith(UserCustomAggregator.class) User user ) {
        System.out.println( "__KENNY__ user : " + user);
    }


    /**
     * Csv 인자값을 받아서, User 객체 생성시 id & username으로 매핑해주는 Aggregator
     * - 다수의 인자값을 받아서 하나의 객체로 리턴
     */
    static class UserCustomAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            return User.builder()
                    .id(accessor.getInteger(0))
                    .username(accessor.getString(1))
                    .build();
        }
    }
}

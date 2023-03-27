package com.kenny.gsaccessingdatar2dbc;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration
public class R2DBCConfig {

    // schema.sql을 셋팅하지 않아도, 어플리케이션이 구동되면서, 자동으로 schema.sql을 인식해서 실행한다.
    // 오히려 아래의 빈으로 등록하면, 테이블을 2번 생성하려고 해서 오류가 발생한다.
//    @Bean
//    public ConnectionFactoryInitializer initializer( final ConnectionFactory connectionFactory ) {
//        final ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
//        initializer.setConnectionFactory(connectionFactory);
//        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
//        return initializer;
//    }
}

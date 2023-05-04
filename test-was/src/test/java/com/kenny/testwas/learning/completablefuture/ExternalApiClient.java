package com.kenny.testwas.learning.completablefuture;

import org.springframework.stereotype.Component;

public class ExternalApiClient {

    public User getUserInfo( final String id ) {
        // 외부연동을 위한 http client에서 수행하는 로직이라고 가정한다.
        // 특정시간( 5초 ) 이후에 응답이 온다고 가정한다.

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new User( "kenny", "고케니" );
    }
}

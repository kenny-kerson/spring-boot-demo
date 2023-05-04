package com.kenny.testwas.learning.completablefuture;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExternalApiClient {

    // 외부연동을 위한 http client에서 수행하는 로직이라고 가정한다.
    // 특정시간( time ) 이후에 응답이 온다고 가정한다.
    public User getUserInfo( final String id, final String time, final String status ) {
        log.warn("# ExternalApiClient.getUserInfo() START!!");

        if ( !"ok".equals(status) ) {
            throw new RuntimeException();
        }

        try {
            Thread.sleep(Long.parseLong(time));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new User( "kenny", "고케니" );
    }
}

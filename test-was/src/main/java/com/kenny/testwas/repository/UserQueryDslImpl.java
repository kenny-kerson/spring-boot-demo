package com.kenny.testwas.repository;

import com.kenny.testwas.domain.QUser;
import com.kenny.testwas.domain.QUserDetail;
import com.kenny.testwas.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.kenny.testwas.domain.QUser.user;
import static com.kenny.testwas.domain.QUserDetail.userDetail;

@Repository
@Slf4j
public class UserQueryDslImpl extends QuerydslRepositorySupport {
    public UserQueryDslImpl() {
        super(User.class);
    }

    public void join() {
        final List<Object> fetchResult = from(user, userDetail)
                .where(user.id.eq(userDetail.userId))
                .fetch()
        ;

        log.debug("fetchResult : {}", fetchResult);
    }
}

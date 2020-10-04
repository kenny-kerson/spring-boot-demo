package com.kenny.springbootdemo.springjunit5mockitotest.member;

import com.kenny.springbootdemo.springjunit5mockitotest.user.User;
import org.springframework.stereotype.Service;

@Service
public interface MemberServiceManager {
    User getMemberInfo( Integer id );
}

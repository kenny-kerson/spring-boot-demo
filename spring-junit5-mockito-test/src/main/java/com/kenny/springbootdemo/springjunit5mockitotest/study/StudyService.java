package com.kenny.springbootdemo.springjunit5mockitotest.study;

import com.kenny.springbootdemo.springjunit5mockitotest.member.MemberServiceManager;
import com.kenny.springbootdemo.springjunit5mockitotest.user.User;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;
    private final MemberServiceManager memberServiceManager;

    public StudyInfo.Out getStudyInfo( final Integer id ) {

        final Study study = studyRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        final User member = memberServiceManager.getMemberInfo(study.getMemberId());

        return StudyInfo.Out.builder()
                .id( study.getId() )
                .name( study.getName() )
                .startDateTime( study.getStartDateTime() )
                .endDateTime( study.getEndDateTime() )
                .memberId( member.getId() )
                .memberName( member.getUsername() )
                .build();
    }
}

package com.kenny.springbootdemo.springjunit5mockitotest.study;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;

    @GetMapping("/study/{id}")
    public StudyInfo.Out getStudyInfo(@PathVariable Integer id) {
        return studyService.getStudyInfo(id);
    }
}

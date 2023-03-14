package com.kenny.testwas.company.ui;

import com.kenny.testwas.company.ui.dto.StandardRequestDto;
import com.kenny.testwas.company.ui.dto.StandardResponseDto;
import com.kenny.testwas.company.ui.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    @PostMapping("/api/lookup/user_info")
    public StandardResponseDto<UserDto.Out> lookUpUserInfo(@RequestBody final StandardRequestDto<UserDto.In> input) {
        log.debug( "input : {}", input );

        return StandardResponseDto.<UserDto.Out>builder()
                .status("OK")
                .cstno(input.cstno())
                .guid(input.guid())
                .dataBody(UserDto.Out.builder()
                        .id(input.dataBody().id())
                        .name("고케니")
                        .teamId("1")
                        .build()
                )
                .build();

//        return StandardResponseDto.<UserDto.Out>builder()
//                .status("OK")
//                .cstno(input.getCstno())
//                .guid(input.getGuid())
//                .dataBody(UserDto.Out.builder()
////                        .id(input.getDataBody().getId())
//                        .name("고케니")
//                        .teamId("1")
//                        .build()
//                )
//                .build();
    }
}

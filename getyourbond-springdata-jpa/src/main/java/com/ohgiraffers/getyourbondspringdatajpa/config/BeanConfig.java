package com.ohgiraffers.getyourbondspringdatajpa.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public ModelMapper modelMapper(){
        /* setter 메소드 미사용 시 ModelMapper가
         * private 필드에 접근할 수 있도록 설정 */
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldAccessLevel(
                        org.modelmapper.config.Configuration.AccessLevel.PRIVATE
                )
                //.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                // ->PRIVATE한 필드도 Access 가능하도록 만듦
                .setFieldMatchingEnabled(true); // 그리고 "필드 이름이 같으면 자동으로 매칭하는 설정"을 넣어줌
        return modelMapper;
    }
}

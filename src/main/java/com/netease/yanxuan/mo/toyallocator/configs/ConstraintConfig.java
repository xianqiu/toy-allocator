package com.netease.yanxuan.mo.toyallocator.configs;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ConstraintConfig {

    @Value("${constraint.age.min}")
    private Integer minAge;
    @Value("${constraint.age.max}")
    private Integer maxAge;

}

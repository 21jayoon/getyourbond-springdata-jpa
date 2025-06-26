package com.ohgiraffers.getyourbondspringdatajpa.main.main.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
/*toString 유의점 : 연관관계 매핑을 양방향으로 맺고 있는 경우
   toString의 중복사용(양 entity 내에 있는 toString 간의 서로 호출)이 발생할 수 있다
 따라서 @ToString : 사용 가능하나 연관 관계 매핑 필드는 제거하고 사용해야 한다. */
public class BondsDTO {
    private int bondCode;
    private String bondName;
    private int bondType;
    private double bondYield;
    private Date bondDuration;
}

package com.ohgiraffers.getyourbondspringdatajpa.main.main.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "MainBondEntity")
@Table(name = "tbl_bonds")
@Getter
/* @Setter 지양(Setter 메소드 작성 지양) -> 객체를 언제든지 변경할 수 있으면 객체의 안전성 보장 X
 * 값 변경이 필요한 경우에는 해당 기능을 위한 메소드를 별도로 생성 */
//@NoArgsConstructor : 기본 생성자를 만들어주세요
@NoArgsConstructor(access = AccessLevel.PROTECTED) //@AllArgsConstructor 지양
// access = AccessLevel.PROTECTED  -> 상속받은 클래스 내에서만 사용 가능
public class Bonds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bondCode;

    private String bondName;
    private int bondType;
    private double bondYield;
    private Date bondDuration;
}

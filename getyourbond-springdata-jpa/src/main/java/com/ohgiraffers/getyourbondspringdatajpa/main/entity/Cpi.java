package com.ohgiraffers.getyourbondspringdatajpa.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_cpi")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cpi {
    @Id
    @Column(name = "rate_id", length = 10)
    private String rateId;

    @Column(name = "cpi", nullable = false)
    private Double cpi;
}
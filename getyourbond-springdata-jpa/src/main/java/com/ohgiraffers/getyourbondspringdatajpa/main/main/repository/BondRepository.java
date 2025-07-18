package com.ohgiraffers.getyourbondspringdatajpa.main.main.repository;

import com.ohgiraffers.getyourbondspringdatajpa.main.main.entity.Bonds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BondRepository extends JpaRepository<Bonds, Integer> {
}

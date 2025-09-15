package com.ohgiraffers.getyourbondspringdatajpa.main.repository;

import com.ohgiraffers.getyourbondspringdatajpa.main.entity.Bonds;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BondRepository extends JpaRepository<Bonds, Integer> {
    List<Bonds> findAllByBondCode(int bondCode);

    List<Bonds> findBondsByBondName(String bondName);

    List<Bonds> findAllByBondType(int bondType, Sort sort);

    @Query("SELECT b FROM Bonds b JOIN FETCH b.bondType WHERE b.bondYield BETWEEN :minYield AND :maxYield")
    List<Bonds> findBondsByBondYieldBetween(@Param("minYield") Double minYield, @Param("maxYield") Double maxYield);
    // @Param은 클라이언트로부터 받은 값을 이용한다는 것을 의미한다.
}

package com.ohgiraffers.getyourbondspringdatajpa.main.main.service;

import com.ohgiraffers.getyourbondspringdatajpa.main.main.dto.BondsDTO;
import com.ohgiraffers.getyourbondspringdatajpa.main.main.entity.Bonds;
import com.ohgiraffers.getyourbondspringdatajpa.main.main.repository.BondRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BondsService {
    private final BondRepository bondRepository;
    private final ModelMapper modelMapper;

    public List<BondsDTO> findAllBonds() {
        List<Bonds> bondsList = bondRepository.findAll(Sort.by("bondCode"));
        return bondsList.stream().map(bonds -> modelMapper.map(bonds, BondsDTO.class)).toList();
    }
}

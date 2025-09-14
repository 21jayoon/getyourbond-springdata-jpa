package com.ohgiraffers.getyourbondspringdatajpa.main.controller;

import com.ohgiraffers.getyourbondspringdatajpa.main.dto.BondsDTO;
import com.ohgiraffers.getyourbondspringdatajpa.main.service.BondsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j // Logger 객체 선언을 위한 어노테이션
public class MainController {
    private final BondsService bondsService;

    @GetMapping(value = {"/", "/main"})
    public String main(Model model){
        List<BondsDTO> bondsList = bondsService.findAllBonds();
        model.addAttribute("bondsList", bondsList);

        return"main/main";
    }
}

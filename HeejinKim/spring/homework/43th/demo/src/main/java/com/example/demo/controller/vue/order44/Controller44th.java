package com.example.demo.controller.vue.order44;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/44th/vuePlay")
public class Controller44th {

    @GetMapping("/dataBind")
    public String getDataBind(){

        log.info("getDataBind()");

        return "/vue/44th/dataBind";
    }

    @GetMapping("/varDataBind")
    public String getVarDataBind(){

        log.info("getVarDataBind()");

        return "/vue/44th/varDataBind";
    }

    @GetMapping("/clickEvent")
    public String getClickEvent(){

        log.info("getClickEvent()");

        return "/vue/44th/clickEvent";
    }

    @GetMapping("/rolePlayGame")
    public String getRolePlayGame(){

        log.info("getRolePlayGame()");

        return "/vue/44th/rolePlayGame";
    }


}

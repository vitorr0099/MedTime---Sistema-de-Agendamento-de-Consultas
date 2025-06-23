package com.medTime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String home() {
        return "Trabalho/especialidades";
    }

    @GetMapping("/especialidades")
    public String especialidades() {
        return "Trabalho/especialidades";
    }

    @GetMapping("/cardiologista")
    public String cardiologista() {
        return "Trabalho/medicos";
    }

    @GetMapping("/neurologista")
    public String neurologista() {
        return "Trabalho/medicos";
    }

    @GetMapping("/ginecologista")
    public String ginecologista() {
        return "Trabalho/medicos";
    }

    @GetMapping("/ortopedia")
    public String ortopedia() {
        return "Trabalho/medicos";
    }

    @GetMapping("/calendario")
    public String calendario() {
        return "Trabalho/calendario";
    }

    @GetMapping("/meus-agendamentos")
    public String meusAgendamentos() {
        return "Trabalho/meus-agendamentos";
    }
} 
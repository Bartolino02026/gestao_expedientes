package mz.co.gestao.gestao_expedientes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String dashboard() {
        return "dashboard";
    }
}
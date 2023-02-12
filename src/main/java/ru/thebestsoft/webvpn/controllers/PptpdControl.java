package ru.thebestsoft.webvpn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.thebestsoft.webvpn.services.ControlService.PptpdService;

@Controller
@RequestMapping("/pptpd/")
public class PptpdControl {

    @GetMapping("/start/")
    public String doGetStart() {
        PptpdService pptpd = new PptpdService();
        pptpd.doStart();
        return "redirect:/";
    }

    @GetMapping("/stop/")
    public String doGetStop() {
        PptpdService pptpd = new PptpdService();
        pptpd.doStop();
        return "redirect:/";
    }

    @GetMapping("/restart/")
    public String doGetRestart() {
        PptpdService pptpd = new PptpdService();
        pptpd.doRestart();
        return "redirect:/";
    }
}

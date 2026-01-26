package br.com.terracota.infra.api.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
@RequestMapping("/api/v1/status")
public class StatusController {

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String status() {
        return "OK";
    }
}

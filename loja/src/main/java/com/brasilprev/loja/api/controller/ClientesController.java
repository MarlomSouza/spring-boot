package com.brasilprev.loja.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api/clientes")
public class ClientesController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String GetClientes() {
        return "oi";
    }
}
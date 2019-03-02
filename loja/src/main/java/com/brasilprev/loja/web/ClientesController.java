package com.brasilprev.loja.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String GetClientes() {
        return "oi";
    }
}
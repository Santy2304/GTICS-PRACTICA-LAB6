package com.example.practicalb6gtics.Contoller;

import com.example.practicalb6gtics.Repository.DispositivoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    final DispositivoRepository dispositivoRepository;
    public AdminController(DispositivoRepository dispositivoRepository) {
        this.dispositivoRepository = dispositivoRepository;
    }
    @GetMapping(value = { "/listaDispositivos",""})
    public String listarDispositivos(Model model) {

        model.addAttribute("listaDispositivos",dispositivoRepository.findAll());

        return "Admin/ListaDispositivos";
    }

}

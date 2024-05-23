package com.example.practicalb6gtics.Contoller;

import com.example.practicalb6gtics.Entity.Dispositivo;
import com.example.practicalb6gtics.Repository.DispositivoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    final DispositivoRepository dispositivoRepository;
    public AdminController(DispositivoRepository dispositivoRepository) {
        this.dispositivoRepository = dispositivoRepository;
    }
    @GetMapping(value = { "/listaDispositivos",""})
    public String listarDispositivos(Model model) {

        model.addAttribute("listaDispositivos",dispositivoRepository.listarDispositivosValidos());

        return "ListaDispositivos";
    }

    @GetMapping(value = { "/edit"})
    public String editarDispositivos(@ModelAttribute("dispositivo")Dispositivo dispositivo, Model model, @RequestParam("id") int idDispositivo) {

        Optional<Dispositivo> optionalDispositivo = dispositivoRepository.findById(idDispositivo);

        if (optionalDispositivo.isPresent()) {
            dispositivo = optionalDispositivo.get();
            model.addAttribute("dispositivo", dispositivo);
            return "Admin/newFrm";
        } else {
            return "redirect:/listaDispositivos";
        }

    }

    @GetMapping(value = { "/new"})
    public String crearDispositivos(@ModelAttribute("dispositivo")Dispositivo dispositivo) {
        return "Admin/newFrm";

    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute ("dispositivo") @Valid Dispositivo dispositivo, BindingResult bindingResult, Model model, RedirectAttributes attr) {

        if(bindingResult.hasErrors()){

            return "Admin/newFrm";

        }else{
            if (dispositivo.getIdDispositivos()==null) {
                attr.addFlashAttribute("msg", "Dispositivo "+dispositivo.getNombre() + " creado exitosamente");
                dispositivo.setHabilitado(true);

            } else {
                attr.addFlashAttribute("msg", "Dispositivo "+ dispositivo.getNombre() + " actualizado exitosamente");

            }
            dispositivoRepository.save(dispositivo);


            return "redirect:/admin/listaDispositivos";
        }

    }

    @GetMapping("/delete")
    public String borrarTransportista(@RequestParam("id") int id,
                                      RedirectAttributes attr) {

        Optional<Dispositivo> optionalDispositivo = dispositivoRepository.findById(id);

        if (optionalDispositivo.isPresent()) {
            dispositivoRepository.eliminarDispositivoPorId(id);
            attr.addFlashAttribute("msg", "Dispositivo borrado exitosamente");
        }
        return "redirect:/admin/listaDispositivos";

    }

}

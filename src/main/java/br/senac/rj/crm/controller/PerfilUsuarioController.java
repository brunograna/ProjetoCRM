package br.senac.rj.crm.controller;

import br.senac.rj.crm.domain.PerfilUsuario;
import br.senac.rj.crm.repository.PerfilUsuarioRepository;
import br.senac.rj.crm.service.PerfilUsuarioService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/perfis-usuarios")
public class PerfilUsuarioController {

    @Autowired
    private PerfilUsuarioService perfilUsuarioService;

    @GetMapping
    public ModelAndView listAll(){
        ModelAndView mv = new ModelAndView("/auth/perfis-usuarios/listarPerfisUsuarios");
        mv.addObject("perfisUsuarios", perfilUsuarioService.findAll());
        return mv;
    }

    @PostMapping
    public ModelAndView save(PerfilUsuario perfilUsuario){
        ModelAndView mv = new ModelAndView("redirect:/perfis-usuarios");
        try{
            perfilUsuarioService.save(perfilUsuario);
        }catch (Exception e){
            mv = new ModelAndView("redirect:/perfis-usuarios/adicionar?error");
        }
        return mv;
    }

    @GetMapping("/adicionar")
    public ModelAndView add(){
        ModelAndView mv = new ModelAndView("/auth/perfis-usuarios/cadastrarPerfisUsuarios");
        mv.addObject("perfilUsuario", new PerfilUsuario());
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("/auth/perfis-usuarios/alterarPerfisUsuarios");
        try {
            mv.addObject("perfilUsuario", perfilUsuarioService.findById(id));
        } catch (ObjectNotFoundException e) {
            mv = new ModelAndView("redirect:/perfis-usuarios?error");
        }
        return mv;
    }


    @PostMapping("/edit")
    public ModelAndView saveEdit(PerfilUsuario perfilUsuario){
        ModelAndView mv = new ModelAndView("redirect:/perfis-usuarios/");
        try {
            perfilUsuarioService.update(perfilUsuario);
        } catch (ObjectNotFoundException e) {
            mv = new ModelAndView("redirect:/perfis-usuarios?error");
        }
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("redirect:/perfis-usuarios");
        perfilUsuarioService.softDelete(id);
        return mv;
    }



}

package br.senac.rj.crm.controller;

import br.senac.rj.crm.domain.Usuario;
import br.senac.rj.crm.service.PerfilUsuarioService;
import br.senac.rj.crm.service.UsuarioService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerfilUsuarioService perfilUsuarioService;

    @GetMapping
    public ModelAndView listAll(){
        ModelAndView mv = new ModelAndView("/auth/usuarios/listarUsuarios");
        mv.addObject("usuarios", usuarioService.findAll());
        return mv;
    }

    @PostMapping
    public ModelAndView save(@Valid Usuario usuario, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.out.println("Has errors");
            return new ModelAndView("/auth/usuarios/cadastrarUsuarios");
        }

        ModelAndView mv = new ModelAndView("redirect:/usuarios");
        try{
            usuarioService.save(usuario);
        }catch (Exception e){
            mv = new ModelAndView("redirect:/usuarios/adicionar?error");
        }
        return mv;
    }

    @GetMapping("/adicionar")
    public ModelAndView add(){
        ModelAndView mv = new ModelAndView("/auth/usuarios/cadastrarUsuarios");
        mv.addObject("usuario", new Usuario());
        mv.addObject("perfisUsuarios", perfilUsuarioService.findAllActive());
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("/auth/usuarios/alterarUsuarios");
        try {
            Usuario userRetrieved = usuarioService.findById(id);
            userRetrieved.setUsuarioSenha("");
            mv.addObject("usuario", userRetrieved);
            mv.addObject("perfisUsuarios", perfilUsuarioService.findAll());
        } catch (ObjectNotFoundException e) {
            mv = new ModelAndView("redirect:/usuarios?error");
        }
        return mv;
    }


    @PostMapping("/edit")
    public ModelAndView saveEdit(@Valid Usuario usuario, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ModelAndView("/auth/usuarios/alterarUsuarios");
        }

        ModelAndView mv = new ModelAndView("redirect:/usuarios/");
        try {
            usuarioService.update(usuario);
        } catch (ObjectNotFoundException e) {
            mv = new ModelAndView("redirect:/usuarios?error");
        }
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("redirect:/usuarios");
        usuarioService.softDelete(id);
        return mv;
    }
}

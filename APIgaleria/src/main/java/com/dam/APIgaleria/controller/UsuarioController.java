package com.dam.APIgaleria.controller;

import com.dam.APIgaleria.model.Usuario;
import com.dam.APIgaleria.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService service;
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }
    @GetMapping
    public List<Usuario> listar() {
        return service.listar();
    }
    @PostMapping
    public Usuario guardar(Usuario usuario) {
        return service.guardar(usuario);
    }
    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable Long id) {
        return service.buscarPorId(id).orElse(null);
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}

package com.dam.APIgaleria.service;


import com.dam.APIgaleria.model.Usuario;
import com.dam.APIgaleria.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepo;
    public UsuarioService(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public List<Usuario> listar() {
        return usuarioRepo.findAll();
    }

    public Usuario guardar(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepo.findById(id);
    }
    public void eliminar(Long id) {
        usuarioRepo.deleteById(id);
    }
}

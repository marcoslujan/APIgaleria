package com.dam.APIgaleria.controller;

import com.dam.APIgaleria.model.Alienigena;
import com.dam.APIgaleria.service.AlienigenaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alienigena")
@CrossOrigin(origins = "*")
public class AlienigenaController {
    private final AlienigenaService service;
    public AlienigenaController(AlienigenaService service) {this.service = service;}
    @GetMapping
    public List<Alienigena> listar() {return service.listar();}
    @PostMapping
    public Alienigena guardar(@RequestBody Alienigena alienigena){return service.guardar(alienigena);}
    @GetMapping("/{id}")
    public Optional<Alienigena> buscar(@PathVariable Long id){return service.buscarPorId(id);}
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {service.eliminar(id);}


}



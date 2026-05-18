package com.dam.APIgaleria.service;
import com.dam.APIgaleria.model.Alienigena;
import com.dam.APIgaleria.repository.AlienigenaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlienigenaService {
    private final AlienigenaRepository alienigenaRepo;

    public AlienigenaService(AlienigenaRepository alienigenaRepo){this.alienigenaRepo = alienigenaRepo; }

    public List<Alienigena> listar() {return alienigenaRepo.findAll();}

    public Alienigena guardar(Alienigena alienigena) {return alienigenaRepo.save(alienigena);}

    public Optional<Alienigena> buscarPorId(Long id) {return alienigenaRepo.findById(id);}
    public void eliminar(Long id) {alienigenaRepo.deleteById(id);}
}

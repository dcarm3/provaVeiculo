package com.example.prova.acessorio.service;

import com.example.prova.acessorio.model.Acessorio;
import com.example.prova.acessorio.repository.AcessorioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AcessorioService {
    private final AcessorioRepository repositorioAcessorio;

    public AcessorioService(AcessorioRepository repositorioAcessorio) {
        this.repositorioAcessorio = repositorioAcessorio;
    }

    //------------------------------CRUD------------------------------
    public List<Acessorio> listarVeiculos() { return repositorioAcessorio.findAll(); }

    public Optional<Acessorio> buscarAcessorioPorId(Long id) { return repositorioAcessorio.findById(id); }

    public boolean deletarAcessorio(Long id) {
        return repositorioAcessorio.findById(id).map(acessorio -> {
            repositorioAcessorio.delete(acessorio);
            return true;
        }).orElse(false);
    }
}

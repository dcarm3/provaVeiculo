package com.example.prova.acessorio.controller;

import com.example.prova.acessorio.model.Acessorio;
import com.example.prova.acessorio.service.AcessorioService;
import com.example.prova.veiculo.model.Veiculo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acessorios")
public class AcessorioController {
    private final AcessorioService acessorioService;

    public AcessorioController(AcessorioService acessorioService) {
        this.acessorioService = acessorioService;
    }

    //------------------------------CRUD------------------------------
    @GetMapping //get acessorio
    public List<Acessorio> listarAcessorios() {
        return acessorioService.listarVeiculos();
    }

    @GetMapping("/{id}") //get acessorio por id
    public ResponseEntity<Acessorio> buscarAcessorioPorId(@PathVariable Long id) {
        return acessorioService.buscarAcessorioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping //post acessorio
    public Acessorio criar(@RequestBody Acessorio acessorio) {
        return acessorioService.criarAcessorio(acessorio);
    }

    @PutMapping("/{id}") //update acessorio pelo id
    public ResponseEntity<Acessorio> atualizar(@PathVariable Long id, @RequestBody Acessorio acessorio) {
        return acessorioService.atualizarAcessorio(id, acessorio)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}") //deletar acessorio pelo id
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (acessorioService.deletarAcessorio(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

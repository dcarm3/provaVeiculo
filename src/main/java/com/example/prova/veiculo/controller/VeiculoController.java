package com.example.prova.veiculo.controller;

import com.example.prova.acessorio.model.Acessorio;
import com.example.prova.veiculo.model.Veiculo;
import com.example.prova.veiculo.service.VeiculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    //------------------------------CRUD------------------------------
    @GetMapping //get veiculo
    public List<Veiculo> listarVeiculos() {
        return veiculoService.listarVeiculos();
    }

    @GetMapping("/{id}") //get veiculo por id
    public ResponseEntity<Veiculo> buscarPorId(@PathVariable Long id) {
        return veiculoService.buscarVeiculoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping //post veiculo
    public Veiculo criar(@RequestBody Veiculo user) {
        return veiculoService.criarVeiculo(user);
    }

    @PutMapping("/{id}") //jupudate veiculo pelo id
    public ResponseEntity<Veiculo> atualizar(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        return veiculoService.atualizarVeiculo(id, veiculo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") //deletar veiculo pelo id
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (veiculoService.deletarVeiculo(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //-----------------Adicionar-e-remover-acessorios-do-veículo------------------
    @PostMapping("/{idVeiculo}/acessorios") //adiciona
    public ResponseEntity<Veiculo> adicionarAcessorio(@PathVariable Long idVeiculo, @RequestBody Acessorio acessorio) {
        return ResponseEntity.ok(veiculoService.adicionarAcessorio(idVeiculo, acessorio));
    }

    @DeleteMapping("/{idVeiculo}/acessorios/{acessorioId}") //remove
    public ResponseEntity<Veiculo> removerAcessorio(@PathVariable Long idVeiculo, @PathVariable Long acessorioId) {
        return ResponseEntity.ok(veiculoService.removerAcessorio(idVeiculo, acessorioId));
    }
}

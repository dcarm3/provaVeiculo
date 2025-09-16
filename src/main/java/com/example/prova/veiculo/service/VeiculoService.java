package com.example.prova.veiculo.service;

import com.example.prova.acessorio.model.Acessorio;
import com.example.prova.acessorio.repository.AcessorioRepository;
import org.springframework.stereotype.Service;
import com.example.prova.veiculo.repository.VeiculoRepository;
import com.example.prova.veiculo.model.Veiculo;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {
    private final VeiculoRepository repositorioVeiculo;
    private final AcessorioRepository repositorioAcessorio;

    public VeiculoService(VeiculoRepository veiculoRepository, AcessorioRepository acessorioRepository) {
        this.repositorioVeiculo = veiculoRepository;
        this.repositorioAcessorio = acessorioRepository;
    }

    //------------------------------CRUD------------------------------
    public List<Veiculo> listarVeiculos() { return repositorioVeiculo.findAll(); }

    public Optional<Veiculo> buscarVeiculoPorId(Long id) { return repositorioVeiculo.findById(id); }

    public Veiculo criarVeiculo(Veiculo veiculo) { return repositorioVeiculo.save(veiculo); }

    public Optional<Veiculo> atualizarVeiculo(Long id, Veiculo informacoes) {
        return repositorioVeiculo.findById(id).map(veiculo -> {
            veiculo.setAnoFabricacao(informacoes.getAnoFabricacao());
            veiculo.setModelo(informacoes.getModelo());
            veiculo.setPlaca(informacoes.getPlaca());
            return repositorioVeiculo.save(veiculo);
        });
    }

    public boolean deletarVeiculo(Long id) {
        return repositorioVeiculo.findById(id).map(veiculo -> {
            repositorioVeiculo.delete(veiculo);
            return true;
        }).orElse(false);
    }

    //-----------------Adicionar-e-remover-acessorios-do-veículo------------------
    public Veiculo adicionarAcessorio(Long idVeiculo, Acessorio acessorio) { //adicionar
        Veiculo veiculo = repositorioVeiculo.findById(idVeiculo)
                .orElseThrow(() -> new RuntimeException("o veículo especificado não foi encontrado!!!"));
        acessorio.setVeiculo(veiculo);
        veiculo.getAcessorios().add(acessorio);
        repositorioAcessorio.save(acessorio);
        return repositorioVeiculo.save(veiculo);
    }

    public Veiculo removerAcessorio(Long idVeiculo, Long idAcessorio) { //remover
        Veiculo veiculo = repositorioVeiculo.findById(idVeiculo)
                .orElseThrow(() -> new RuntimeException("o veículo especificado não foi encontrado!!!"));
        Acessorio acessorio = repositorioAcessorio.findById(idAcessorio)
                .orElseThrow(() -> new RuntimeException("o acessorio especificado não foi encontrado!!!"));
        if (!veiculo.getAcessorios().contains(acessorio)) {
            throw new RuntimeException("esse acessorio não pertence a esse veiculo especificado!!!");
        }
        veiculo.getAcessorios().remove(acessorio); //remove o acessorio do veiculo
        repositorioAcessorio.delete(acessorio); //deleta o acessorio
        return repositorioVeiculo.save(veiculo); //e atualizaz o veiculo para não ter mais o acessório
    }
}

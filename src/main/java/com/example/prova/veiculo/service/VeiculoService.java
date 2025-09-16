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

    //-----------------Adicionar-e-remover-acessorios-do-veiculo------------------

    public Veiculo adicionarAcessorio(Long idVeiculo, Acessorio acessorio) {
        Veiculo veiculo = repositorioVeiculo.findById(idVeiculo)
                .orElseThrow(() -> new RuntimeException("o veiculo nao foi encontrado"));
        veiculo.adicionarAcessorio(acessorio);
        return repositorioVeiculo.save(veiculo);
    }

    public Veiculo removerAcessorio(Long idVeiculo, Long idAcessorio) {
        Veiculo veiculo = repositorioVeiculo.findById(idVeiculo)
                .orElseThrow(() -> new RuntimeException("o veiculo nao foi encontrdo"));

        veiculo.getAcessorios().removeIf(a -> a.getId().equals(idAcessorio));

        return repositorioVeiculo.save(veiculo);
    }
}

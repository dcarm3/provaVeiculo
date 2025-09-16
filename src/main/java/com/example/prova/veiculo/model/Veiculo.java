package com.example.prova.veiculo.model;

import com.example.prova.acessorio.model.Acessorio;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "veiculos")
public class Veiculo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//verificar depois de long é o melhor tipo pra id

    private int anoFabricacao; //ano de fabricacao do veiculo
    private String modelo; //modelo do veiculo
    private String placa; //placa do veiculo

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)//se o veiculo for deletado, os acessorios são deletados juntos
    @JoinColumn(name = "IdVeiculo")
    @JsonManagedReference //pra não aninhar infinitamente
    private List<Acessorio> acessorios = new ArrayList<>();
    //1 veiculo possui 1 ou mais acessorios, 1 acessorio pertence a 1 veiculo

    public void adicionarAcessorio(Acessorio acessorio) {
        acessorios.add(acessorio);
        acessorio.setVeiculo(this);
    }

    public void removerAcessorio(Acessorio acessorio) {
        acessorios.remove(acessorio);
        acessorio.setVeiculo(null);
    }
}

package com.example.prova.acessorio.model;

import jakarta.persistence.*;
import lombok.*;
import com.example.prova.veiculo.model.Veiculo;
@Entity
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "acessorios")
public class Acessorio{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome; //nome do acessório

    @ManyToOne //os veiculos podem ter nenhum ou muitos acessorios
    @JoinColumn(name = "IdVeiculo")
    private Veiculo veiculo;
}

package com.example.prova.veiculo.repository;

import com.example.prova.veiculo.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> { //lembrar de verificar o tipo do dado do id
}

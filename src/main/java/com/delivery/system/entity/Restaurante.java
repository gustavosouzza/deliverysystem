package com.delivery.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurantes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String categoria;

    @Embedded
    private Endereco endereco;

    private LocalTime horarioAbertura;
    private LocalTime horarioFechamento;

    @Builder.Default
    private Double avaliacaoMedia = 0.0;

    @Builder.Default
    private Boolean ativo = true;

    @OneToOne
    @JoinColumn(name = "dono_id")
    private Usuario dono;

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Produto> produtos = new ArrayList<>();

    public boolean estaAberto() {
        LocalTime agora = LocalTime.now();
        return ativo && !agora.isBefore(horarioAbertura) && !agora.isAfter(horarioFechamento);
    }
}

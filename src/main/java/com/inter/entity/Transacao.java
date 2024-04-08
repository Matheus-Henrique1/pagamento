package com.inter.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transacao")
public class Transacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_transacao")
    private LocalDate dataTransacao;

    @Column(name = "nome_estabelecimento")
    private String nomeEstabelecimento;

    @Column(name = "valor_transacao")
    private BigDecimal valorDaTransacao;

    @Column(name = "valor_parcela")
    private BigDecimal valorDaParcela;

    @Column(name = "numero_parcelas")
    private Integer numeroDeParcelas;

    @Column(name = "conta_corrente")
    private Long numeroContaCorrente;

    @Column(name = "parcela")
    private Integer parcela;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_fatura")
    private Fatura fatura;

}

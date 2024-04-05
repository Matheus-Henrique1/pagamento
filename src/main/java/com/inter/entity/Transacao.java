package com.inter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "data_transacao")
    private Date dataTransacao;

    @Column(name = "nome_estabelecimento")
    private String nomeEstabelecimento;

    @Column(name = "valor_transacao")
    private BigDecimal valorDaTransacao;

    @Column(name = "valor_parcela")
    private BigDecimal valorDaParcela;

    @Column(name = "quantidade_parcelas")
    private Integer quantidadeDeParcelas;

    @Column(name = "conta_corrente")
    private String numeroContaCorrente;

    @ManyToOne
    @JoinColumn(name = "id_fatura")
    private Fatura fatura;


}

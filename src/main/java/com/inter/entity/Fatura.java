package com.inter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Fatura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "aberta")
    private Boolean aberta;

    @Column(name = "mes_fatura")
    private String mesDaFatura;

    @OneToMany(mappedBy = "fatura", cascade = CascadeType.ALL)
    private List<Transacao> transacaos;

    @ManyToOne
    @JoinColumn(name = "id_conta_corrente")
    private ContaCorrente contaCorrente;

}

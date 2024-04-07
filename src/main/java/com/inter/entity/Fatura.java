package com.inter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fatura")
public class Fatura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "aberta")
    private boolean aberta;

    @Column(name = "mes_fatura")
    private String mesDaFatura;

    @OneToMany(mappedBy = "fatura", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transacao> transacoes;

    @ManyToOne
    @JoinColumn(name = "id_conta_corrente")
    private ContaCorrente contaCorrente;

}

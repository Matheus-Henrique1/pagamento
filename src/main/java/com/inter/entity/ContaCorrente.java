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
public class ContaCorrente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "numero_conta")
    private String numeroConta;

    @OneToMany(mappedBy = "contaCorrente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Fatura> faturas;

    @OneToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

}

package com.cainant.testebackendtgid.domain;

import com.cainant.testebackendtgid.dto.empresa.EmpresaCadastroDados;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "empresas")
@Entity(name = "Empresa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cnpj;

    private Long saldo;

    public Empresa(@Valid EmpresaCadastroDados cadastroDados) {
        this.nome = cadastroDados.nome();
        this.cnpj = cadastroDados.cnpj();
        this.saldo = 0L;
    }
}

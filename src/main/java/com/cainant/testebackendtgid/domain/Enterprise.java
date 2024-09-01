package com.cainant.testebackendtgid.domain;

import com.cainant.testebackendtgid.dto.enterprise.EnterpriseRegisterData;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@Table(name = "enterprises")
@Entity
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String cnpj;

    private Long balance;

    private Float fee;

    public Enterprise(@Valid EnterpriseRegisterData registerData) {
        this.name = registerData.name();
        this.cnpj = registerData.cnpj();
        this.balance = ThreadLocalRandom.current().nextLong(1000, 5000);
        this.fee = registerData.fee();
    }
}

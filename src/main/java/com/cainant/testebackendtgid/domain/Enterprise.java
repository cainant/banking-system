package com.cainant.testebackendtgid.domain;

import com.cainant.testebackendtgid.dto.enterprise.EnterpriseRegisterData;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.concurrent.ThreadLocalRandom;

@Table(name = "enterprises")
@Entity
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String cnpj;

    private Float balance;

    private Float fee;

    private String webhookURL;

    public Enterprise(@Valid EnterpriseRegisterData registerData) {
        this.name = registerData.name();
        this.cnpj = registerData.cnpj();
        this.email = registerData.email();
        this.balance = ThreadLocalRandom.current().nextFloat(1000, 5000);
        this.fee = registerData.fee();
        this.webhookURL = registerData.webhookURL();
    }
}

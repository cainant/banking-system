package com.cainant.testebackendtgid.domain;

import com.cainant.testebackendtgid.dto.account.AccountRegisterData;
import com.cainant.testebackendtgid.dto.client.ClientRegisterData;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "clients")
@Entity
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String cpf;

    public Client(@Valid ClientRegisterData clientRegisterData) {
        this.name = clientRegisterData.name();
        this.cpf = clientRegisterData.cpf();
    }

    public Client(@Valid AccountRegisterData accountData) {
        this.name = accountData.name();
        this.cpf = accountData.cpf();
    }
}

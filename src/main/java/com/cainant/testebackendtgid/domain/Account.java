package com.cainant.testebackendtgid.domain;

import com.cainant.testebackendtgid.dto.account.AccountRegisterData;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private Float balance;

    @OneToOne
    private Client client;

    @ManyToOne
    private Enterprise enterprise;

    public Account(AccountRegisterData accountData, Client client, Enterprise enterprise) {
        this.email = accountData.email();
        this.balance = 0F;
        this.client = client;
        this.enterprise = enterprise;
    }

}

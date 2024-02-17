package com.tcc.maosestendidas.models.pessoa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Pessoa implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idPessoa;

    private String nomePessoa;
    private String emailPessoa;

    private String senhaPessoa;

    private String cpfPessoa;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataNascimentoPessoa;

    @ManyToOne
    @JoinColumn(name = "idPessoaRole")
    private PessoaRole rolePessoa;

    //MÉTODOS QUE VEM DA CLASSE USERDETAILS
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.rolePessoa.getRolePessoa()));
    }

    @Override
    public String getUsername() {
        return getEmailPessoa();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return getSenhaPessoa();
    }
}

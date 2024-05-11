package com.tcc.maosestendidas.models.pessoa.service;

import com.tcc.maosestendidas.models.pessoa.DTO.PessoaRoleDTO;
import com.tcc.maosestendidas.models.pessoa.entity.PessoaRole;

import java.util.List;
import java.util.Optional;

public interface PessoaRoleService {

    List<PessoaRole> listaPessoaRoles();

    Optional<PessoaRole> buscarPessoaRolePeloNomeOptional(String pessoaRole);

    PessoaRole updatePessoaRole(PessoaRoleDTO dto, String idPessoaRole);


    PessoaRole criaPessoaRole(PessoaRoleDTO dto);
}

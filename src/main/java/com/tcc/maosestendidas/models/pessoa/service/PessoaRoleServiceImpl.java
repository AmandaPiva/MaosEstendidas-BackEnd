package com.tcc.maosestendidas.models.pessoa.service;

import com.tcc.maosestendidas.models.pessoa.DTO.PessoaRoleDTO;
import com.tcc.maosestendidas.models.pessoa.entity.PessoaRole;
import com.tcc.maosestendidas.models.pessoa.entity.PessoaRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaRoleServiceImpl implements PessoaRoleService{
    @Autowired
    private PessoaRoleRepository pessoaRoleRepository;

    @Override
    public List<PessoaRole> listaPessoaRoles() {
        return pessoaRoleRepository.findAll();
    }

    @Override
    public Optional<PessoaRole> buscarPessoaRolePeloNomeOptional(String pessoaRole) {
        return pessoaRoleRepository.findByRolePessoa(pessoaRole);
    }

    @Override
    public PessoaRole updatePessoaRole(PessoaRoleDTO dto, String idPessoaRole) {
        Optional<PessoaRole> pessoaRole = pessoaRoleRepository.findById(idPessoaRole);
        if(pessoaRole.isEmpty()) throw new RuntimeException("Role não encontrada pelo id informado");

        PessoaRole updateRolePessoa = pessoaRole.get();

        updateRolePessoa.setRolePessoa(dto.getRolePessoa());
        pessoaRoleRepository.save(updateRolePessoa);

        return updateRolePessoa;
    }

    @Override
    public PessoaRole criaPessoaRole(PessoaRoleDTO dto) {
      PessoaRole pessoaRole = converteDtoParaPessoaRole(dto);
      pessoaRoleRepository.save(pessoaRole);

      return pessoaRole;
    }

    private PessoaRole converteDtoParaPessoaRole(PessoaRoleDTO dto){
        PessoaRole pessoaRole = new PessoaRole();

        pessoaRole.setRolePessoa(dto.getRolePessoa());

        return pessoaRole;
    }

}

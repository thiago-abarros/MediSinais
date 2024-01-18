package com.mang.medisinais.domain.dto;

import com.mang.medisinais.domain.Endereco;
import com.mang.medisinais.domain.PlanoSaude;
import com.mang.medisinais.domain.Profissional;
import com.mang.medisinais.domain.enums.EspecialidadeProfissional;

import java.util.List;

public record ResultadoDTO(String nome, EspecialidadeProfissional especialidade, List<PlanoSaude> planosAceitos,
                           String email, List<Endereco> enderecos, String telefone, byte[] foto) {

    public static ResultadoDTO fromProfissional(Profissional profissional) {
        return new ResultadoDTO(
                profissional.getNome(), profissional.getEspecialidade(),
                profissional.getPlanosAceitos(), profissional.getEmail(), profissional.getEnderecos(),
                profissional.getTelefone(), profissional.getFoto()
        );
    }

}

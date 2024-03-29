package com.mang.medisinais.domain;

import com.github.slugify.Slugify;
import com.mang.medisinais.domain.enums.EspecialidadeProfissional;
import com.mang.medisinais.dto.CadastroProfissionalDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "profissional")
public class Profissional {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id_profissional")
  private UUID id;

  @Column(nullable = false)
  private String nome;

  @Column(unique = true)
  private String slug;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private EspecialidadeProfissional especialidade;

  @Enumerated(EnumType.STRING)
  @ManyToMany()
  @Column(name = "planos_aceitos")
  private List<PlanoSaude> planosAceitos;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String senha;

  @OneToOne(mappedBy = "profissional")
  private Endereco endereco;

  @Column(unique = true, length = 11, nullable = false)
  @Size(min = 11, max = 11)
  private String cpf;

  @Column(unique = true, length = 11, nullable = false)
  @Size(min = 11, max = 11)
  private String telefone;

  @Column(name = "foto_usuario")
  private byte[] foto;

  public Profissional(CadastroProfissionalDTO profissionalDTO, String senha,
                      List<PlanoSaude> planos) {
    this.nome = profissionalDTO.nome();
    this.email = profissionalDTO.email();
    this.cpf = profissionalDTO.cpf();
    this.telefone = profissionalDTO.telefone();
    this.planosAceitos = planos;
    this.especialidade = profissionalDTO.especialidade();
    this.senha = senha;
    try {
      if(profissionalDTO.foto().isEmpty()) {
        this.foto = null;
      } else {
        this.foto = profissionalDTO.foto().getBytes();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    this.slug = Slugify.builder().build().slugify(profissionalDTO.nome());
  }
}

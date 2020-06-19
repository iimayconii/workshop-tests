package br.com.crptecnologia.workshop.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioRequest {
    String nome;
    String email;
}

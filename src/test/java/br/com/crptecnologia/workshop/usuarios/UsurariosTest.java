package br.com.crptecnologia.workshop.usuarios;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsurariosTest {

    Usuarios usuarios;

    @BeforeEach
    public void setUp() {
        usuarios = new Usuarios(new InMemoryUsuarioRepository());
    }

    @Test
    public void testSalvarUsuarioComSucesso() throws EmailInvalidoException, EmailEmUsoException {
        Usuario usuarioParaSalvar = Usuario.builder().nome("Anderson").email("anderson@gmail.com").build();
        Usuario usuarioSalvo = usuarios.adicionar(usuarioParaSalvar);
        Assertions.assertThat(usuarioSalvo).isNotNull().extracting("id").isNotNull();
    }

}

package br.com.crptecnologia.workshop.spring;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.crptecnologia.workshop.usuarios.EmailEmUsoException;
import br.com.crptecnologia.workshop.usuarios.EmailInvalidoException;
import br.com.crptecnologia.workshop.usuarios.Usuario;
import br.com.crptecnologia.workshop.usuarios.Usuarios;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SpringUsuarioTest {

    @Autowired
    Usuarios usuarios;

    @Test
    public void testSalvarUsuarioSpringComSucesso() throws EmailInvalidoException, EmailEmUsoException {
        Usuario usuarioParaSalvar = Usuario.builder().nome("Anderson").email("anderson@gmail.com").build();
        Usuario usuarioSalvo = usuarios.adicionar(usuarioParaSalvar);
        Assertions.assertThat(usuarioSalvo).isNotNull().extracting("id").isNotNull();
    }

    @Test
    public void testSalvarUsuarioSemEmailRetornandoException() {
        assertThrows(EmailInvalidoException.class, () -> {
            Usuario usuarioParaSalvar = Usuario.builder().nome("Anderson").build();
            usuarios.adicionar(usuarioParaSalvar);
        });
    }

}

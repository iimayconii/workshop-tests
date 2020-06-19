package br.com.crptecnologia.workshop.spring;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.crptecnologia.workshop.controller.UsuarioController;
import br.com.crptecnologia.workshop.controller.UsuarioRequest;
import br.com.crptecnologia.workshop.usuarios.EmailInvalidoException;
import br.com.crptecnologia.workshop.usuarios.Usuario;
import br.com.crptecnologia.workshop.usuarios.Usuarios;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UsuarioController.class)
public class SpringUsuarioIntegrationTest {

    @MockBean
    Usuarios usuarios;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testSalvarUsuarioSpringComSucesso() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Usuario usuarioSalvo = Usuario.builder().nome("Anderson").email("anderson@gmail.com").build();
        UsuarioRequest usuarioRequest = UsuarioRequest.builder().nome("Anderson").email("anderson@gmail.com").build();

        when(usuarios.adicionar(any(Usuario.class))).thenReturn(usuarioSalvo);

        mockMvc.perform(post("/usuarios")
                .content(objectMapper.writeValueAsString(usuarioRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome")
                        .value(usuarioRequest.getNome()))
                .andExpect(jsonPath("$.email")
                        .value(usuarioRequest.getEmail()));
    }

    @Test
    public void testSalvarUsuarioSpringSemEmailRetornandoBadRequest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        UsuarioRequest usuarioRequest = UsuarioRequest.builder().nome("Anderson").build();

        when(usuarios.adicionar(any(Usuario.class))).thenThrow(EmailInvalidoException.class);

        mockMvc.perform(post("/usuarios")
                .content(objectMapper.writeValueAsString(usuarioRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}

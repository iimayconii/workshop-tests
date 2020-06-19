package br.com.crptecnologia.workshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crptecnologia.workshop.usuarios.EmailEmUsoException;
import br.com.crptecnologia.workshop.usuarios.EmailInvalidoException;
import br.com.crptecnologia.workshop.usuarios.Usuario;
import br.com.crptecnologia.workshop.usuarios.Usuarios;

@RestController
@RequestMapping({"/usuarios"})
public class UsuarioController {

    final Usuarios usuarios;

    public UsuarioController(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    @PostMapping
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody UsuarioRequest request) throws EmailInvalidoException, EmailEmUsoException {
        try {
            Usuario usuario = new Usuario();
            usuario.setNome(request.getNome());
            usuario.setEmail(request.getEmail());
            return ResponseEntity.ok(usuarios.adicionar(usuario));
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable long id, @RequestBody UsuarioRequest request) throws EmailInvalidoException, EmailEmUsoException {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        return ResponseEntity.ok(usuarios.atualizar(usuario));
    }

}

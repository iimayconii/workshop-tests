package br.com.crptecnologia.workshop.usuarios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class Usuarios {

    private final CrudRepository<Usuario, Long> repository;

    public Usuarios(CrudRepository<Usuario, Long> repository) {
        this.repository = repository;
    }

    public Usuario adicionar(Usuario usuario) throws EmailInvalidoException, EmailEmUsoException {
        validarEmail(usuario.getEmail());
        return repository.save(usuario);
    }

    public Usuario atualizar(Usuario usuario) throws EmailInvalidoException, EmailEmUsoException {
        validarEmail(usuario.getEmail());
        return repository.save(usuario);
    }

    //TODO: Ainda precisa validar se o e-mail realmente é válido, se tem ou não a @ e o domain.
    private void validarEmail(String email) throws EmailEmUsoException, EmailInvalidoException {
        if (email == null) {
            throw new EmailInvalidoException("E-mail não informado.");
        }
        if (existeUsuarioComEmail(email)) {
            throw new EmailEmUsoException("E-mail em uso");
        }
    }

    //TODO: Não está verificando o id no usuário de proposito para cair no test
    private boolean existeUsuarioComEmail(String email) {
        List<Usuario> usuarioList = (List<Usuario>) repository.findAll();
        Optional<Usuario> usuarioComEmailInformado =
                usuarioList.stream().filter(x -> x.getEmail().equalsIgnoreCase(email)).findFirst();
        return usuarioComEmailInformado.isPresent();
    }
}

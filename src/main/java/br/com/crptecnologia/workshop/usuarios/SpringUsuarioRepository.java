package br.com.crptecnologia.workshop.usuarios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringUsuarioRepository extends CrudRepository<Usuario, Long> {

}

package br.com.centralerros;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.com.centralerros.entity.Usuario;
import br.com.centralerros.repository.UsuarioRepository;

@SpringBootTest
@Transactional
@Rollback
class CentralerrosApplicationTests {

	@Autowired
	private UsuarioRepository userRepo;

	@Test
	void contextLoads() {
	}

	@Test
	void UsuarioRepositorySaveTestOk(){
		Usuario usuario = new Usuario();
		usuario.setNome("teste");
		usuario.setEmail("usuario@teste.com.br");
		usuario.setSenha("1234");
		userRepo.save(usuario);
		assertNotNull(usuario.getId());
	}


}

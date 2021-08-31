package br.com.centralerros;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.HashSet;

import br.com.centralerros.service.LogService;
import br.com.centralerros.service.LogServiceImpl;
import br.com.centralerros.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.com.centralerros.entity.Autorizacao;
import br.com.centralerros.entity.Categoria;
import br.com.centralerros.entity.Level;
import br.com.centralerros.entity.Log;
import br.com.centralerros.entity.Usuario;
import br.com.centralerros.repository.AutorizacaoRepository;
import br.com.centralerros.repository.LogRepository;
import br.com.centralerros.repository.UsuarioRepository;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback
class CentralerrosApplicationTests {

	@Autowired
	private UsuarioRepository userRepo;

	@Autowired
	private LogRepository logRepo;

	@Autowired
	private AutorizacaoRepository autorizacaoRepo;

	@Autowired
	private UsuarioService userService;

	@Autowired
	private LogService logService;

	@Test
	void contextLoads() {
	}

	@Test
	void UsuarioRepositorySaveTestOk(){
		Usuario usuario = new Usuario();
		usuario.setNome("teste");
		usuario.setEmail("usuario@teste.com.br");
		usuario.setSenha("1234");
		usuario.setToken("as902asldkdo");
		userRepo.save(usuario);
		assertNotNull(usuario.getId());
	}

	@Test
	void UsuarioRepositorySaveWithAutorizacaoTestOk(){
		Autorizacao aut = new Autorizacao();
		aut.setNome("ROLE_2");
		autorizacaoRepo.save(aut);
		Usuario usuario = new Usuario();
		usuario.setNome("teste");
		usuario.setEmail("usuario@teste.com.br");
		usuario.setSenha("1234");
		usuario.setToken("as902asldkdo");
		usuario.setAutorizacoes(new HashSet<Autorizacao>());
		usuario.getAutorizacoes().add(aut);
		userRepo.save(usuario);
		assertNotNull(usuario.getId());
	}

	@Test
	void UsuarioRepositoryFindByAutorizacaoNomeTestOk(){
		Autorizacao aut = new Autorizacao();
		aut.setNome("ROLE_2");
		autorizacaoRepo.save(aut);
		Usuario usuario = new Usuario();
		usuario.setNome("teste");
		usuario.setEmail("usuario@teste.com.br");
		usuario.setSenha("1234");
		usuario.setToken("as902asldkdo");
		usuario.setAutorizacoes(new HashSet<Autorizacao>());
		usuario.getAutorizacoes().add(aut);
		userRepo.save(usuario);
		List<Usuario> users = userRepo.findByAutorizacoesNome("ROLE_2");
		assertFalse(users.isEmpty());
	}

	@Test
	void UsuarioRepositoryFindOneByEmailTestOk(){
		Usuario usuario = new Usuario();
		usuario.setNome("teste");
		usuario.setEmail("usuario@teste.com.br");
		usuario.setSenha("1234");
		usuario.setToken("as902asldkdo");
		userRepo.save(usuario);
		assertNotNull(userRepo.findOneByEmail("usuario@teste.com.br"));
	}

	@Test
	void UsuarioServiceCadastrarTestOk(){
		userService.cadastrarUsuarioWithAutorizacao("Usuario","1234","usuario@email.com","ROLE");
		List<Usuario> usuarios = userRepo.findByAutorizacoesNome("ROLE");
		assertFalse(usuarios.isEmpty());
	}

	@Test
	void LogRepositorySaveTestOk(){
		Log log = new Log();
		log.setTitulo("teste");
		log.setCategoria(Categoria.DEVELOPMENT);
		log.setLevel(Level.DEBUG);
		log.setDetalhe("teste");
		log.setOrigem("novo");
		logRepo.save(log);
		assertNotNull(log.getId());
	}

	@Test
	void LogRepositoryFindOneByCategoriaTestOk(){
		Log log = new Log();
		log.setTitulo("teste");
		log.setCategoria(Categoria.DEVELOPMENT);
		log.setLevel(Level.DEBUG);
		log.setDetalhe("teste");
		log.setOrigem("novo");
		logRepo.save(log);
		assertNotNull(logRepo.findOneByCategoria(Categoria.DEVELOPMENT));
	}

	@Test
	void LogRepositoryFindOneByCategoriaAndLevelTestOk(){
		Log log = new Log();
		log.setTitulo("teste");
		log.setCategoria(Categoria.DEVELOPMENT);
		log.setLevel(Level.DEBUG);
		log.setDetalhe("teste");
		log.setOrigem("novo");
		logRepo.save(log);
		assertNotNull(logRepo.findOneByCategoriaAndLevel(Categoria.DEVELOPMENT,Level.DEBUG));
	}

	@Test
	void LogRepositoryFindByUsuarioNomeTestOk(){
		Usuario user = new Usuario();
		user.setNome("Usuario");
		user.setEmail("usuario@email.com");
		user.setSenha("senha");
		userRepo.save(user);
		Log log = new Log();
		log.setTitulo("teste");
		log.setCategoria(Categoria.DEVELOPMENT);
		log.setLevel(Level.DEBUG);
		log.setDetalhe("teste");
		log.setOrigem("novo");
		log.setUsuario(new HashSet<Usuario>());
		log.getUsuario().add(user);
		logRepo.save(log);
		List<Log> logs = logRepo.findByUsuarioNome("Usuario");
		assertFalse(logs.isEmpty());
	}

	@Test
	void LogRepositoryFindByUsuarioEmailTestOk(){
		Usuario user = new Usuario();
		user.setNome("Usuario");
		user.setEmail("usuario@email.com");
		user.setSenha("senha");
		userRepo.save(user);
		Log log = new Log();
		log.setTitulo("teste");
		log.setCategoria(Categoria.DEVELOPMENT);
		log.setLevel(Level.DEBUG);
		log.setDetalhe("teste");
		log.setOrigem("novo");
		log.setUsuario(new HashSet<Usuario>());
		log.getUsuario().add(user);
		logRepo.save(log);
		List<Log> logs = logRepo.findByUsuarioEmail("usuario@email.com");
		assertFalse(logs.isEmpty());
	}

	@Test
	void LogServiceSaveTestOk(){
		Usuario user = new Usuario();
		user.setNome("Usuario");
		user.setEmail("usuario@email.com");
		user.setSenha("senha");
		userRepo.save(user);
		logService.cadastrarLog("Titulo",Categoria.DEVELOPMENT,Level.DEBUG,"detalhe","origem",user);
		assertNotNull(logRepo.findByUsuarioEmail("usuario@email.com"));
	}

	@Test
	void LogServiceSaveWithNewUserTestOk(){
		logService.cadastrarLogWithNewUser("Titulo",Categoria.DEVELOPMENT,Level.DEBUG,"detalhe","origem",
				"Usuario","usuario@email.com","senha");
		assertNotNull(logRepo.findByUsuarioEmail("usuario@email.com"));
	}

	@Test
	void AutorizacaoRepositorySaveTestOk(){
		Autorizacao autorizacao = new Autorizacao();
		autorizacao.setNome("ROLE_1");
		autorizacaoRepo.save(autorizacao);
		assertNotNull(autorizacao.getId());
	}

	@Test
	void AutorizacaoRepositoryFindNameTestOk(){
		Autorizacao autorizacao = new Autorizacao();
		autorizacao.setNome("ROLE_1");
		autorizacaoRepo.save(autorizacao);
		assertNotNull(autorizacaoRepo.findByNome("ROLE_1"));
	}

	@Test
	void AutorizacaoRepositoryFindUsuarioEmailTestOk(){
		Autorizacao autorizacao = new Autorizacao();
		autorizacao.setNome("ROLE_1");
		autorizacaoRepo.save(autorizacao);
		Usuario user = userService.cadastrarUsuarioWithAutorizacao("Usuario","senha","usuario@email.com","ROLE_1");

		assertNotNull(autorizacaoRepo.findByUsuariosEmail("usuario@email.com"));
	}
}

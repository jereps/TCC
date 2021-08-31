 package br.com.centralerros.service;

 import br.com.centralerros.entity.Categoria;
 import br.com.centralerros.entity.Level;
 import br.com.centralerros.entity.Log;
 import br.com.centralerros.entity.Usuario;
 import br.com.centralerros.repository.LogRepository;
 import br.com.centralerros.repository.UsuarioRepository;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;

 import java.util.HashSet;
 import java.util.Optional;

// import br.com.centralerros.mapper.LogMapper;

 @Service
 public class LogServiceImpl implements LogService{

     @Autowired
     private UsuarioRepository userRepo;

     @Autowired
     private LogRepository logRepo;

     @Transactional
     public Log cadastrarLog(String titulo, Categoria categoria, Level level, String detalhe, String origem, Usuario user){
         Log log = new Log();
         log.setTitulo(titulo);
         log.setCategoria(categoria);
         log.setLevel(level);
         log.setDetalhe(detalhe);
         log.setOrigem(origem);
         log.setUsuario(new HashSet<Usuario>());
         log.getUsuario().add(user);
         logRepo.save(log);
         return log;
     }

     @Transactional
     public Log cadastrarLogWithNewUser(String titulo, Categoria categoria, Level level,
                                     String detalhe, String origem,String userNome, String userEmail, String userSenha){

         Usuario user = userRepo.findByEmail(userEmail);
         if (user == null) {
             user = new Usuario();
             user.setNome(userNome);
             user.setEmail(userEmail);
             user.setSenha(userSenha);
             userRepo.save(user);
         }

         Log log = new Log();
         log.setTitulo(titulo);
         log.setCategoria(categoria);
         log.setLevel(level);
         log.setDetalhe(detalhe);
         log.setOrigem(origem);
         log.setUsuario(new HashSet<Usuario>());
         log.getUsuario().add(user);
         logRepo.save(log);
         return log;
     }











//     private LogMapper mapper;
//
//     @Autowired
//     private LogRepository logRepository;
//
//
//     public Log add(String token, LogDTO dto) {
//         Usuario usuario = usuarioService.findByToken(token)
//                 .orElseThrow(() -> new IllegalArgumentException("Token invalido"));
//
//         Log log = mapper.map(dto);
//         //log.setUsuario(usuario);
//         return logRepository.saveAndFlush(log);
//     }
//
//     @PreAuthorize("isAuthenticated")
//     public List<LogRespondeDTO> findByAll(Pageable pageable) {
//         return mapper.map(logRepository.findAll(pageable).getContent());
//     }
//
//     @PreAuthorize("isAuthenticated")
//     public List<LogRespondeDTO> findByCategoria(Enum categoria, Pageable pageable) {
//         return mapper.map(logRepository.findByCategoria(categoria, pageable).getContent());
//     }
//
//     @PreAuthorize("isAuthenticated")
//     public List<LogRespondeDTO> findByCategoriaAndLevel(Enum categoria, Enum level, Pageable pageable) {
//         return mapper.map(logRepository.findByCategoriaAndLevel(categoria, level, pageable).getContent());
//     }
//
//     @PreAuthorize("isAuthenticated")
//     public List<LogRespondeDTO> findByCategoriaWithDescricao(Enum categoria, String descricao, Pageable pageable) {
//         return mapper.map(logRepository.findByCategoriaWithDescricao(categoria, descricao, pageable).getContent());
//     }
//
//     @PreAuthorize("isAuthenticated")
//     public List<LogRespondeDTO> findByCategoriaWithOrigem(Enum categoria, String origem, Pageable pageable) {
//         return mapper.map(logRepository.findByCategoriaWithOrigem(categoria, origem, pageable).getContent());
//     }
 }

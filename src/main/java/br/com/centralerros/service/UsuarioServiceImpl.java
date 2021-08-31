 package br.com.centralerros.service;

 import br.com.centralerros.entity.Autorizacao;
 import br.com.centralerros.entity.Usuario;
 import br.com.centralerros.repository.AutorizacaoRepository;
 import br.com.centralerros.repository.UsuarioRepository;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;

 import java.util.HashSet;

// import br.com.centralerros.mapper.UsuarioMapper;

 @Service
 public class UsuarioServiceImpl implements UsuarioService  {

     @Autowired
     private UsuarioRepository userRepo;

     @Autowired
     private AutorizacaoRepository autRepo;

//     @Autowired
//     private UsuarioMapper mapper;

     @Transactional
     public Usuario cadastrarUsuarioWithAutorizacao(String nome, String senha,String email, String autorizacao){
         Autorizacao aut = autRepo.findByNome(autorizacao);
         if (aut == null) {
             aut = new Autorizacao();
             aut.setNome(autorizacao);
             autRepo.save(aut);
         }

         Usuario user = new Usuario();
         user.setNome(nome);
         user.setEmail(email);
         user.setSenha(senha);
         user.setAutorizacoes(new HashSet<Autorizacao>());
         user.getAutorizacoes().add(aut);
         userRepo.save(user);

         return user;
     }

     @Transactional
     public Usuario cadastrarUsuario(String nome, String senha,String email){
         Usuario user = new Usuario();
         user.setNome(nome);
         user.setEmail(email);
         user.setSenha(senha);
         userRepo.save(user);

         return user;
     }



















//     @PreAuthorize("isAuthenticated")
//     public Optional<Usuario> findByEmail(String email) {
//         return usuarioRepository.findByEmail(email);
//     }
//
// //    @PreAuthorize("isAuthenticated")
//     public List<Usuario> findAll() {
//         return usuarioRepository.findAll();
//     }
//
//     @Transactional
// //    @PreAuthorize("hasRole('ROLE_ADMIN')")
//     public Usuario save(UsuarioDTO dto) {
//         validarEmailExists(dto.getEmail());
//
//         Usuario user = mapper.map(dto);
//         user.setToken(UUID.randomUUID().toString());
//         user.setSenha(passwordEncoder().encode(dto.getSenha()));
//         user = usuarioRepository.saveAndFlush(user);
//         return user;
//     }
//
//     private void validarEmailExists(String email) {
//         if (usuarioRepository.findByEmail(email).isPresent()) throw new ExistException("Email já cadastrado");
//     }
//
//     public static BCryptPasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
//
//     @PreAuthorize("isAuthenticated")
//     public Optional<Usuario> findByToken(String token) {
//         return usuarioRepository.findByToken(token);
//     }
 }

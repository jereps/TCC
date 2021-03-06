 package br.com.centralerros.service;

 import br.com.centralerros.dto.UsuarioDTO;
 import br.com.centralerros.entity.Autorizacao;
 import br.com.centralerros.entity.Usuario;
 import br.com.centralerros.repository.AutorizacaoRepository;
 import br.com.centralerros.repository.UsuarioRepository;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;

 import java.util.HashSet;
 import java.util.List;
 import java.util.Optional;

// import br.com.centralerros.mapper.UsuarioMapper;

 @Service
 public class UsuarioServiceImpl implements UsuarioService  {

     @Autowired
     private UsuarioRepository userRepo;

     @Autowired
     private AutorizacaoRepository autRepo;

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

     @Transactional
     public Usuario cadastrarUsuarioWithAutorizacao(UsuarioDTO userDTO){
         Autorizacao aut = autRepo.findByNome(userDTO.getAutorizacao().getNome());
         if (aut == null) {
             aut = new Autorizacao();
             aut.setNome(userDTO.getAutorizacao().getNome());
             autRepo.save(aut);
         }
         Usuario user = new Usuario();
         user.setNome(user.getNome());
         user.setEmail(user.getEmail());
         user.setSenha(user.getSenha());
         user.setAutorizacoes(new HashSet<Autorizacao>());
         user.getAutorizacoes().add(aut);

         userRepo.save(user);
         return user;
     }

     public List<Usuario> findAll() {
         return userRepo.findAll();
     }

      public Usuario findByEmail(String email) {
         return userRepo.findOneByEmail(email);
     }

     public Usuario atualizarUsuario(UsuarioDTO userDto) {
         Usuario usuario = userRepo.findOneByEmail(userDto.getEmail());

         return userRepo.save(usuario);
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
//         if (usuarioRepository.findByEmail(email).isPresent()) throw new ExistException("Email j?? cadastrado");
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

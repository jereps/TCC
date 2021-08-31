// package br.com.centralerros.service;

// import br.com.centralerros.dto.UsuarioDTO;
// import br.com.centralerros.exception.ExistException;
// import br.com.centralerros.repository.UsuarioRepository;
// import br.com.centralerros.entity.Usuario;
// import br.com.centralerros.mapper.UsuarioMapper;
// import lombok.AllArgsConstructor;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import java.util.List;
// import java.util.Optional;
// import java.util.UUID;

// @Service
// @AllArgsConstructor
// public class UsuarioServiceImpl  {

//     @Autowired
//     private UsuarioRepository usuarioRepository;

//     @Autowired
//     private UsuarioMapper mapper;

//     @PreAuthorize("isAuthenticated")
//     public Optional<Usuario> findByEmail(String email) {
//         return usuarioRepository.findByEmail(email);
//     }

// //    @PreAuthorize("isAuthenticated")
//     public List<Usuario> findAll() {
//         return usuarioRepository.findAll();
//     }

//     @Transactional
// //    @PreAuthorize("hasRole('ROLE_ADMIN')")
//     public Usuario save(UsuarioDTO dto) {
//         validarEmailExists(dto.getEmail());

//         Usuario user = mapper.map(dto);
//         user.setToken(UUID.randomUUID().toString());
//         user.setSenha(passwordEncoder().encode(dto.getSenha()));
//         user = usuarioRepository.saveAndFlush(user);
//         return user;
//     }

//     private void validarEmailExists(String email) {
//         if (usuarioRepository.findByEmail(email).isPresent()) throw new ExistException("Email já cadastrado");
//     }

//     public static BCryptPasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @PreAuthorize("isAuthenticated")
//     public Optional<Usuario> findByToken(String token) {
//         return usuarioRepository.findByToken(token);
//     }
// }

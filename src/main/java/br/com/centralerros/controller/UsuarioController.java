 package br.com.centralerros.controller;

 import br.com.centralerros.dto.UsuarioDTO;
 import br.com.centralerros.entity.Usuario;
 import br.com.centralerros.service.UsuarioService;
 import io.swagger.annotations.Api;
 import io.swagger.annotations.ApiOperation;
 import lombok.AllArgsConstructor;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.web.bind.annotation.*;

 import javax.validation.Valid;
 import java.util.List;

 @AllArgsConstructor
 @RestController
 @RequestMapping("usuario")
 @Api(tags = {"Authentication"}, description = "Endpoint para gerenciamento de usuario")
 public class UsuarioController {

     @Autowired
     private UsuarioService usuarioService;

     @ApiOperation(value = "Criar um novo usuario")
     @PostMapping
     @ResponseStatus(code = HttpStatus.CREATED)
     public Usuario add(@Valid @RequestBody UsuarioDTO dto) {
         return usuarioService.cadastrarUsuarioWithAutorizacao(dto);
     }

     @ApiOperation(value = "Pega dados do usuario autenticado")
     @GetMapping
     public List<Usuario> listarUsuarios() {
         return usuarioService.findAll();
     }

     @ApiOperation(value = "Pega dados do usuario autenticado")
     @GetMapping(value = "{email}")
     public Usuario buscarPorEmail(@PathVariable("email") String email) {
         return usuarioService.findByEmail(email);
     }

     @ApiOperation(value = "Pega dados do usuario autenticado")
     @PutMapping
     public Usuario atualizarUsuario(@RequestBody UsuarioDTO dto) {
         return usuarioService.atualizarUsuario(dto);
     }


 //    @ApiOperation(value = "Pega dados do usuario autenticado")
 //    @GetMapping
 //    public UsuarioRespontaDTO listar() {
 //        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 //        return mapper.map(usuarioService.findByEmail(usuario.getEmail()).get());
 //    }

//     @ApiOperation(value = "Pega dados do usuario autenticado")
//     @GetMapping
//     public List<UsuarioRespontaDTO> listar() {
//         Usuario usuario = new Usuario();
//         return (List<UsuarioRespontaDTO>) mapper.map(usuarioService.findAll());
//     }




 }

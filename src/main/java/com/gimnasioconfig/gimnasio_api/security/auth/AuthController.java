package com.gimnasioconfig.gimnasio_api.security.auth;

import com.gimnasioconfig.gimnasio_api.dto.ClienteDTO;
import com.gimnasioconfig.gimnasio_api.dto.RegistroClienteDTO;
import com.gimnasioconfig.gimnasio_api.models.Cliente;
import com.gimnasioconfig.gimnasio_api.models.Usuario;
import com.gimnasioconfig.gimnasio_api.security.config.AuthRequest;
import com.gimnasioconfig.gimnasio_api.security.config.AuthResponse;
import com.gimnasioconfig.gimnasio_api.security.jwt.JwtUtil;
import com.gimnasioconfig.gimnasio_api.services.ClienteService;
import com.gimnasioconfig.gimnasio_api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClienteService clienteService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistroClienteDTO dto) {
        if (usuarioService.findByUsername(dto.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("El nombre de usuario ya existe");
        }

        // 1. Crear usuario
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());
        usuario.setRol("CLIENTE");
        usuario.setActivo(true);
        Usuario usuarioGuardado = usuarioService.save(usuario);

        // 2. Crear cliente asociado
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setTelefono(dto.getTelefono());
        cliente.setFechaRegistro(LocalDate.now());
        cliente.setActivo(true);
        cliente.setUsuario(usuarioGuardado); // relación con usuario
        clienteService.save(cliente);

        // 3. Generar token JWT automáticamente
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                usuarioGuardado.getUsername(),
                usuarioGuardado.getPassword(), // ya viene encriptado
                List.of(new SimpleGrantedAuthority("ROLE_" + usuarioGuardado.getRol()))
        );
        String token = jwtUtil.generateToken(userDetails);

        // 4. Devolver token directamente (o el cliente y token si prefieres)
        return ResponseEntity.ok(new AuthResponse(token));
    }

}
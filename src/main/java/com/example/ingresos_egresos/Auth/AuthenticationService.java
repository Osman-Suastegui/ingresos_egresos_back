package com.example.ingresos_egresos.Auth;

import com.example.ingresos_egresos.HandleErrors.InvalidCredentialsException;
import com.example.ingresos_egresos.Rol.RolRepository;
import com.example.ingresos_egresos.Rol.Role;
import com.example.ingresos_egresos.users.User;
import com.example.ingresos_egresos.users.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RolRepository rolRepository;

    public ResponseEntity<AuthenticationResponse>  authenticate(AuthenticationRequest authenticateRequest) {
         String usuario = authenticateRequest.getUsuario();
         String password = authenticateRequest.getPassword();
         boolean credentialsValid = userRepository.existsByUsernameAndPassword(usuario,password);
         Long id = 0L;
         String rol ="";
         if(!credentialsValid) throw new InvalidCredentialsException();


        id = userRepository.findIdByUsername(usuario);
        rol = userRepository.findById(id).get().getRole().getName();
         AuthenticationResponse response = new AuthenticationResponse(id,credentialsValid,rol);
        return ResponseEntity.ok(response);

    }

    public ResponseEntity<AuthenticationResponse> register(RegisterRequest req) {
        User newUser = new User();
        newUser.setUsername(req.getUsuario());
        newUser.setPassword(req.getPassword());
        newUser.setEmail(req.getEmail());
        newUser.setActive(true);
        Optional<Role> role = rolRepository.findById(req.getIdRol());
        newUser.setRole(role.get());
        User savedUser = userRepository.save(newUser);
        Long id = savedUser.getId();
        return ResponseEntity.ok(new AuthenticationResponse(id,true,role.get().getName()));
    }
}

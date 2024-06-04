package com.example.ingresos_egresos.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private  final AuthenticationService usuarioService;
    @CrossOrigin(origins = "*")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest req) {
        return usuarioService.authenticate(req);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/register")

    public ResponseEntity<AuthenticationResponse> register ( @RequestBody RegisterRequest req) {
        return usuarioService.register(req);
    }
}

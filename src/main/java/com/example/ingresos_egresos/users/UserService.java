package com.example.ingresos_egresos.Escuelas;


import com.example.ingresos_egresos.users.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.ingresos_egresos.users.User;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

private final UserRepository userRepository;


    public List<User> getUsers() {
        return userRepository.findAll();
    }

}

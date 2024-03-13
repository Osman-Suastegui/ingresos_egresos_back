package com.example.ingresos_egresos.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByUsernameAndPassword(String username,String password);

    @Query("SELECT u.id FROM User u WHERE u.username = ?1")
    Long findIdByUsername(String username);


}

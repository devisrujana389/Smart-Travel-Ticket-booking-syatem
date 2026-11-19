package com.decodelabs.UserApi;

//import com.decodelabs.userapi.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repositary extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);
}
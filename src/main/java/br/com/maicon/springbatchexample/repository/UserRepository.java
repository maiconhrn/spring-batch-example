package br.com.maicon.springbatchexample.repository;

import br.com.maicon.springbatchexample.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

package br.com.carro.repository;
import br.com.carro.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String login);
}

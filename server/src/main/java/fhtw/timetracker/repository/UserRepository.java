package fhtw.timetracker.repository;

import fhtw.timetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByLogin(String login);
}

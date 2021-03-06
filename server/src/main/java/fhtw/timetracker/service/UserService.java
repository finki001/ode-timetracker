package fhtw.timetracker.service;

import fhtw.timetracker.model.Role;
import fhtw.timetracker.model.User;
import fhtw.timetracker.model.UserDTO;
import fhtw.timetracker.repository.UserRepository;
import fhtw.timetracker.util.DTOMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(DTOMapper::convertUsertoUserDTO)
                .collect(Collectors.toList());
    }

    public void createUser(String login, String firstname, String lastname, Role role, String passwordPlain) {
        User newUser = new User(login, firstname, lastname, role, passwordEncoder.encode(passwordPlain));
        userRepository.save(newUser);
    }

    public boolean deleteUser(int userId) {
        userRepository.deleteById(userId);
        return true;
    }

    public UserDTO findByUsername(String username) throws Exception {
        User user = userRepository.findUserByLogin(username);

        if (user == null) {
            throw new Exception("User not found");
        }

        return DTOMapper.convertUsertoUserDTO(user);
    }
}

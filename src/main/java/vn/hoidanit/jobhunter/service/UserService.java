package vn.hoidanit.jobhunter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getListUsers() {
        return this.userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else
            return null;
    }

    public User updatedUser(User user) {
        User current = getUserById(user.getId());
        if (user != null) {
            current.setUsername(user.getUsername());
            current.setPassword(user.getPassword());
            current.setEmail(user.getEmail());
            current = userRepository.save(current);
        }

        return current;
    }

    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }
}

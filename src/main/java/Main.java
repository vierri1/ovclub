import ru.openvoleyballclub.model.Role;
import ru.openvoleyballclub.model.User;
import ru.openvoleyballclub.repository.implementation.UserRepositoryJdbcImpl;
import ru.openvoleyballclub.repository.interfaces.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryJdbcImpl();
//        System.out.println(
//                userRepository.add(new User(null, "name", "surname", LocalDateTime.now(),
//                        "login", "123", LocalDate.now(), Role.PLAYER, false, null))
//        );

        User user = new User(null, "newUser", "surnameUser", LocalDateTime.now(), "user", "pass", LocalDate.now(), Role.ADMIN, false, null);
        userRepository.add(user);
        System.out.println(userRepository.get(9));
    }
}

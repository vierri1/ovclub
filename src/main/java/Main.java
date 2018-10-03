import ru.openvoleyballclub.repository.implementation.UserRepositoryJdbcImpl;
import ru.openvoleyballclub.repository.interfaces.UserRepository;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryJdbcImpl();
        System.out.println(userRepository.getAuthUser("travis", "456"));
    }
}

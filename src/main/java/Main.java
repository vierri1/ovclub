import ru.openvoleyballclub.repository.implementation.TeamRepositoryJdbcImpl;
import ru.openvoleyballclub.repository.implementation.UserRepositoryJdbcImpl;
import ru.openvoleyballclub.repository.interfaces.TeamRepository;
import ru.openvoleyballclub.repository.interfaces.UserRepository;
import ru.openvoleyballclub.service.implementation.TeamServiceImpl;
import ru.openvoleyballclub.service.intervaces.TeamService;

public class Main {
    public static void main(String[] args) {
        TeamRepository teamRepository = new TeamRepositoryJdbcImpl();
        UserRepository userRepository = new UserRepositoryJdbcImpl();
        TeamService teamService = new TeamServiceImpl();

        // System.out.println(teamService.leaveTeam("8", "1"));
        System.out.println(teamService.sendRequestToTeam("8", "2"));
    }
}

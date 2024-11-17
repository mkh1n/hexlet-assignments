package exercise.dto.users;

import exercise.model.User;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
// BEGIN
public class UsersPage {
    private List<User> users;
}
// END

package poker.servlet;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    public String insertUser(User user);
    public User findUserByUsernameOrEmailOrGameId(String username, String email, String gameId);
    public String findRequests(String username);
    public String updateRequests(String username, String requests);
    public String findFriends(String username);
    public String updateFriends(String username, String requests);
}
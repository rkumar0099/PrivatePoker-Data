package poker.servlet;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@RestController
public class PokerServlet {
    @Autowired(required = true)
    UserRepository repository;

    private final static PrintStream out = System.out;

    @GetMapping(value="/users")
    public Map<String, Object> getUser(@RequestParam(value="identifier", required=false) String identifier, 
     @RequestParam(value="username", required = false) String username,
     @RequestParam(value="email", required = false) String email,
     @RequestParam(value="password", required = false) String password, 
     @RequestParam(value="gameId", required = false) String gameId,
     HttpServletRequest request, HttpServletResponse response) {
        out.printf("[get user request] identifier: %s, username: %s, email: %s, password: %s, gameId: %s\n", identifier, username, email, password, gameId);
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String, Object> result = new HashMap<>();
        User user = null;
        if (identifier != null) {
            username = identifier;
            email = identifier;
            gameId = identifier;
        }
        user = repository.findUserByUsernameOrEmailOrGameId(username, email, gameId);
        if (user != null) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        System.out.printf("[user result] username: %s\n", user.getUsername());
        result.put("user", user);
        return result;
    }

    @PostMapping(value="/users")
    public String createUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        out.printf("[create user request] username: %s, email: %s, password: %s\n", user.getUsername(), user.getEmail(), user.getPassword());
        out.printf("content-type: %s\n", request.getHeader("Content-Type"));
        response.setHeader("Access-Control-Allow-Origin", "*");
        return repository.insertUser(user);
    }

    @GetMapping(value="/users/requests")
    public String getRequests(@RequestParam("username") String username, 
     HttpServletRequest request, HttpServletResponse response) {
        out.printf("content-type: %s\n", request.getHeader("Content-Type"));
        response.setHeader("Access-Control-Allow-Origin", "*");
        return repository.findRequests(username);
    }

    @PatchMapping(value="/users/requests")
    public String updateRequests(@RequestBody User updatedUser, HttpServletRequest request, HttpServletResponse response) {
        //User currentUser = repository.findUserByUsernameOrEmail(updatedUser.getUsername(), null);
        //patcher.patchUser(currentUser, updatedUser);
        System.out.printf("[patch request to update requests] username: %s, requests: %s\n", updatedUser.getUsername(), updatedUser.getRequests());
        String username = updatedUser.getUsername();
        if (username == null) {
            return "username must not be null";
        }
        String requests = updatedUser.getRequests();
        return repository.updateRequests(username, requests);
    }

    @GetMapping(value="/users/friends")
    public String getFriends(@RequestParam("username") String username, 
     HttpServletRequest request, HttpServletResponse response) {
        out.printf("content-type: %s\n", request.getHeader("Content-Type"));
        response.setHeader("Access-Control-Allow-Origin", "*");
        return repository.findFriends(username);
    }

    @PatchMapping(value="/users/friends")
    public String updateFriends(@RequestBody User updatedUser, HttpServletRequest request, HttpServletResponse response) {
        //User currentUser = repository.findUserByUsernameOrEmail(updatedUser.getUsername(), null);
        //patcher.patchUser(currentUser, updatedUser);
        System.out.printf("[patch request to update friends] username: %s, requests: %s\n", updatedUser.getUsername(), updatedUser.getRequests());
        String username = updatedUser.getUsername();
        if (username == null) {
            return "username must not be null";
        }
        String friends = updatedUser.getFriends();
        return repository.updateFriends(username, friends);
    }

}

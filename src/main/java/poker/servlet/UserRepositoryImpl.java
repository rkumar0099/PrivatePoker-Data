package poker.servlet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import jakarta.json.JsonObjectBuilder;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired(required = true)
    private Json json;

    private User fetchUser(String query) {
        System.out.printf("[%s] find user\n", java.time.LocalTime.now());
        try {
            User user = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(User.class));
            System.out.printf("User found, gameId: %s\n", user.getGameId());
            return user;
        }
        catch(Exception e) {
            System.out.printf("exception while finding user\n");
            // ignore
            //e.printStackTrace();
        }
        return null;
    }

    private int updateUser(String query) {
        try {
            int rows = jdbcTemplate.update(query);
            return rows;
        }
        catch(Exception e) {
            // ignore
            System.out.println(e);
        }
        return -1;
    }

    public User findUserByUsernameOrEmailOrGameId(String username, String email, String gameId) {
        if (username == null) {
            username = "NULL";
        }
        if (email == null) {
            email = "NULL";
        }
        if (gameId == null) {
            gameId = "NULL";
        }
        String query = String.format("select * from user where username = '%s' OR email = '%s' OR gameId = '%s'", username, email, gameId);
        return fetchUser(query);
    }

    public String success(boolean value, String data) {
        if (data == null) {
            return json.newJsonObjectBuilder().add("success", value).build().toString();
        }
        return json.newJsonObjectBuilder().add("success", value).add("data", data).build().toString();
        
    }

    public String insertUser(User user) {
        String query = String.format("insert into user (username, email, password) values ('%s', '%s', '%s')", user.getUsername(), user.getEmail(), user.getPassword());
        int rowsAffected = updateUser(query);
        if (rowsAffected > 0) {
            return success(true, null);
        }
        return success(false, null);
    }

    public String findRequests(String username) {
        String query = String.format("select requests from user where username = '%s'", username);
        System.out.printf("[%s] [user=%s] finding requests\n", java.time.LocalTime.now(), username);
        try {
            List<String> requests = jdbcTemplate.queryForList(query, String.class);
            if (requests.size() > 0) {
                String result = requests.get(0);
                if (result.equals("NULL")) {
                    result = null;
                }
                System.out.println(result);
                return success(true, result);
            }
        }
        catch(Exception e) {
            System.out.printf("exception while finding friends\n");
            // ignore
            //e.printStackTrace();
        } 
        return success(false, null);
    }

    public String updateRequests(String username, String requests) {
        if (username == null) {
            return success(false, null);
        }
        String query = "";
        if (requests == null) {
            query = String.format("update user set requests = null where username = '%s'", username);
        } else {
            query = String.format("update user set requests = '%s' where username = '%s'", requests, username);
        }
       
        int rowsAffected = updateUser(query);
        if (rowsAffected > 0) {
            return success(true, null);
        }
        return success(false, null);
    }

    public String findFriends(String username) {
        String query = String.format("select friends from user where username = '%s'", username);
        System.out.printf("[%s] [user=%s] finding friends\n", java.time.LocalTime.now(), username);
        try {
            List<String> requests = jdbcTemplate.queryForList(query, String.class);
            if (requests.size() > 0) {
                String result = requests.get(0);
                //if (result != null && result.equals("NULL")) {
                //    result = null;
                //}
                System.out.println(result);
                return success(true, result);
            }
        }
        catch(Exception e) {
            System.out.printf("exception while finding friends\n");
            System.out.println(e);
            // ignore
            //e.printStackTrace();
        } 
        return success(false, null);
    }

    public String updateFriends(String username, String friends) {
        if (username == null) {
            return success(false, null);
        }
        String query = "";
        if (friends == null) {
            query = String.format("update user set friends = null where username = '%s'", username);
        } else {
            query = String.format("update user set friends = '%s' where username = '%s'", friends, username);
        }
       
        int rowsAffected = updateUser(query);
        if (rowsAffected > 0) {
            return success(true, null);
        }
        return success(false, null);
    }


}
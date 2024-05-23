package poker.servlet;

public class User {
    private String username;
    private String email;
    private String password;
    private String picture;
    private String balance;
    private String friends;
    private String requests;
    private String gameId;


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPicture() {
        return picture;
    }

    public String getBalance() {
        return balance;
    }

    public String getFriends() {
        return friends;
    }

    public String getRequests() {
        return requests;
    }

    public String getGameId() {
        return gameId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    public void setRequests(String requests) {
        this.requests = requests;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
} 
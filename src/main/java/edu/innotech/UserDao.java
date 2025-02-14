package edu.innotech;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createUser(User user) {
        jdbcTemplate.update("INSERT INTO users (username) VALUES (?)", user.getUsername());
    }

    public void createUsers(List<String> usernames) {
        for (String username : usernames) {
            jdbcTemplate.update("INSERT INTO users (username) VALUES (?)", username);
        }
    }

    public void deleteUser(Long id) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }

    public void deleteUserByName(String username) {
        jdbcTemplate.update("DELETE FROM users WHERE username = ?", username);
    }

    public void deleteAllUsers() {
        jdbcTemplate.update("DELETE FROM users");
    }

    public User getUser(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new Object[]{id}, (rs, rowNum) ->
                new User(rs.getLong("id"), rs.getString("username")));
    }

    public User getUserByName(String username) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE username = ?", new Object[]{username}, (rs, rowNum) ->
                new User(rs.getLong("id"), rs.getString("username")));
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM users", (rs, rowNum) ->
                new User(rs.getLong("id"), rs.getString("username")));
    }

}

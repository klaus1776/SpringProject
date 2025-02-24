package edu.innotech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into users (username) values (:username)", nativeQuery = true)
    void createUser(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "delete from users where username = :username", nativeQuery = true)
    void deleteUserByUsername(@Param("username") String username);

    @Transactional
    default void createUsers(List<String> usernames) {
        JdbcTemplate jdbcTemplate;
        List<User> entities = new ArrayList<>();

        for (String username : usernames) {
            User user = new User();
            user.setUsername(username);
            entities.add(user);
        }

        saveAll(entities);
    }

    @Modifying
    @Transactional
    @Query(value = "delete from users", nativeQuery = true)
    void deleteAllUsers();

    @Modifying
    @Transactional
    @Query(value = "delete from users where username in :usernames", nativeQuery = true)
    void deleteUsersByName(@Param("usernames") List<String> usernames);

    @Query(value = "select * from users where username =:username", nativeQuery = true)
    Optional<User> findUserByUsername(@Param("username") String username);

    @Query(value = "select * from users where username in :usernames", nativeQuery = true)
    Optional<List<User>> findUsersByUsername(@Param("usernames") List<String> usernames);

}

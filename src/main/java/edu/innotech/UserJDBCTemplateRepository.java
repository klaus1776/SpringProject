package edu.innotech;

import jakarta.transaction.Transactional;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserJDBCTemplateRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserJDBCTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    void createUsers(List<String> usernames) {
        List<User> entities = new ArrayList<>();

        for (String username : usernames) {
            User user = new User();
            user.setUsername(username);
            entities.add(user);
        }

        String sql = "INSERT INTO users (username) VALUES (?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                User entity = entities.get(i);
                ps.setString(1, entity.getUsername());
            }

            @Override
            public int getBatchSize() {
                return entities.size();
            }
        });

    }
}

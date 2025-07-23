package repository;

import model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class UserRepository {
    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    public void save(User user) {
        String sql = "INSERT INTO users(name, email) VALUES (?, ?)";
        jdbc.update(sql, user.getName(), user.getEmail());
    }

    public List<User> findAll() {
        return jdbc.query("SELECT * FROM users", userRowMapper());
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email")
        );
    }
}

package com.example.springLab5.repository.jdbc;

import com.example.springLab5.entity.MenuItems;
import com.example.springLab5.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Primary
public class JdbcMenuRepository implements MenuRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcMenuRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<MenuItems> getMenu() {
        return jdbcTemplate.query(
                "select * from menuItems",
                (rs, rowNum) ->
                        new MenuItems(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getInt("price")
                        )
        );
    }
}

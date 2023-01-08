package com.example.springLab5.repository.jdbc;

import com.example.springLab5.entity.MenuItems;
import com.example.springLab5.repository.MenuItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class JdbcMenuItemsRepository implements MenuItemsRepository {
    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;

    @Autowired
    public JdbcMenuItemsRepository(DataSource dataSource, TransactionTemplate transactionTemplate) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public Optional<MenuItems> findById(Long id) {
        List<MenuItems> menuItems = jdbcTemplate.query(
                "select * from menuItems where id = ?",
                this::mapRow, id
        );
        if (menuItems.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(menuItems.get(0));
    }

    @Override
    public List<MenuItems> getMenuItems() {
        return jdbcTemplate.query(
                "select * from menuItems",
                this::mapRow
        );
    }

    @Override
    public List<MenuItems> findAll(Integer price, String description) {
        return null;
    }

    @Override
    public List<MenuItems> findAll(Integer price) {
        return jdbcTemplate.query(
                "select * from menuItems where price = ?",
                this::mapRow
        );
    }

    @Override
    public List<MenuItems> findPaginated(Integer price, String description, Integer page, Integer size) {
        return getMenuItems();
    }

    @Override
    public void save(MenuItems menuItem) {
        transactionTemplate.execute(status -> {
            KeyHolder key = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(
                        "insert into menuItems (name, description, price) values(?,?,?)",
                        Statement.RETURN_GENERATED_KEYS
                );
                ps.setString(1, menuItem.getName());
                ps.setString(2, menuItem.getDescription());
                ps.setInt(3, menuItem.getPrice());
                return ps;
            }, key);

            jdbcTemplate.update(
                    "insert into menuItems (menu_item_id) values(?)",
                    key.getKeyList().get(0).get("id"));
            return true;
        });
    }

    @Override
    public void update(MenuItems updatedMenuItem) {
        transactionTemplate.execute(status -> {
            Optional<MenuItems> optionalMenuItem = findById(updatedMenuItem.getId());
            if (optionalMenuItem.isEmpty()) {
                return false;
            }
            Long menuItemId = updatedMenuItem.getId();
            jdbcTemplate.update("update menu_items set name = '" +
                    updatedMenuItem.getName() + "' where id = " + menuItemId);
            jdbcTemplate.update("update menu_items set description = '" +
                    updatedMenuItem.getDescription() + "' where id = " + menuItemId);
            jdbcTemplate.update("update menu_items set price = '" +
                    updatedMenuItem.getPrice() + "' where id = " + menuItemId);
            return true;
        });
    }

    @Override
    public boolean delete(MenuItems menuItem) {
        return Boolean.TRUE.equals(transactionTemplate.execute(status -> {
            jdbcTemplate.update("delete from menuItems where id = " + menuItem.getId());
            return true;
        }));
    }

    private MenuItems mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new MenuItems(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getInt("price")
        );
    }
}

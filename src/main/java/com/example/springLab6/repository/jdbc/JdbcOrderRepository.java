package com.example.springLab6.repository.jdbc;

import com.example.springLab6.entity.Order;
import com.example.springLab6.repository.MenuItemsRepository;
import com.example.springLab6.repository.OrderRepository;
import com.example.springLab6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class JdbcOrderRepository implements OrderRepository {
    private final TransactionTemplate transactionTemplate;
    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;
    private final MenuItemsRepository menuItemsRepository;

    @Autowired
    public JdbcOrderRepository(TransactionTemplate transactionTemplate, UserRepository userRepository,
                               MenuItemsRepository menuItemsRepository, DataSource dataSource) {
        this.transactionTemplate = transactionTemplate;
        this.userRepository = userRepository;
        this.menuItemsRepository = menuItemsRepository;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Order> findById(Long id) {
        List<Order> orders = jdbcTemplate.query(
                "select * from orders where id = ?",
                this::mapRow, id
        );
        if (orders.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(orders.get(0));
    }

    @Override
    public List<Order> getOrders() {
        return jdbcTemplate.query(
                "select * from orders",
                (rs, rowNum) ->
                        new Order(
                                rs.getLong("id"),
                                userRepository.findById(rs.getLong("user_id")).orElse(null),
                                menuItemsRepository.findById(rs.getLong("menu_item_id")).orElse(null)
                        )
        );
    }

    @Override
    public boolean delete(Order order) {
        return Boolean.TRUE.equals(transactionTemplate.execute(status -> {
            jdbcTemplate.update("delete from orders where id = " + order.getId());
            jdbcTemplate.update("delete from menuItems where id = " + order.getMenuItems().getId());
            return true;
        }));
    }

    private Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Order(
                rs.getLong("id"),
                userRepository.findById(rs.getLong("user_id")).orElse(null),
                menuItemsRepository.findById(rs.getLong("menu_item_id")).orElse(null)
        );
    }
}

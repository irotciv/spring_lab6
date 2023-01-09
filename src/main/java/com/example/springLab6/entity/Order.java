package com.example.springLab6.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "orders")
@org.hibernate.annotations.NamedQuery(name = "Order.getOrders", query = "FROM Order")
public class Order {
    @Id
    @SequenceGenerator(name = "order_id_seq_gen", sequenceName = "order_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq_gen")
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "menu_items_id", referencedColumnName = "id", nullable = false)
    private MenuItems menuItems;

}

package com.example.springLab5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "menu_items")
public class MenuItems {
    @Id
    @SequenceGenerator(name = "menu_item_id_seq_gen", sequenceName = "menu_item_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_item_id_seq_gen")
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Size(min = 1)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(min = 1)
    @Column(name = "description", nullable = false)
    private String description;

    @Min(1)
    @Column(name = "price", nullable = false)
    private Integer price;

    @JsonIgnore
    @OneToOne(mappedBy = "menu_item")
    private Order order;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
}

package com.example.springLab6.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import nonapi.io.github.classgraph.json.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {
    @JsonIgnore
    @Id
    @SequenceGenerator(name = "user_id_seq_gen", sequenceName = "user_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq_gen")
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(name = "password", nullable = false, unique = true)
    private String password;

    @NotNull
    @Size(min = 1)
    @Column(name = "firstName")
    private String firstName;

    @NotNull
    @Size(min = 1)
    @Column(name = "lastName")
    private String lastName;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders;
}

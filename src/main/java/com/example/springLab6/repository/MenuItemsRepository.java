package com.example.springLab6.repository;

import com.example.springLab6.entity.MenuItems;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemsRepository extends CrudRepository<MenuItems, Long> {
    List<MenuItems> getMenuItems();
    List<MenuItems> findAll(Integer price, String description);
    @Query(value = "SELECT u FROM MenuItems u WHERE (:name is null OR u.name = :name)" +
            " AND (:description is null OR u.description = :description)" +
            " AND (:price is null OR u.price = :price)")
    Page<MenuItems> findPaginated(Pageable pageable,
                                  @Param("name") String name,
                                  @Param("description") String description,
                                  @Param("price") Integer price);
}

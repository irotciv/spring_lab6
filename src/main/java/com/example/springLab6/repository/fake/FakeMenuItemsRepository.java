package com.example.springLab6.repository.fake;

import com.example.springLab6.entity.MenuItems;
import com.example.springLab6.repository.MenuItemsRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Getter
@Setter
public class FakeMenuItemsRepository implements MenuItemsRepository {
    private final List<MenuItems> menuItems = new ArrayList<>();

    public FakeMenuItemsRepository() {
        menuItems.add(new MenuItems(0L, "Dessert 1", "milk, eggs, sugar, chocolate", 200));
        menuItems.add(new MenuItems(1L, "Dessert 2", "milk, eggs, sugar, strawberry", 300));
    }

    @Override
    public Optional<MenuItems> findById(Long id) {
        return menuItems.stream().filter(menuItem -> id.equals(menuItem.getId())).findAny();
    }


    @Override
    public List<MenuItems> getMenuItems() {
        return menuItems;
    }

    @Override
    public List<MenuItems> findAll(Integer price, String description) {
        List<MenuItems> menuItems = new ArrayList<>();
        for (MenuItems menuItem : getMenuItems()) {
            if (description == null && menuItem.getPrice().equals(price)) {
                menuItems.add(menuItem);
            }
            if (description != null && price == null && menuItem.getDescription().contains(description)) {
                menuItems.add(menuItem);
            }
            if ((description != null && menuItem.getDescription().contains(description))
                    && (menuItem.getPrice().equals(price))) {
                menuItems.add(menuItem);
            }
        }
        if (price == null && description == null)
            return getMenuItems();
        return menuItems;
    }

    @Override
    public List<MenuItems> findPaginated(Integer price, String description, Integer page, Integer size) {
        List<MenuItems> menuItems = findAll(price, description);
        int startIndex = page * size;
        int endIndex = page * size + size;
        if (startIndex > menuItems.size() || endIndex > menuItems.size()) {
            startIndex = Math.min(startIndex, menuItems.size());
            endIndex = menuItems.size();
        }
        return menuItems.subList(startIndex, endIndex);
    }

    @Override
    public void save(MenuItems menuItem) {
        menuItems.add(menuItem);
    }

    @Override
    public void update(MenuItems menuItem) {
        if (menuItems.contains(menuItem)) {
        }
        else {
            menuItems.add(menuItem);
        }
    }

    @Override
    public boolean delete(MenuItems menuItem) {
        return menuItems.remove(menuItem);
    }
}

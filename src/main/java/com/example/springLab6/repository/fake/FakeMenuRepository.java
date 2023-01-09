package com.example.springLab6.repository.fake;

import com.example.springLab6.entity.MenuItems;
import com.example.springLab6.repository.MenuItemsRepository;
import com.example.springLab6.repository.MenuRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Getter
@Setter
public class FakeMenuRepository implements MenuRepository {
    private MenuItemsRepository fakeMenuItemsRepository;

    public FakeMenuRepository(MenuItemsRepository fakeMenuItemsRepository) {
        this.fakeMenuItemsRepository = fakeMenuItemsRepository;
    }

    @Override
    public List<MenuItems> getMenu() {
        return fakeMenuItemsRepository.getMenuItems();
    }
}

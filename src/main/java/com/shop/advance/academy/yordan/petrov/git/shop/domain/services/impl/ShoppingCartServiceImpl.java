package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ItemDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ShoppingCartDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.UserDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Item;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ItemCountPair;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ShoppingCart;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.User;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ItemCountPairServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ShoppingCartServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ShoppingCartServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.UserServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ShoppingCartService;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.UserService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartDao shoppingCartDao;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final UserDao userDao;
    private final ItemDao itemDao;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao, ModelMapper modelMapper,
                                   UserService userService, UserDao userDao, ItemDao itemDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.userDao = userDao;
        this.itemDao = itemDao;
    }

    @Override
    public ShoppingCartServiceViewModel createShoppingCart(ShoppingCartServiceModel shoppingCartServiceModel) {
        ShoppingCart shoppingCart = mapShoppingCartServiceModelToShoppingCartViewModel(shoppingCartServiceModel);
        setShoppingCartToUser(shoppingCartServiceModel, shoppingCart);
        shoppingCart.setItemCountPair(createItemCountPair(shoppingCartServiceModel.getItemCountPair()));
        shoppingCart.setTotalItemsPrice(getTotalForAllItemCountPair(shoppingCart.getItemCountPair()));
        shoppingCart.setCreated(LocalDateTime.now());
        shoppingCart.setModified(LocalDateTime.now());
        return mapShoppingCartToShoppingCartServiceViewModel(saveShoppingCart(shoppingCart));
    }

    @Override
    @Transactional
    public ShoppingCartServiceViewModel updateShoppingCart(ShoppingCartServiceModel shoppingCartServiceModel) {
        ShoppingCart shoppingCart = mapShoppingCartServiceModelToShoppingCartViewModel(shoppingCartServiceModel);
        shoppingCart.setModified(LocalDateTime.now());
        findShoppingCardById(shoppingCartServiceModel.getId());
        return mapShoppingCartToShoppingCartServiceViewModel(saveShoppingCart(shoppingCart));
    }


    @Override
    public ShoppingCartServiceViewModel getShoppingCartById(long id) {
        return mapShoppingCartToShoppingCartServiceViewModel(findShoppingCardById(id));
    }

    @Override
    public List<ShoppingCartServiceViewModel> getAllShoppingCarts() {
        validateIfAnyShoppingCarsArePresent();
        List<ShoppingCart> shoppingCarts = findAllShoppingCarts();
        return mapListShoppingCartToShoppingCartViewModel(shoppingCarts);
    }

    @Override
    public ShoppingCartServiceViewModel deleteShoppingCartById(long id) {
        findShoppingCardById(id);
        ShoppingCartServiceViewModel deletedShoppingCart = this.getShoppingCartById(id);
        this.shoppingCartDao.deleteById(id);
        return deletedShoppingCart;
    }

    public BigDecimal calculateTotalPrice(Integer itemCount, BigDecimal itemPrice) {

        return itemPrice.multiply(BigDecimal.valueOf(itemCount));
    }

    private ShoppingCartServiceViewModel mapShoppingCartToShoppingCartServiceViewModel(ShoppingCart shoppingCart) {
        return this.modelMapper.map(shoppingCart, ShoppingCartServiceViewModel.class);
    }

    public User mapUserServiceViewModelToUser(UserServiceViewModel userServiceViewModel) {
        return this.modelMapper.map(userServiceViewModel, User.class);
    }

    public ShoppingCart findShoppingCardById(long id) {
        return this.shoppingCartDao.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Shopping cart with ID %s not found.", id)));
    }

    public List<ShoppingCart> findAllShoppingCarts() {
        return shoppingCartDao.findAll();
    }

    public List<ShoppingCartServiceViewModel> mapListShoppingCartToShoppingCartViewModel(List<ShoppingCart> shoppingCarts) {
        return modelMapper.map(shoppingCarts, new TypeToken<List<ShoppingCartServiceViewModel>>() {
        }.getType());
    }

    public void validateIfAnyShoppingCarsArePresent() {
        findAllShoppingCarts()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Shopping carts were found"));
    }

    public void setShoppingCartToUser(ShoppingCartServiceModel shoppingCartServiceModel, ShoppingCart shoppingCart) {
        userDao.findById(shoppingCartServiceModel.getUser().getId())
                .ifPresent(c -> {
                    shoppingCart.setUser(mapUserServiceViewModelToUser(getUserById(shoppingCartServiceModel)));
                });
    }

    public UserServiceViewModel getUserById(ShoppingCartServiceModel shoppingCartServiceModel) {
        return this.userService.getUserById(shoppingCartServiceModel.getUser().getId());
    }

    public ShoppingCart mapShoppingCartServiceModelToShoppingCartViewModel(ShoppingCartServiceModel shoppingCartServiceModel) {
        return this.modelMapper.map(shoppingCartServiceModel, ShoppingCart.class);
    }

    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart) {
        return this.shoppingCartDao.saveAndFlush(shoppingCart);
    }


    public BigDecimal getTotalForAllItemCountPair(List<ItemCountPair> itemCountPairList) {
        BigDecimal totalPerShoppingCart = new BigDecimal(0);

        List<BigDecimal> res = new ArrayList<>();

        itemCountPairList.forEach(icp -> {
            Integer itemCount = icp.getItemCount();
            BigDecimal itemPrice = icp.getItem().getPrice();
            BigDecimal totalSumPerItem = calculateTotalPrice(itemCount, itemPrice);
            res.add(totalSumPerItem);
        });

        for (BigDecimal re : res) {
            totalPerShoppingCart = totalPerShoppingCart.add(re);
        }

        return totalPerShoppingCart;
    }

    @Transactional
    public List<ItemCountPair> createItemCountPair(
            List<ItemCountPairServiceModel> itemCountPairServiceModelList) {

        List<ItemCountPair> pairList = new ArrayList<>();
        for (ItemCountPairServiceModel itemCountPairServiceModel : itemCountPairServiceModelList) {
            ItemCountPair itemCountPair = new ItemCountPair();
            Long itemId = itemCountPairServiceModel
                    .getItem()
                    .getId();
            Item item = itemDao
                    .findById(itemId)
                    .orElseThrow();
            Integer itemCount = itemCountPairServiceModel.getItemCount();
            itemCountPair.setItem(item);
            itemCountPair.setItemCount(itemCount);
            pairList.add(itemCountPair);
        }
        return pairList;
    }
}

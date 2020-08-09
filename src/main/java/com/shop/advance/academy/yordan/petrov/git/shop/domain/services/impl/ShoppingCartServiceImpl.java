package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ItemDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ShoppingCartDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.UserDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Item;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.ItemCountPair;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.ShoppingCart;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.User;
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
/**
 * Class interface service implementation  for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartDao shoppingCartDao;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final UserDao userDao;
    private final ItemDao itemDao;

    /**
     * Constructor
     */
    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao, ModelMapper modelMapper,
                                   UserService userService, UserDao userDao, ItemDao itemDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.userDao = userDao;
        this.itemDao = itemDao;
    }

    /**
     * @param shoppingCartServiceModel
     * @return
     */
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

    /**
     * @param shoppingCartServiceModel
     * @return
     */
    @Override
    @Transactional
    public ShoppingCartServiceViewModel updateShoppingCart(ShoppingCartServiceModel shoppingCartServiceModel) {
        ShoppingCart shoppingCart = mapShoppingCartServiceModelToShoppingCartViewModel(shoppingCartServiceModel);
        shoppingCart.setModified(LocalDateTime.now());
        findShoppingCardById(shoppingCartServiceModel.getId());
        return mapShoppingCartToShoppingCartServiceViewModel(saveShoppingCart(shoppingCart));
    }


    /**
     * @param id
     * @return
     */
    @Override
    public ShoppingCartServiceViewModel getShoppingCartById(long id) {
        return mapShoppingCartToShoppingCartServiceViewModel(findShoppingCardById(id));
    }

    /**
     * @return
     */
    @Override
    public List<ShoppingCartServiceViewModel> getAllShoppingCarts() {
        validateIfAnyShoppingCarsArePresent();
        List<ShoppingCart> shoppingCarts = findAllShoppingCarts();
        return mapListShoppingCartToShoppingCartViewModel(shoppingCarts);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ShoppingCartServiceViewModel deleteShoppingCartById(long id) {
        findShoppingCardById(id);
        ShoppingCartServiceViewModel deletedShoppingCart = this.getShoppingCartById(id);
        this.shoppingCartDao.deleteById(id);
        return deletedShoppingCart;
    }

    /**
     * @param itemCount
     * @param itemPrice
     * @return
     */
    public BigDecimal calculateTotalPrice(Integer itemCount, BigDecimal itemPrice) {

        return itemPrice.multiply(BigDecimal.valueOf(itemCount));
    }

    /**
     * @param shoppingCart
     * @return
     */
    private ShoppingCartServiceViewModel mapShoppingCartToShoppingCartServiceViewModel(ShoppingCart shoppingCart) {
        return this.modelMapper.map(shoppingCart, ShoppingCartServiceViewModel.class);
    }

    /**
     * @param userServiceViewModel
     * @return
     */
    public User mapUserServiceViewModelToUser(UserServiceViewModel userServiceViewModel) {
        return this.modelMapper.map(userServiceViewModel, User.class);
    }

    /**
     * @param id
     * @return
     */
    public ShoppingCart findShoppingCardById(long id) {
        return this.shoppingCartDao.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Shopping cart with ID %s not found.", id)));
    }

    /**
     * @return
     */
    public List<ShoppingCart> findAllShoppingCarts() {
        return shoppingCartDao.findAll();
    }

    public List<ShoppingCartServiceViewModel> mapListShoppingCartToShoppingCartViewModel(List<ShoppingCart> shoppingCarts) {
        return modelMapper.map(shoppingCarts, new TypeToken<List<ShoppingCartServiceViewModel>>() {
        }.getType());
    }

    /**
     *
     */
    public void validateIfAnyShoppingCarsArePresent() {
        findAllShoppingCarts()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Shopping carts were found"));
    }

    /**
     * @param shoppingCartServiceModel
     * @param shoppingCart
     */
    public void setShoppingCartToUser(ShoppingCartServiceModel shoppingCartServiceModel, ShoppingCart shoppingCart) {
        userDao.findById(shoppingCartServiceModel.getUser().getId())
                .ifPresent(c -> {
                    shoppingCart.setUser(mapUserServiceViewModelToUser(getUserById(shoppingCartServiceModel)));
                });
    }

    /**
     * @param shoppingCartServiceModel
     * @return
     */
    public UserServiceViewModel getUserById(ShoppingCartServiceModel shoppingCartServiceModel) {
        return this.userService.getUserById(shoppingCartServiceModel.getUser().getId());
    }

    /**
     * @param shoppingCartServiceModel
     * @return
     */
    public ShoppingCart mapShoppingCartServiceModelToShoppingCartViewModel(ShoppingCartServiceModel shoppingCartServiceModel) {
        return this.modelMapper.map(shoppingCartServiceModel, ShoppingCart.class);
    }

    /**
     * @param shoppingCart
     * @return
     */
    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart) {
        return this.shoppingCartDao.saveAndFlush(shoppingCart);
    }


    /**
     * @param itemCountPairList
     * @return
     */
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

    /**
     * @param itemCountPairServiceModelList
     * @return
     */
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

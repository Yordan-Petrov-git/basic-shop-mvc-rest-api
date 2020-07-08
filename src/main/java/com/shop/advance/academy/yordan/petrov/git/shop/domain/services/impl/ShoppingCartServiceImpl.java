package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ItemRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ShoppingCartRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.UserRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Item;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ItemCountPair;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ShoppingCart;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.User;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ItemCountPairServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ShoppingCartServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ShoppingCartServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.UserServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ItemCountPairService;
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
import java.util.stream.Collectors;


@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final ItemCountPairService itemCountPairService;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, ModelMapper modelMapper,
                                   UserService userService, UserRepository userRepository, ItemRepository itemRepository,
                                   ItemCountPairService itemCountPairService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.itemCountPairService = itemCountPairService;
    }

    @Override
    public ShoppingCartServiceViewModel createShoppingCart(ShoppingCartServiceModel shoppingCartServiceModel) {
        ShoppingCart shoppingCart = mapShoppingCartServiceModelToShoppingCartViewModel(shoppingCartServiceModel);
        setShoppingCartToUser(shoppingCartServiceModel, shoppingCart);
        shoppingCart.setItemCountPair(createItemCountPair(shoppingCartServiceModel.getItemCountPair()));
        List<ItemCountPair> itemCountPairs = shoppingCart.getItemCountPair();
        shoppingCart.setTotalItemsPrice(getTotalForAllItemCountPair(itemCountPairs));
        shoppingCart.setCreated(LocalDateTime.now());
        shoppingCart.setModified(LocalDateTime.now());
        return mapShoppingCartToShoppingCartServiceViewModel(saveShoppingCart(shoppingCart));
    }

    @Override
    @Transactional
    public ShoppingCartServiceViewModel updateShoppingCart(ShoppingCartServiceModel shoppingCartServiceModel) {
        ShoppingCart shoppingCart = mapShoppingCartServiceModelToShoppingCartViewModel(shoppingCartServiceModel);
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
        this.shoppingCartRepository.deleteById(id);
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
        return this.shoppingCartRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Shopping cart with ID %s not found.", id)));
    }


    public List<ShoppingCart> findAllShoppingCarts() {
        return shoppingCartRepository.findAll();
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

    public Integer getItemCount(ShoppingCartServiceModel shoppingCartServiceModel) {
        return shoppingCartServiceModel.getItemCountPair()
                .stream()
                .map(ItemCountPairServiceModel::getItemCount)
                .findFirst()
                .orElseThrow(() -> new InvalidEntityException("No item counts were found "));
    }

    public Long getItemCountPairId(ShoppingCartServiceModel shoppingCartServiceModel) {
        return shoppingCartServiceModel.getItemCountPair()
                .stream()
                .map(e -> e.getItem().getId())
                .findFirst()
                .orElseThrow(() -> new InvalidEntityException("No items were found "));
    }

    public void setShoppingCartToUser(ShoppingCartServiceModel shoppingCartServiceModel, ShoppingCart shoppingCart) {
        userRepository.findById(shoppingCartServiceModel.getUser().getId())
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
        return this.shoppingCartRepository.saveAndFlush(shoppingCart);
    }


    public List<Long> getListOfShoppingCrtItemsPairItemId(ShoppingCartServiceModel shoppingCartServiceModel) {
        return shoppingCartServiceModel.getItemCountPair()
                .stream()
                .map(s -> s.getItem().getId())
                .collect(Collectors.toList());
    }

    public List<Item> getListOfShoppingCrtItemsPairItem(List<Long> itemIds) {
        List<Item> itemList = new ArrayList<>();
        itemIds.forEach(id -> {
            itemList.add(itemRepository
                    .findById(id)
                    .orElseThrow(
                            () -> new InvalidEntityException("No items with id's found")
                    ));
        });
        return itemList;
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

    public List<ItemCountPair> createItemCountPair(
            List<ItemCountPairServiceModel> itemCountPairServiceModelList) {

        List<ItemCountPair> pairList = new ArrayList<>();
        for (ItemCountPairServiceModel itemCountPairServiceModel : itemCountPairServiceModelList) {
            ItemCountPair itemCountPair = new ItemCountPair();
            Long itemId = itemCountPairServiceModel
                    .getItem()
                    .getId();
            Item item = itemRepository
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

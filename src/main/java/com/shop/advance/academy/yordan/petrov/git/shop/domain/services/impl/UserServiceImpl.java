package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.repository.ContactInformationRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.repository.RoleRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.repository.UserRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Role;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.User;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.RoleServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.UserServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.UserServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.RoleService;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.UserService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.IllegalDeleteOperation;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ContactInformationRepository contactInformationRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ContactInformationRepository contactInformationRepository,
                           ModelMapper modelMapper, RoleRepository roleRepository, RoleService roleService,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.contactInformationRepository = contactInformationRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserServiceViewModel createUser(@Valid UserServiceModel userServiceModel) {
        User user = maoUserServiceModelToUser(userServiceModel);
        validateIfUsernameExists(user);
        validateIfContactInfoIsDuplicated(userServiceModel);
        addRolesToUsers(user);
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));
        return mapUserToUserServiceViewModel(this.userRepository.saveAndFlush(user));
    }

    @Override
    @Transactional
    public UserServiceViewModel updateUser(@Valid UserServiceModel userServiceModel) {
        User user = maoUserServiceModelToUser(userServiceModel);
        validateIfUsernameExists(user);
        user.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));
        user.setModified(LocalDateTime.now());
        return mapUserToUserServiceViewModel(this.userRepository.saveAndFlush(user));
    }

    @Override
    public UserServiceViewModel getUserById(long id) {
        return mapUserToUserServiceViewModel(findUserById(id));
    }

    @Override
    public List<UserServiceViewModel> getAllUsers() {
        validateIfAnyUsersArePresent();
        return mapUserListToUserServiceViewModel();
    }

    @Override
    @Transactional
    public UserServiceViewModel deleteUserById(long id) {
        UserServiceViewModel deletedUser = getUserServiceViewModelById(id);
        validateIfUserRoleAdminPresentForDeletion(id);
        return deletedUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidEntityException(String.format("No user with username %s", username)));
    }

    @Override
    public UserServiceViewModel getUserByUsername(String username) throws InvalidEntityException {
        return mapUserToUserServiceViewModel(this.userRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidEntityException(String.format("No user with username %s", username))));
    }

    @Override
    public List<UserServiceViewModel> getUserByUsernameLike(String username) {
        //TODO
        return modelMapper.map(this.userRepository.findByUsernameLike(username)
                , new TypeToken<List<UserServiceViewModel>>() {
                }.getType());
    }

    @Override
    public UserServiceViewModel getUserByFirstName(String firstName) {
        //TODO
        return mapUserToUserServiceViewModel(this.userRepository.findByFirstName(firstName)
                .orElseThrow(() -> new InvalidEntityException(String.format("No user with first name %s", firstName))));
    }

    @Override
    public List<UserServiceViewModel> getUserByFirstNameLike(String firstName) {
        //TODO
        return modelMapper.map(this.userRepository.findByLastNameLike(firstName)
                , new TypeToken<List<UserServiceViewModel>>() {
                }.getType());
    }

    @Override
    public UserServiceViewModel getUserByLastName(String lastName) {
        //TODO
        return mapUserToUserServiceViewModel(this.userRepository.findByLastName(lastName)
                .orElseThrow(() -> new InvalidEntityException(String.format("No user with last name %s", lastName))));
    }

    @Override
    public List<UserServiceViewModel> getUserByLastNameLike(String lastName) {
        //TODO
        return modelMapper.map(this.userRepository.findByLastNameLike(lastName)
                , new TypeToken<List<UserServiceViewModel>>() {
                }.getType());
    }

    @Override
    public UserServiceViewModel getUserByFirstNameAndLastName(String firstName, String lastName) {
        //TODO
        return mapUserToUserServiceViewModel(this.userRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new InvalidEntityException(String.format("No user with last name and last name %s %s", firstName, lastName))));
    }

    @Override
    public List<UserServiceViewModel> getUserByFirstNameLikeAndLastNameLike(String firstName, String lastName) {
        //TODO
        return modelMapper.map(this.userRepository.findByFirstNameLikeAndLastNameLike(firstName, lastName)
                , new TypeToken<List<UserServiceViewModel>>() {
                }.getType());
    }

    public List<UserServiceViewModel> mapUserListToUserServiceViewModel() {
        return this.modelMapper.map(findAllUsersFromRepository(), new TypeToken<List<UserServiceViewModel>>() {
        }.getType());
    }

    public void validateIfAnyUsersArePresent() {
        findAllUsersFromRepository()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Users were found"));
    }

    public List<User> findAllUsersFromRepository() {
        return this.userRepository.findAll();
    }

    public void validateIfUserRoleAdminPresentForDeletion(long id) {
        if (userRepository.findById(1L).isPresent() && id == 1) {
            throw new IllegalDeleteOperation("Admin user cannot be deleted");
        } else {
            this.userRepository.deleteById(id);
        }
    }

    public UserServiceViewModel getUserServiceViewModelById(long id) {
        return this.getUserById(id);
    }

    private UserServiceViewModel mapUserToUserServiceViewModel(User user) {
        return this.modelMapper.map(user, UserServiceViewModel.class);
    }

    public User findUserById(long id) {
        return this.userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("User with ID %s not found.", id)));
    }

    public void validateIfUsernameExists(User user) {
        this.userRepository.findByUsername(user.getUsername()).ifPresent(u -> {
            throw new InvalidEntityException(String.format("User with username '%s' already exists.", user.getUsername()));
        });
    }

    public void validateIfContactInfoIsDuplicated(@Valid UserServiceModel userServiceModel) {
        if (contactInformationRepository.count() != 0) {
            this.contactInformationRepository.findByEmail(userServiceModel.getContactInformation()
                    .stream()
                    .findAny()
                    .get().getEmail()).ifPresent(e -> {
                throw new InvalidEntityException(String.format("Email '%s' is  already registered.", e.getEmail()));
            });
            this.contactInformationRepository.findByPhoneNumber(userServiceModel.getContactInformation()
                    .stream()
                    .findAny()
                    .get().getPhoneNumber()).ifPresent(p -> {
                throw new InvalidEntityException(String.format("Phone number : '%s' is  already registered.", p.getPhoneNumber()));
            });
        }
    }

    public User maoUserServiceModelToUser(@Valid UserServiceModel userServiceModel) {
        return this.modelMapper.map(userServiceModel, User.class);
    }


    public void addRolesToUsers(User user) {
        if (userRepository.count() == 0) {
            //Sets 1 st registered user as admin role
            this.roleService.seedRolesInDatabase();

            user.setAuthorities(this.roleService.findAllRoles()
                    .stream()
                    .map(this::mapRoleModelServiceToRole)
                    .collect(Collectors.toSet()));

        } else if (userRepository.count() > 0) {
            //Sets 2 and so on user  as user role
            user.setAuthorities(new LinkedHashSet<>());
            user.getAuthorities().add(authorityToRole());
        }
    }

    public Role authorityToRole() {
        return findRoleByAuthority("ROLE_USER");
    }

    public Role findRoleByAuthority(String authority) {
        return this.roleRepository.findByAuthority(authority);
    }

    public Role mapRoleModelServiceToRole(RoleServiceModel r) {
        return this.modelMapper.map(r, Role.class);
    }

}

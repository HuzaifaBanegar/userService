package com.example.userservice.services;

import com.example.userservice.dtos.UserDTO;
import com.example.userservice.errorHandler.UserNotFound;
import com.example.userservice.models.Address;
import com.example.userservice.models.Name;
import com.example.userservice.models.User;
import com.example.userservice.repositories.AddressRepository;
import com.example.userservice.repositories.NameRepository;
import com.example.userservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("databaseUserService")
public class DatabaseUserService implements UserService {
    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private NameRepository nameRepository;
    public DatabaseUserService(UserRepository userRepository, AddressRepository addressRepository, NameRepository nameRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.nameRepository = nameRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.get();
    }

    @Override
    public User updateUser(Long userId, UserDTO partialUserDTO) throws UserNotFound {
        Optional<User> Optionaluser = userRepository.findUserById(userId);
        if(Optionaluser.isEmpty()){
            throw new UserNotFound("User not found");
        }
        User userToUpdate = Optionaluser.get();
        if(partialUserDTO.getUsername()!=null){
            userToUpdate.setUsername(partialUserDTO.getUsername());
        }
        if(partialUserDTO.getEmail()!=null){
            userToUpdate.setEmail(partialUserDTO.getEmail());
        }
        if(partialUserDTO.getPassword()!=null){
            userToUpdate.setPassword(partialUserDTO.getPassword());
        }
        if(partialUserDTO.getPhone()!=null){
            userToUpdate.setPhone(partialUserDTO.getPhone());
        }
        if(partialUserDTO.getAddress()!=null){
            Address address = new Address();
            address.setCity(partialUserDTO.getAddress().getCity());
            address.setStreet(partialUserDTO.getAddress().getStreet());
            address.setNumber(partialUserDTO.getAddress().getNumber());
            address.setZipcode(partialUserDTO.getAddress().getZipcode());
            address = addressRepository.save(address);
            userToUpdate.setAddress(address);
        }
        if(partialUserDTO.getName()!=null){
            Name name = new Name();
            name.setFirstname(partialUserDTO.getName().getFirstname());
            name.setLastname(partialUserDTO.getName().getLastname());
            name = nameRepository.save(name);
            userToUpdate.setName(name);
        }
        return userRepository.save(userToUpdate);


    }


    @Override
    public User createUser(String username, String email, String password, String phone, String city, String street, int number, String zipcode, String firstname, String lastname) {
        User userDTO = new User();
        userDTO.setUsername(username);
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        userDTO.setPhone(phone);

        Address address = new Address();
        address.setCity(city);
        address.setStreet(street);
        address.setNumber(number);
        address.setZipcode(zipcode);
        address = addressRepository.save(address);
        userDTO.setAddress(address);
        Name name = new Name();
        name.setFirstname(firstname);
        name.setLastname(lastname);
        name = nameRepository.save(name);
        userDTO.setName(name);
        return userRepository.save(userDTO);
    }

    @Override
    public User deleteUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return null;
        }
        User userToDelete = userOptional.get();
        userRepository.delete(userToDelete); // CascadeType.ALL handles related entities
        return userToDelete;
    }
}

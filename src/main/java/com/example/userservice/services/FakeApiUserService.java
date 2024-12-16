package com.example.userservice.services;

import com.example.userservice.dtos.UserDTO;
import com.example.userservice.errorHandler.UserNotFound;
import com.example.userservice.models.Address;
import com.example.userservice.models.Name;
import com.example.userservice.models.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeApiUserService")
public class FakeApiUserService implements UserService {
    private RestTemplate restTemplate;


    public FakeApiUserService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> getUsers() {
        final String URL = "https://fakestoreapi.com/users";
        try{
            ResponseEntity<UserDTO[]> response = restTemplate.getForEntity(URL, UserDTO[].class);

            if(response.getBody()==null){
                return new ArrayList<>();
            }
            List<User> users = new ArrayList<>();
            for(UserDTO userDTO: response.getBody()){
                users.add(userDTO.toUser());
            }

            return users;
        }catch (HttpClientErrorException e) {
            // Handle HTTP errors
            throw new RuntimeException("HTTP error while getting user: " + e.getStatusCode(), e);
        } catch (Exception e) {
            // Handle other exceptions
            throw new RuntimeException("Unexpected error while getting user", e);
        }
    }

    @Override
    public User getUser(Long userId) throws UserNotFound {
       final String URL = "https://fakestoreapi.com/users/" + userId;

       try{
           ResponseEntity<UserDTO> response = restTemplate.getForEntity(URL, UserDTO.class);

           if(response.getBody()==null){
               throw new UserNotFound("User not found");
           }else if (response.getStatusCode().is5xxServerError()){
               throw new UserNotFound("Server error");
           }

           return response.getBody().toUser();
       }catch(Exception e){
           throw new UserNotFound("User not found");
       }

    }

    @Override
    public User updateUser(Long userId, UserDTO partialUserDTO) throws UserNotFound {
        final String URL = "https://fakestoreapi.com/users/" + userId;
        try {
            // Fetch existing user first
            ResponseEntity<UserDTO> existingResponse = restTemplate.exchange(
                    URL,
                    HttpMethod.GET,
                    null,
                    UserDTO.class
            );

            if (existingResponse.getBody() == null) {
                throw new UserNotFound("User Not found");
            }

            // Create a copy of existing user to update
            UserDTO existingUser = existingResponse.getBody();

            // Apply partial updates
            existingUser.updateFrom(partialUserDTO);

            // Prepare request entity with updated user
            HttpEntity<UserDTO> requestEntity = new HttpEntity<>(existingUser);

            // Send PATCH request
            ResponseEntity<UserDTO> response = restTemplate.exchange(
                    URL,
                    HttpMethod.PUT,
                    requestEntity,
                    UserDTO.class
            );

            if (response.getBody() == null) {
                throw new UserNotFound("User Not found");
            }

            return response.getBody().toUser();

        } catch (HttpClientErrorException e) {
            throw new RuntimeException("User Not found");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User createUser(Long userId, String username, String email, String password, String phone, String city, String street, int number, String zipcode, String firstname, String lastname) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }
}

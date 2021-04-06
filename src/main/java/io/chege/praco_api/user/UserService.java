package io.chege.praco_api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User createUser(User user){
        if (userRepository.getUserByEmail(user.getEmail()).isPresent()){
            throw new IllegalStateException("Email already exists");
        }
        else {
            return userRepository.save(user);
        }
    }
     public User getUser(String userId){
        confirmUser(userId);
         Optional<User> user = userRepository.findById(userId);
         if (user.isPresent()){
             return user.get();
         }else{
             throw new IllegalStateException("User not found");
         }
     }

     public User updateUser(String userId, User userDetails){
         confirmUser(userId);
         Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            if (userDetails.getFirstName() != null) {
                user.get().setFirstName(userDetails.getFirstName());
            }
            if (userDetails.getLastName() != null) {
                user.get().setLastName(userDetails.getLastName());
            }
            if (userDetails.getEmail() != null) {
                Optional<User> emailUser = userRepository.getUserByEmailExcept(userDetails.getEmail(), userId);
                if (emailUser.isPresent()){
                    throw new IllegalStateException("Email already exists");
                }
                user.get().setEmail(userDetails.getEmail());
            }
            if (userDetails.getStatus() != null) {
                user.get().setStatus(userDetails.getStatus());
            }
            userRepository.save(user.get());
            return user.get();
        }else{
            throw new IllegalStateException("User not found");
        }
     }

    public void confirmUser(String userId) {
        User currentUser = getCurrentUser();
        if (!currentUser.getId().equals(userId)) {
            throw new IllegalStateException("Invalid user");
        }
    }

     public User getCurrentUser() {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         return userRepository.getUserByEmail(authentication.getName()).get();
     }

     public User findById(String id){
        return userRepository.findById(id).get();
     }
}

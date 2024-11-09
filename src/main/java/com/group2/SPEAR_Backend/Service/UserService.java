package com.group2.SPEAR_Backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.group2.SPEAR_Backend.Model.User;
import com.group2.SPEAR_Backend.Repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    UserRepository spearRepo;

    @Autowired
    private UserRepository userRepository;

    public User createUser(User insert) {
        return spearRepo.save(insert);
    }

    public List<User> seeAllUser() {
        return spearRepo.findAll();
    }

    @SuppressWarnings("finally")
    public User updateUser(int uid, User newUser) {
        User s = new User();
        try {
            s = spearRepo.findById(uid).get();
            s.setFirstname(newUser.getFirstname());
            s.setLastname(newUser.getLastname());
            s.setEmail(newUser.getEmail());
            s.setPassword(newUser.getPassword());

        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("User " + uid + " not found");
        } finally {
            return spearRepo.save(s);
        }
    }

    public String deleteUser(int uid) {
        if (spearRepo.existsById(uid)) {
            spearRepo.deleteById(uid);
            return "User " + uid + " is deleted";
        } else {
            return "User " + uid + " not found";
        }
    }
}


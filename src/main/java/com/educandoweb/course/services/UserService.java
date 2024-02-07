package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.domain.User;
import com.educandoweb.course.dto.UserDTO;
import com.educandoweb.course.repository.UserRepository;
import com.educandoweb.course.services.exception.ObjectNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;
    
    public List<User> findAll() {
        return repo.findAll();
    }

    @SuppressWarnings("null")
    public User findById(String id) {
        Optional<User> user = repo.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ObjectNotFoundException("Object not found");
        }        
    }

    @SuppressWarnings("null")
    public User insert(User obj) {
        return repo.insert(obj);
    }

    @SuppressWarnings("null")
    public void delete(String id) {
        findById(id);
        repo.deleteById(id);
    }

    @SuppressWarnings("null")
    public User update(User obj) {
        Optional<User> newObj = repo.findById(obj.getId());
        updateData(newObj.get(), obj);
        return repo.save(newObj.get());
    }

    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    public User fromDTO(UserDTO objDto) {
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }
}

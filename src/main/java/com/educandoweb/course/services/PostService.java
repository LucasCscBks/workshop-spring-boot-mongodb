package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.domain.Post;
import com.educandoweb.course.repository.PostRepository;
import com.educandoweb.course.services.exception.ObjectNotFoundException;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;
    
    @SuppressWarnings("null")
    public Post findById(String id) {
        Optional<Post> post = repo.findById(id);
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new ObjectNotFoundException("Object not found");
        }        
    }

    public List<Post> findByTitle(String text) {
        return repo.searchTitle(text);
    }
}

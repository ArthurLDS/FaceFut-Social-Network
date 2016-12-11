/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service.Service;

import br.com.crescer.social.entity.Post;
import br.com.crescer.social.service.Repository.PostRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arthur.souza
 */
@Service
public class PostService {
    
    @Autowired
    PostRepository repository;
    
    public Iterable<Post> findAll(){
        return repository.findAll();
    }
    
    public void save(Post post){
        repository.save(post);
    }
    
    public List<Post> findAllByOrderByIdDesc() {
        return repository.findAllByOrderByIdDesc();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service.Repository;

import br.com.crescer.social.entity.entities.Perfil;
import br.com.crescer.social.entity.entities.Post;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author arthur.souza
 */
public interface PostRepository extends PagingAndSortingRepository<Post, Long>{
    
    public List<Post> findAllByOrderByIdDesc();
    
    public Page<Post> findAllByOrderByIdDesc(Pageable pgbl);
    
    public Page<Post> findAllByPerfilAutor(Perfil perfilAutor,Pageable pgbl);
}

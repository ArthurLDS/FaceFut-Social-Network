/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service.Service;

import br.com.crescer.social.entity.entities.Time;
import br.com.crescer.social.service.Repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Arthur
 */
@Service
public class TimeService {
    
    @Autowired
    TimeRepository repository;
    
    public Iterable<Time> findAll(){
        return repository.findAll();
    }
    
    public void save(Time time){
        repository.save(time);
    }
}

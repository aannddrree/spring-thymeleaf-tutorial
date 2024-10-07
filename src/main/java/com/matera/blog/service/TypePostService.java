package com.matera.blog.service;

import com.matera.blog.model.TypePost;
import com.matera.blog.repository.TypePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypePostService {

    @Autowired
    private TypePostRepository typePostRepository;

    public List<TypePost> findAll(){
        return typePostRepository.findAll();
    }
}

package com.matera.blog.repository;

import com.matera.blog.model.Post;
import com.matera.blog.model.TypePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypePostRepository extends JpaRepository<TypePost, Integer> { }

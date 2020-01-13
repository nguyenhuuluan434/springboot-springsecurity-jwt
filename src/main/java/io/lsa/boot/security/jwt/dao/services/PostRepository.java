package io.lsa.boot.security.jwt.dao.services;

import io.lsa.boot.security.jwt.dao.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PostRepository extends CrudRepository<Post, UUID> {
    Post findByTitleLike(String pattern);
}

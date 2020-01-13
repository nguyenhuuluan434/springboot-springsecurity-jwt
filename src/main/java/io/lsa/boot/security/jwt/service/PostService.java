package io.lsa.boot.security.jwt.service;

import io.lsa.boot.security.jwt.exception.AppException;
import io.lsa.boot.security.jwt.models.PostRequest;
import io.lsa.boot.security.jwt.models.PostResponse;

public interface PostService {
    String createPost(PostRequest request) throws AppException;
}

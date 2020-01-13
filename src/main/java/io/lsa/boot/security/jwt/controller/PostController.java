package io.lsa.boot.security.jwt.controller;

import io.lsa.boot.security.jwt.exception.AppException;
import io.lsa.boot.security.jwt.models.PostRequest;
import io.lsa.boot.security.jwt.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ResponseEntity<?> createPosts(@RequestBody PostRequest request) throws Exception {
        try {
            String postId = postService.createPost(request);
            return ResponseEntity.ok(postId);
        } catch (Throwable t) {
            if (t instanceof AppException)
                throw t;
            throw new AppException("could not add post");
        }
    }

}

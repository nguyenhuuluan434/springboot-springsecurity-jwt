package io.lsa.boot.security.jwt.service;

import io.lsa.boot.security.jwt.dao.models.Post;
import io.lsa.boot.security.jwt.dao.services.PostRepository;
import io.lsa.boot.security.jwt.exception.AppException;
import io.lsa.boot.security.jwt.models.PostRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PostServiceImpl implements PostService {
    final static private Logger logger = LogManager.getLogger(PostServiceImpl.class);

    @Autowired
    private PostRepository postRepository;

    @Override
    public String createPost(PostRequest request) throws AppException {
        try {
            if (Objects.isNull(request) || Objects.isNull(request.getTitle()) || request.getTitle().isEmpty() || Objects.isNull(request.getContent()) || request.getContent().isEmpty())
                throw new AppException("invalid request");
            final Post post = new Post(request.getTitle(), request.getContent());
            postRepository.save(post);
            return (Objects.isNull(post.getId()) ? "" : post.getId().toString());
        } catch (Throwable t) {
            logger.error("error ", t);
            throw new AppException("Error when create post", t);
        } finally {
            logger.info("end exe");
        }
    }
}

package io.lsa.boot.security.jwt.dao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Repository
public class PostRepositoryCustomImpl implements PostRepositoryCustom {
    @Autowired
    EntityManager entityManager;
}

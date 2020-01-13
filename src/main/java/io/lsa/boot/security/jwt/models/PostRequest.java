package io.lsa.boot.security.jwt.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostRequest implements Serializable {
    private String title;
    private String content;
}

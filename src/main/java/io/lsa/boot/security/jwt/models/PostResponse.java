package io.lsa.boot.security.jwt.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostResponse implements Serializable {
    private String id;
    private String title;
    private String contain;
}

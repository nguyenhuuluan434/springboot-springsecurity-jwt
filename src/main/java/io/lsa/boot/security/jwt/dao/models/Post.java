package io.lsa.boot.security.jwt.dao.models;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Post")
@Data
public class Post extends BaseEntity {

    @Column(name = "title", length = 4096)
    private String title;

    @Column(name = "content")
    @Type(type = "text")
    private String content;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

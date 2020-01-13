package io.lsa.boot.security.jwt.dao.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            //name of Generator
            generator = "uuid"
    )
    @GenericGenerator(
            //the name what will used for GeneratedValue.generator
            name = "uuid",
            // UUIDHexGenerator is default that’s registered under the uuid name in Hibernate.
            // uuid2 strategy stands for org.hibernate.id.UUIDGenerator what was used as custom uuid generator.
            strategy = "uuid2",
            //if we was use strategy = "uuid2" you need to provide a class implement UUIDGenerator as generator,
            parameters = @Parameter(
                    name = "uuid_gen_strategy_class",
                    value = "io.lsa.boot.security.jwt.dao.utils.SqlUuidGenerationStrategy"
            )
    )
    @Column(name = "id")
    private UUID id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @PrePersist
    void beforePersist(){
        createTime = new Date();
        updateTime = new Date();
    }


}

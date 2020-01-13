package io.lsa.boot.security.jwt.dao.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "TestEntity")
@Table(name = "TestEntity")
@Data
public class TestEntity {
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
    private UUID id;
    private String title;

}

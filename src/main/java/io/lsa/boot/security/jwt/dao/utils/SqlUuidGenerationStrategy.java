package io.lsa.boot.security.jwt.dao.utils;

import org.hibernate.Session;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerationStrategy;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

public class SqlUuidGenerationStrategy implements UUIDGenerationStrategy {


    @Override
    public int getGeneratedVersion() {
        return 1;
    }

    @Override
    public UUID generateUUID(SharedSessionContractImplementor sharedSessionContractImplementor) {
        final UUID[] result = new UUID[1];

        ((Session) sharedSessionContractImplementor).doWork(connection -> {
            try (
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(" SELECT UUID()");) {
                while (resultSet.next()) {
                    result[0] = UUID.fromString((String) resultSet.getObject(1));
                    break;
                }
            } catch (Throwable t) {
                throw new IllegalArgumentException("can't fetch uuid", t);
            }
        });

        return result[0];
    }
}

package de.kontux.smartql;

import java.util.Objects;
import java.util.Properties;

public class DatabaseCredentials {

    private final String host;
    private final String database;
    private final String userName;
    private final String password;
    private final int port;

    public DatabaseCredentials(String host, String database, String userName, String password, int port) {
        Objects.requireNonNull(this.host = host, "Host may not be null!");
        Objects.requireNonNull(this.database = database, "Database name may not be null!");
        Objects.requireNonNull(this.userName = userName, "User name may not be null!");
        this.password = password;
        this.port = port == 0 ? 3306 : port;
    }

    public String getHost() {
        return host;
    }

    public String getDatabase() {
        return database;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    public static DatabaseCredentials fromProperties(Properties properties) {
        String host = properties.getProperty("host");
        String database = properties.getProperty("database");
        String userName = properties.getProperty("username");
        String password = properties.getProperty("password");
        int port = Integer.parseInt(properties.getProperty("port"));

        try {
            return new DatabaseCredentials(host, database, userName, password, port);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Invalid properties!", e);
        }
    }
}

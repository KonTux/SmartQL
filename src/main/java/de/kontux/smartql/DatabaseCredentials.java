package de.kontux.smartql;

public class DatabaseCredentials {

    private final String host;
    private final String database;
    private final String userName;
    private final String password;
    private final int port;

    public DatabaseCredentials(String host, String database, String userName, String password, int port) {
        this.host = host;
        this.database = database;
        this.userName = userName;
        this.password = password;
        this.port = port;
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
}

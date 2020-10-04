package de.kontux.smartql;

import de.kontux.smartql.exception.MySQLConnectException;
import de.kontux.smartql.exception.QueryException;
import de.kontux.smartql.statement.resulted.ResultedStatement;
import de.kontux.smartql.statement.resultless.ResultLessStatement;

import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class MySQL {

    private final ExecutorService threadPool;
    private Connection connection;
    private DatabaseMetaData meta;

    public MySQL(int threads) {
        threadPool = Executors.newFixedThreadPool(threads);
    }

    public void connect(DatabaseCredentials credentials, Runnable whenDone) {
        connect(credentials.getHost(), credentials.getDatabase(), credentials.getUserName(), credentials.getPassword(), credentials.getPort(), whenDone);
    }

    public synchronized void connect(String host, String database, String user, String password, int port, Runnable whenDone) {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", user, password);
            this.meta = connection.getMetaData();
            whenDone.run();
        } catch (SQLException e) {
            throw new MySQLConnectException("Failed to connect to database " + host + ": " + e.getMessage(), e);
        }
    }

    public void update(ResultLessStatement command, Runnable onFinish) {
        if (connection == null) {
            return;
        }

        threadPool.submit(() -> {
            try {
                PreparedStatement statement = connection.prepareStatement(command.constructStatement());
                statement.execute();
                statement.close();

                if (onFinish != null) {
                    onFinish.run();
                }
            } catch (SQLException e) {
                throw new QueryException("Failed to run update statement: " + e.getMessage(), e);
            }
        });
    }

    public void update(ResultLessStatement command) {
        update(command, null);
    }

    public void query(ResultedStatement command, Consumer<QueryResult> callback) {
        if (connection == null) {
            return;
        }

        threadPool.submit(() -> {
            try {
                PreparedStatement statement = connection.prepareStatement(command.constructStatement());
                ResultSet rs = statement.executeQuery();
                callback.accept(new QueryResult(command, rs));
            } catch (SQLException e) {
                throw new QueryException("Failed to run update statement: " + e.getMessage(), e);
            }
        });
    }

    public synchronized void disconnect(boolean force) {
        if (force) {
            threadPool.shutdownNow();
        } else {
            threadPool.shutdown();
            try {
                threadPool.awaitTermination(10L, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException("Could not stop thread pool properly.", e);
            }
        }

        try {
            if (connection == null || connection.isClosed()) {
                return;
            }

            connection.close();
        } catch (SQLException e) {
            throw new MySQLConnectException("Failed to disconnect from database: " + e.getMessage(), e);
        }
    }

    /**
     * Lets you identify whether a column exists in a table.
     *
     * @param table The table name to look up the column in
     * @param name  The column's name
     * @return If the column exists in the given table. Always false if one parameter is null or the table doesn't exist
     */
    public boolean hasColumn(String table, String name) {
        if (name == null || table == null || meta == null) {
            return false;
        }

        try {
            return meta.getColumns(null, null, table, name).next();
        } catch (SQLException e) {
            return false;
        }
    }

    public DatabaseMetaData getMeta() {
        return meta;
    }
}

package de.kontux.smartql.exception;

import java.sql.SQLException;

/**
 * Thrown if an error occurs while connecting to a database
 */
public class MySQLConnectException extends SmartQLException {

    public MySQLConnectException(String message, SQLException cause) {
        super(message, cause);
    }
}

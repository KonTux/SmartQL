package de.kontux.smartql.exception;

import java.sql.SQLException;

/**
 * Thrown if an error occurs while executing a query
 */
public class QueryException extends SmartQLException {

    public QueryException(String message, SQLException cause) {
        super(message, cause);
    }
}

package de.kontux.smartql.exception;

import java.sql.SQLException;

public class SmartQLException extends RuntimeException {

    protected final SQLException cause;

    protected SmartQLException(String message, SQLException cause) {
        super(message, cause);
        this.cause = cause;
    }

    @Override
    public synchronized SQLException getCause() {
        return cause;
    }

}

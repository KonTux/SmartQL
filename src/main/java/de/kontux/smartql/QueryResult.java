package de.kontux.smartql;

import de.kontux.smartql.statement.resulted.ResultedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryResult {

    private final boolean success;
    private final ResultedStatement command;
    private final ResultSet resultSet;

    QueryResult(ResultedStatement command, ResultSet resultSet) {
        this.success = successfulResultSet(resultSet);
        this.resultSet = resultSet;
        this.command = command;

    }

    public Boolean getBoolean(String column) {
        try {
            return resultSet.getBoolean(column);
        } catch (SQLException e) {
            return null;
        }
    }

    public String getString(String column) {
        try {
            return resultSet.getString(column);
        } catch (SQLException e) {
            return null;
        }
    }

    public Integer getInt(String column) {
        try {
            return resultSet.getInt(column);
        } catch (SQLException e) {
            return null;
        }
    }

    public Long getLong(String column) {
        try {
            return resultSet.getLong(column);
        } catch (SQLException e) {
            return null;
        }
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public ResultedStatement getCommand() {
        return command;
    }

    public void relative(int rows) {
        try {
            resultSet.relative(rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isSuccess() {
        return success;
    }

    private boolean successfulResultSet(ResultSet resultSet) {
        try {
            return resultSet != null && resultSet.next();
        } catch (SQLException e) {
            return false;
        }
    }
}

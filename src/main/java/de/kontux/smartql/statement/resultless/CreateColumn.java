package de.kontux.smartql.statement.resultless;

import java.io.Serializable;

public class CreateColumn implements ResultLessStatement {

    private final String table;
    private final String name;
    private final String after;
    private final String type;


    public CreateColumn(String table, String name, String after, String type) {
        this.table = table;
        this.name = name;
        this.after = after;
        this.type = type;
    }

    @Override
    public String constructStatement() {
        return "ALTER TABLE " + table + " ADD COLUMN " + name + " " + type + " AFTER " + after;
    }

    public String getTable() {
        return table;
    }

    public String getAfter() {
        return after;
    }

    public Serializable getType() {
        return type;
    }
}

package de.kontux.smartql.statement.resultless;

public class Update implements ResultLessStatement {

    private final String table;
    private final String column;
    private final String value;
    private final String where;
    private final String condition;

    public Update(String table, String column, String value, String where, String condition) {
        this.table = table;
        this.column = column;
        this.value = value;
        this.where = where;
        this.condition = condition;
    }

    @Override
    public String constructStatement() {
        return "UPDATE " + table + " SET " + column + " = " +
                "'" + value + "' WHERE " + where + " = " +
                "'" + condition + "';";
    }

    public String getTable() {
        return table;
    }

    public String getColumn() {
        return column;
    }

    public String getValue() {
        return value;
    }

    public String getWhere() {
        return where;
    }

    public String getCondition() {
        return condition;
    }
}

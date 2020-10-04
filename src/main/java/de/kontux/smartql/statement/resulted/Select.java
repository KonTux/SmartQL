package de.kontux.smartql.statement.resulted;

public class Select implements ResultedStatement {

    private final String table;
    private final String column;
    private final String where;
    private final String condition;

    public Select(String table, String column, String where, String condition) {
        this.table = table;
        this.column = column;
        this.where = where;
        this.condition = condition;
    }

    @Override
    public String constructStatement() {
        return "SELECT " + column + " FROM " + table + " WHERE " + where + " = " + "'" + condition + "';";
    }

    public String getTable() {
        return table;
    }

    public String getColumn() {
        return column;
    }

    public String getWhere() {
        return where;
    }

    public String getCondition() {
        return condition;
    }
}

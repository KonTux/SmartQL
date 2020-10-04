package de.kontux.smartql.statement.resultless;

public class Delete implements ResultLessStatement {

    private final String table;
    private final String where;
    private final String condition;

    public Delete(String table, String where, String condition) {
        this.table = table;
        this.where = where;
        this.condition = condition;
    }

    @Override
    public String constructStatement() {
        return "DELETE FROM " + table + " WHERE " + where + " = '" + condition + "';";
    }
}

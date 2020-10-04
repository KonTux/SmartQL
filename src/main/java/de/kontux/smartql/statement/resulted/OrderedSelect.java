package de.kontux.smartql.statement.resulted;

public class OrderedSelect implements ResultedStatement {

    private final String table;
    private final String column;
    private final String orderBy;
    private final SortingDirection direction;
    private final int limit;

    public OrderedSelect(String table, String column, String orderBy, SortingDirection direction, int limit) {
        this.table = table;
        this.column = column;
        this.orderBy = orderBy;
        this.direction = direction;
        this.limit = limit;
    }

    @Override
    public String constructStatement() {
        return "SELECT " + column + " FROM " + table + " ORDER BY " + orderBy + " " + direction.getSyntax() + " LIMIT " + limit;
    }

    public enum SortingDirection {
        ASCENDING("ASC"), DESCENDING("DESC");

        private final String syntax;

        SortingDirection(String syntax) {
            this.syntax = syntax;
        }

        public String getSyntax() {
            return syntax;
        }
    }
}

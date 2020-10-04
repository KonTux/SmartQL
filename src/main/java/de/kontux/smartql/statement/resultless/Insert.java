package de.kontux.smartql.statement.resultless;

import java.util.HashMap;

public class Insert implements ResultLessStatement {

    private final String table;
    private final HashMap<String, String> values;

    public Insert(String table, HashMap<String, String> values) {
        this.table = table;
        this.values = values;
    }

    @Override
    public String constructStatement() {
        StringBuilder builder = new StringBuilder();

        builder.append("INSERT INTO ").append(table).append(" (");

        //Add all columns
        int columnCount = 0;
        for (String column : values.keySet()) {
            columnCount++;
            builder.append(column);

            if (columnCount < values.size()) {
                builder.append(", ");
            }
        }

        builder.append(") VALUES (");

        //Add all default values for columns
        int valueCount = 0;
        for (String column : values.keySet()) {
            valueCount++;
            String value = values.get(column);
            builder.append("'").append(value).append("'");

            if (valueCount < values.size()) {
                builder.append(", ");
            }
        }

        builder.append(");");

        return builder.toString();
    }

    public String getTable() {
        return table;
    }

    public HashMap<String, String> getValues() {
        return values;
    }
}

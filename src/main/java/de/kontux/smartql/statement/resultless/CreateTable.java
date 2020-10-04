package de.kontux.smartql.statement.resultless;

import java.util.HashMap;

public class CreateTable implements ResultLessStatement {

    private final String name;
    private final HashMap<String, String> values;

    public CreateTable(String name, HashMap<String, String> values) {
        this.name = name;
        this.values = values;
    }

    @Override
    public String constructStatement() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS ").append(name).append(" (");

        int i = 0;
        for (String value : values.keySet()) {
            i++;
            String type = values.get(value);
            builder.append(value).append(" ").append(type);

            if (i < values.size()) {
                builder.append(", ");
            }
        }

        builder.append(")");
        return builder.toString();
    }

    public String getName() {
        return name;
    }

    public HashMap<String, String> getValues() {
        return values;
    }
}

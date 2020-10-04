package de.kontux.smartql;

public class SQLDataType {

    public static final String BIG_INT = "BIGINT";
    public static final String DATE = "DATE";
    public static final String INT = "int";

    public static String VARCHAR(int length) {
        return "varchar(" + length + ")";
    }
}

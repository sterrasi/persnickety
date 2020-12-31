package org.stepwiselabs.persnickety.schema;

import org.stepwiselabs.flair.exceptions.BadDataException;
import org.stepwiselabs.flair.exceptions.ValidationException;

import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collection;
import java.util.TimeZone;

import static org.stepwiselabs.flair.Preconditions.checkNotBlank;


public abstract class DBColumnType {

    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.RFC_1123_DATE_TIME
            .withZone(ZoneId.systemDefault());

    public abstract String getName();

    public abstract void setStatementValue(PreparedStatement ps, int index, Object value, Column col,
                                           StatementType statementType) throws SQLException;

    public String getPlaceholder(StatementType statementType) {
        return "?";
    }


    /**
     * Enum Type
     */
    public static class EnumDBColumnType extends DBColumnType {

        private final String customType;

        public EnumDBColumnType(String customType) {
            checkNotBlank(customType, "custom enum type");
            this.customType = customType;
        }

        @Override
        public String getName() {
            return "enum";
        }

        @Override
        public void setStatementValue(PreparedStatement ps, int index, Object value, Column col,
                                      StatementType statementType) throws SQLException {

            if (!(value instanceof Integer)) {
                throw BadDataException.create("Expecting Object value to be of type Integer");
            }
            Integer val = (Integer) value;
            if (val == null) {

                if (col.isValueRequired()) {
                    throw ValidationException.create("the %s Integer column is required", col.getName());
                }
                ps.setObject(index, null);
            } else {
                ps.setInt(index, val);
            }
        }

        public String getPlaceholder(StatementType statementType) {
            return String.format("?::\"%s\"", customType);
        }
    }

    /**
     * String Type
     */
    public static class StringDBColumnType extends DBColumnType {

        @Override
        public String getName() {
            return "string";
        }

        @Override
        public void setStatementValue(PreparedStatement ps, int index, Object value, Column col,
                                      StatementType statementType) throws SQLException {

            if (!(value instanceof String)) {
                throw BadDataException.create("Expecting Object value to be of type String");
            }
            String val = (String) value;
            if (val == null) {

                if (col.isValueRequired()) {
                    throw ValidationException.create("the %s String column is required", col.getName());
                }
                ps.setObject(index, null);
            } else {
                ps.setString(index, val);
            }
        }
    }

    /**
     * Integer Type
     */
    public static class IntegerDBColumnType extends DBColumnType {

        @Override
        public String getName() {
            return "integer";
        }

        @Override
        public void setStatementValue(PreparedStatement ps, int index, Object value, Column col,
                                      StatementType statementType) throws SQLException {

            if (!(value instanceof Integer)) {
                throw BadDataException.create("Expecting Object value to be of type Integer");
            }
            Integer val = (Integer) value;
            if (val == null) {

                if (col.isValueRequired()) {
                    throw ValidationException.create("the %s Integer column is required", col.getName());
                }
                ps.setObject(index, null);
            } else {
                ps.setInt(index, val);
            }
        }
    }

    /**
     * Long Type
     */
    public static class LongDBColumnType extends DBColumnType {

        @Override
        public String getName() {
            return "long";
        }

        @Override
        public void setStatementValue(PreparedStatement ps, int index, Object value, Column col,
                                      StatementType statementType) throws SQLException {

            if (!(value instanceof Long)) {
                throw BadDataException.create("Expecting Object value to be of type Long");
            }
            Long val = (Long) value;
            if (val == null) {

                if (col.isValueRequired()) {
                    throw ValidationException.create("the %s Long column is required", col.getName());
                }
                ps.setObject(index, null);
            } else {
                ps.setLong(index, val);
            }
        }
    }

    /**
     * Boolean Type
     */
    public static class BooleanDBColumnType extends DBColumnType {

        @Override
        public String getName() {
            return "boolean";
        }

        @Override
        public void setStatementValue(PreparedStatement ps, int index, Object value, Column col,
                                      StatementType statementType) throws SQLException {

            if (!(value instanceof Boolean)) {
                throw BadDataException.create("Expecting Object value to be of type Boolean");
            }
            Boolean val = (Boolean) value;
            if (val == null) {

                if (col.isValueRequired()) {
                    throw ValidationException.create("the %s Boolean column is required", col.getName());
                }
                ps.setObject(index, null);
            } else {
                ps.setBoolean(index, val);
            }
        }
    }

    /**
     * Timestamp Type
     */
    public static class TimestampDBColumnType extends DBColumnType {

        private static TimeZone UTC = TimeZone.getTimeZone("UTC");

        @Override
        public String getName() {
            return "timestamp";
        }

        @Override
        public void setStatementValue(PreparedStatement ps, int index, Object value, Column col,
                                      StatementType statementType) throws SQLException {

            if (!(value instanceof ZonedDateTime)) {
                throw BadDataException.create("Expecting Object value to be of type ZonedDateTime");
            }
            ZonedDateTime val = (ZonedDateTime) value;
            if (val == null) {

                if (col.isValueRequired()) {
                    ps.setTimestamp(index, Timestamp.from(Instant.now()), Calendar.getInstance(UTC));
                } else {
                    ps.setTimestamp(index, null);

                }
            } else {
                ps.setTimestamp(index, Timestamp.from(val.toInstant()), Calendar.getInstance(UTC));
            }
        }
    }

    /**
     * Integer Array Type
     */
    public static class IntArrayDBColumnType extends DBColumnType {

        @Override
        public String getName() {
            return "int[]";
        }

        @Override
        public void setStatementValue(PreparedStatement ps, int index, Object value, Column col,
                                      StatementType statementType) throws SQLException {

            if (!(value instanceof Collection)) {
                throw BadDataException.create("Expecting Object value to be of type Collection");
            }
            Collection<Integer> val = (Collection<Integer>) value;
            if (val == null) {

                if (col.isValueRequired()) {
                    throw ValidationException.create("the %s integer array column is required", col.getName());
                }
                ps.setObject(index, null);
            } else {
                ps.setArray(index, toArray(ps.getConnection(), val));
            }
        }

        private static Array toArray(Connection conn, Collection<Integer> list) throws SQLException {
            if (list == null || list.isEmpty()) {
                return null;
            }
            return conn.createArrayOf("INTEGER", list.toArray());
        }
    }

    /**
     * String Array Type
     */
    public static class StringArrayDBColumnType extends DBColumnType {

        @Override
        public String getName() {
            return "string[]";
        }

        @Override
        public void setStatementValue(PreparedStatement ps, int index, Object value, Column col,
                                      StatementType statementType) throws SQLException {

            if (!(value instanceof Collection)) {
                throw BadDataException.create("Expecting Object value to be of type Collection");
            }
            Collection<String> val = (Collection<String>) value;
            if (val == null) {

                if (col.isValueRequired()) {
                    throw ValidationException.create("the %s string array column is required", col.getName());
                }
                ps.setObject(index, null);
            } else {
                ps.setArray(index, toArray(ps.getConnection(), val));
            }
        }

        private static Array toArray(Connection conn, Collection<String> list) throws SQLException {
            if (list == null || list.isEmpty()) {
                return null;
            }
            return conn.createArrayOf("VARCHAR", list.toArray());
        }
    }
//
//
//    INT,
//    FLOAT,
//    STRING,
//    DATE,
//    ENUM,
//    ENTITY_ID,
//    STRING_ARRAY,
//    INT_ARRAY;
}

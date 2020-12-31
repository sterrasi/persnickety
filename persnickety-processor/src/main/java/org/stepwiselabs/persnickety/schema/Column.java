package org.stepwiselabs.persnickety.schema;

import java.util.function.Function;

import static org.stepwiselabs.flair.Preconditions.checkNotBlank;
import static org.stepwiselabs.flair.Preconditions.checkNotNull;

public class Column<M> {

    private final String name;
    private final DBColumnType type;
    private final boolean updatable;
    private final boolean insertable;
    private final boolean primaryKey;
    private final boolean valueRequired;
    private final boolean omitColumnIfNull;
    private final Function<M, Object> valueFunction;

    private Column(Builder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.updatable = builder.updatable;
        this.insertable = builder.insertable;
        this.primaryKey = builder.primaryKey;
        this.valueFunction = builder.valueFunction;
        this.valueRequired = builder.valueRequired;
        this.omitColumnIfNull = builder.omitColumnIfNull;
    }

    public DBColumnType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public boolean isUpdatable(M model) {
        return updatable && includeColumn(model);
    }

    public boolean isInsertable(M model) {
        return insertable && includeColumn(model);
    }

    public boolean isValueRequired() {
        return valueRequired;
    }

    private Object getValue(M model) {
        return valueFunction.apply(model);
    }

    private boolean includeColumn(M model) {
        if (getValue(model) == null && omitColumnIfNull) {
            return false;
        }
        return true;
    }

    public class Builder {
        private final String name;
        private DBColumnType type;
        private boolean updatable;
        private boolean insertable;
        private boolean primaryKey;
        private boolean valueRequired;
        private boolean omitColumnIfNull;
        private Function<M, Object> valueFunction;

        public Builder(String name) {
            this.name = name;
        }

        public Builder setType(DBColumnType type) {
            this.type = type;
            return this;
        }

        public Builder setUpdatable(Boolean updatable) {
            this.updatable = updatable;
            return this;
        }

        public Builder setInsertable(Boolean insertable) {
            this.insertable = insertable;
            return this;
        }

        public Builder setPrimaryKey(Boolean primaryKey) {
            this.primaryKey = primaryKey;
            return this;
        }

        public Builder setValueRequired(Boolean required) {
            this.valueRequired = required;
            return this;
        }

        public Builder setOmitColumnIfNull(Boolean omitColumnIfNull) {
            this.omitColumnIfNull = omitColumnIfNull;
            return this;
        }

        public Builder setValueFunction(Function<M, Object> valueFunction) {
            this.valueFunction = valueFunction;
            return this;
        }

        public Column build() {
            checkNotBlank(name, "column name");
            checkNotNull(valueFunction, "column " + name + " value function");
            checkNotNull(type, "column " + name + " db type");
            return new Column(this);
        }

    }


}

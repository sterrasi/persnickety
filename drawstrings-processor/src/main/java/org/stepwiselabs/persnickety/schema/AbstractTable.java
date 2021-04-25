package org.stepwiselabs.persnickety.schema;

import org.stepwiselabs.flair.exceptions.NotFoundException;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class AbstractTable<M> {

    private String name;
    private String alias;
    private Class<M> modelClazz;
    private LinkedHashMap<String, Column<M>> cols;
    private List<TableJoin> joins;


    public AbstractTable(String name, String alias, Class<M> modelClazz) {
        this(name, alias, modelClazz, Collections.emptyList());
    }


    public AbstractTable(String name, String alias, Class<M> modelClazz, List<TableJoin> joins) {
        this.name = name;
        this.alias = alias;
        this.modelClazz = modelClazz;
        this.joins = joins;
        this.cols = createColumns();
    }

    public abstract LinkedHashMap<String, Column<M>> createColumns();

    public Stream<Column<M>> getColumnStream() {
        return cols.values().stream();
    }

    public Column<M> getColumn(String colName) {
        return Optional.ofNullable(cols.get(colName)).orElseThrow(() -> NotFoundException.create(
                "Cannot find column with name '%s' in table '%s'", colName, name));
    }

    public Optional<Column<M>> getPrimaryKeyColumn() {
        return cols.values().stream()
                .filter(c -> c.isPrimaryKey())
                .findFirst();
    }

    public String getAliasedColumnName(String columnName) {
        return String.format("%s.%s", alias, columnName);
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public List<TableJoin> getJoins() {
        return joins;
    }
}

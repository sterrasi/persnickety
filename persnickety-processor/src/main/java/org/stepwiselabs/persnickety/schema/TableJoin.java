package org.stepwiselabs.persnickety.schema;

import ch.qos.logback.core.util.StringCollectionUtil;
import org.stepwiselabs.flair.Strings;

import java.util.ArrayList;
import java.util.List;

public class TableJoin<M> {

//    private final AbstractTable<M> hostTable;
//    private final AbstractTable<M> joinTable;
//    private final List<Constraint> constraints;

    private TableJoin(Builder<M> builder){
//        this.type = builder.type;
//        this.targetTable = builder.targetTable;
//        this.joinTableAlias = builder.joinTableAlias;
//        this.constraints = builder.constraints;
    }

    public List<String> getAliasedSelectFields(){
        return getAliasedSelectFields(null);
    }

    /**
     * This is not complete because a tree needs to be iterated on in order to
     * create the correct aliases.
     * @param parentTargetTableAlias
     * @return
     */
    public List<String> getAliasedSelectFields(String parentTargetTableAlias){

//        String alias = createAlias(parentTargetTableAlias);
//        List<String> results = new ArrayList<>();
//        //results.addAll(targetTable.getSelectFields(alias));
//
//
//        return results;
        return null;

    }

//    private String createAlias(String parentAlias){
//        if (Strings.isBlank(parentAlias)){
//            return joinTableAlias;
//        }
//        if ( joinTableAlias.startsWith(parentAlias)){
//            return joinTableAlias;
//        }
//        return parentAlias + joinTableAlias;
//    }

    private static class JoinConstraint {
        private final String hostTableColName;
        private final String joinTableColName;

        public JoinConstraint(String hostTableColName, String joinTableColName) {
            this.hostTableColName = hostTableColName;
            this.joinTableColName = joinTableColName;
        }

        public String getConstraintSql(String hostTableAlias, String joinTableAlias){
            return String.format("%s.%s=%s.%s", hostTableAlias, hostTableColName, joinTableAlias, joinTableColName);
        }
    }

    private static class Builder<M> {

        private final TableJoinType type;
        private final AbstractTable<M> targetTable;
        private final String joinTableAlias;
        private final List<JoinConstraint> constraints;


        public Builder(AbstractTable<M> targetTable, TableJoinType type, String joinTableAlias) {
            this.type = type;
            this.targetTable = targetTable;
            this.joinTableAlias = joinTableAlias;
            this.constraints = new ArrayList<>();
        }

        public Builder addJoinConstraint(String hostTableColName, String joinTableColName ){
            constraints.add(new JoinConstraint(hostTableColName, joinTableColName));
            return this;
        }

        public TableJoin build(){
            return new TableJoin(this);
        }

    }

}


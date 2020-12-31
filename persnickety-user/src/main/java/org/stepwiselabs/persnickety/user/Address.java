package org.stepwiselabs.persnickety.user;

import org.stepwiselabs.persnickety.annotation.*;

@DBTable("address")
public interface Address {

    @DBColumn("id")
    @PrimaryKey()
    @AutoIncrement()
    Long getId();

    @DBColumn("value")
    @NotNull()
    String getValue();


}

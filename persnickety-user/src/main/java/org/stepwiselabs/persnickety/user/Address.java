package org.stepwiselabs.persnickety.user;

import org.stepwiselabs.persnickety.annotation.*;

@DBTable("address")
public interface Address {

    @DBField("id")
    @PrimaryKey()
    @AutoIncrement()
    Long getId();

    @DBField("value")
    @NotNull()
    String getValue();


}

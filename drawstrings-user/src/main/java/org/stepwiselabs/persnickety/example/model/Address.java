package org.stepwiselabs.persnickety.example.model;

import org.stepwiselabs.persnickety.annotation.*;

public interface Address {

    @Column("id")
    @PrimaryKey()
    @AutoIncrement()
    Long getId();

    @Column("value")
    @NotNull()
    String getValue();

}

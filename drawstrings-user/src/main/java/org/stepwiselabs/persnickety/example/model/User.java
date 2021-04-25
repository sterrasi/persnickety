package org.stepwiselabs.persnickety.example.model;


import org.stepwiselabs.persnickety.annotation.*;

public interface User {

    @Column("id")
    @AutoIncrement()
    @PrimaryKey()
    Long getId();

    @Column("user-name")
    @NotNull()
    String getName();

    @Column("address-id")
    @NotNull()
    @Lazy
    Address getAddress();
}

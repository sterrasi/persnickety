package org.stepwiselabs.persnickety.user;


import org.stepwiselabs.persnickety.annotation.*;

@DBTable("users")
public interface UsersTable {

    @DBColumn("id")
    @AutoIncrement()
    @PrimaryKey()
    Long getId();

    @DBColumn("user-name")
    @NotNull()
    String getName();

    @DBColumn("address")
    @NotNull()
    Address getAddress();
}

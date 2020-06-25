package org.stepwiselabs.persnickety.user;


import org.stepwiselabs.persnickety.annotation.*;

@DBTable("users")
public interface UsersTable {

    @DBField("id")
    @AutoIncrement()
    @PrimaryKey()
    Long getId();

    @DBField("user-name")
    @NotNull()
    String getName();

    @DBField("address")
    @NotNull()
    Address getAddress();
}

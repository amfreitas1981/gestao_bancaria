package com.banking.account.transact.domain.username;

public record DataUsername(Long id, String name, String login, Boolean admin) {

    public DataUsername(Username username) {
        this(username.getId(), username.getName(), username.getLogin(), username.isAdmin());
    }
}

package com.banking.account.transact.domain.username;

public record DataUsername(Long id, String nome, String login, Boolean admin) {

    public DataUsername(Username username) {
        this(username.getId(), username.getNome(), username.getLogin(), username.isAdmin());
    }
}

package com.welop.bank;

public class AccountMembershipException extends Throwable {
    private Account owner;
    private Lobby lobby;

    public Account getOwner() {
        return owner;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public AccountMembershipException(Account owner, Lobby lobby) {
        this.lobby = lobby;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Account " + owner + " has no wallet in lobby " + lobby;
    }
}

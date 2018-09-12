package com.welop.banktest;

import com.welop.bank.*;

public class BankTest {
    public static void main(String[] args) throws LobbyInactiveException, WithdrawException, AccountMembershipException, AlreadyJoinedException {
        System.out.println("This is bank test startup class and getting started guide for Bank Library.");

        Account a1 = new Account("Sergei", "sergei@gmail.com", "qwerty".hashCode());
        Account a2 = new Account("Yuri", "yuri@gmail.com", "abc123".hashCode());
        Account a3 = new Account("Anastacia", "anastacia@gmail.com", "asdfgh".hashCode());
        Account a4 = new Account("Christian", "gray@gmail.com", "dominity".hashCode());

        GameSettings gameSettings = new GameSettings();

        Lobby lobby = a1.createRoom("Cat", gameSettings);
        lobby.addAccount(a2, "Hat");
        lobby.addAccount(a3, "Plane");
        lobby.addAccount(a4, "Special Toy");

        TransactionManager lobbyManager = lobby.getTransactionManager();

        lobbyManager.transfer(a1, a2, 500);
        lobbyManager.withdraw(a3, 300);
        lobbyManager.deposit(a4, 400);
        lobbyManager.withdraw(a2, 3);

        lobbyManager.transfer(a4, a3, 150);

        lobbyManager.go(a1);
        lobbyManager.payEach(a1, 1);
        lobbyManager.payEach(a1, 50);

        lobbyManager.collectFromEveryone(a1, 1);
        lobbyManager.collectFromEveryone(a1, 60);


    }
}

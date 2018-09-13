package com.welop.banktest;

import com.welop.bank.*;

public class BankTest {
    public static void main(String[] args) throws LobbyInactiveException, WithdrawException, AlreadyJoinedException, NonpositiveAmountException {
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


        lobby.transfer(a1, a2, 500);
        lobby.withdraw(a3, 300);
        lobby.deposit(a4, 400);
        lobby.withdraw(a2, 3);

        lobby.transfer(a4, a3, 150);

        lobby.go(a1);
        lobby.payEach(a1, 1);
        lobby.payEach(a1, 50);

        lobby.collectFromEveryone(a1, 1);
        lobby.collectFromEveryone(a1, 60);

        System.out.println("Works fine!");
    }
}

package com.welop.bank;

import java.awt.*;

/**
 * Profile of an account for concrete lobby.
 */
public class Wallet {
    private Account owner;
    private String name; // "Cat", "Hat", "Plane", etc.
    private Lobby lobby;
    private int balance;
    private Boolean isOnline;
    private String color;

    public String getColor() {
        return color;
    }

    void setColor(String color) {
        this.color = color;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    void setOnline(Boolean online) {
        isOnline = online;
    }

    public Lobby getLobby() {
        return lobby;
    }

    /**
     * Returns the wallet owner Account.
     * @return The wallet owner Account.
     */
    Account getOwner() {
        return owner;
    }

    /**
     * Returns wallet name.
     * @return Wallet name.
     */
    public String getName() {
        return name;
    }

    int getBalance() {
        return balance;
    }

    void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Public constructor.
     * @param account Account owner.
     * @param lobby The lobby which is this wallet a member of.
     * @param name The name of this wallet ("Cat", "Hat", "Plane", etc.).
     * @param color Wallet color.
     */
    Wallet(Account account, Lobby lobby, String name, String color) {
        this.owner = account;
        this.name = name;
        this.lobby = lobby;
        this.isOnline = true;
        this.color = color;
    }

    /**
     * Transfers an amount of money from this wallet to stated.
     * @param to The receiver wallet.
     * @param amount Amount to be transferred.
     * @throws WithdrawException Throws when try to transfer more money than it is available.
     */
    void transfer(Wallet to, int amount) throws WithdrawException, NonpositiveAmountException {
        withdraw(amount);
        to.deposit(amount);
    }

    /**
     * Withdraws an amount of money from this wallet.
     * @param amount Amount to be withdrawn.
     * @throws WithdrawException Throws when try to transfer more money than it is available.
     */
    void withdraw(int amount) throws WithdrawException, NonpositiveAmountException {
        if (amount <= 0)
            throw new NonpositiveAmountException(amount);
        if (amount > balance)
            throw new WithdrawException(this, amount - balance);
        balance -= amount;
    }

    /**
     * Deposits an amount of money to this wallet.
     * @param amount Amount to be added.
     */
    void deposit(int amount) throws NonpositiveAmountException {
        if (amount <= 0)
            throw new NonpositiveAmountException(amount);
        balance += amount;
    }

    /**
     * Returns full name of the wallet.
     * @return Full name consisting of Account name and this wallet name.
     */
    @Override
    public String toString() {
        return owner + " the " + name;
    }
}

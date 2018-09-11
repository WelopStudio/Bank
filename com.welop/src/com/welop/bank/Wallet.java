package com.welop.bank;

/**
 * Profile of an account for concrete room.
 */
public class Wallet {
    private Account owner;
    private String name; // "Cat", "Hat", "Plane", etc.
    private Room room;
    private int balance;

    public Room getRoom() {
        return room;
    }

    /**
     * Returns the wallet owner Account.
     * @return The wallet owner Account.
     */
    public Account getOwner() {
        return owner;
    }

    /**
     * Returns wallet name.
     * @return Wallet name.
     */
    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Public constructor.
     * @param account Account owner.
     * @param room The room which is this wallet a member of.
     * @param name The name of this wallet ("Cat", "Hat", "Plane", etc.).
     */
    Wallet(Account account, Room room, String name) {
        this.owner = account;
        this.name = name;
        this.room = room;
    }

    /**
     * Transfers an amount of money from this wallet to stated.
     * @param to The receiver wallet.
     * @param amount Amount to be transferred.
     * @throws WithdrawException Throws when try to transfer more money than it is available.
     */
    void transfer(Wallet to, int amount) throws WithdrawException {
        withdraw(amount);
        to.deposit(amount);
    }

    /**
     * Withdraws an amount of money from this wallet.
     * @param amount Amount to be withdrawn.
     * @throws WithdrawException Throws when try to transfer more money than it is available.
     */
    void withdraw(int amount) throws WithdrawException {
        if (amount > balance)
            throw new WithdrawException(this, amount - balance);
        balance -= amount;
    }

    /**
     * Deposits an amount of money to this wallet.
     * @param amount Amount to be added.
     */
    void deposit(int amount) {
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

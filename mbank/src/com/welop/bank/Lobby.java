package com.welop.bank;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Game room class. This is where several wallets do play Monopoly-like games but not Monopoly.
 */
public class Lobby {
    private Account administrator;
    private HashMap<Account, Wallet> wallets;
    private final GameSettings gameSettings;
    private TransactionManager transactionManager;
    private Boolean isActive;

    /**
     * Returns whether lobby is active or not.
     * @return Whether lobby is active or not.
     */
    public Boolean getActive() {
        for (Wallet w: getWallets().values()) {
            if (!w.getOnline())
                return false;
        }
        return true;
    }

    /**
     * Returns current room administrator reference.
     * @return Current administrator.
     */
    public Account getAdministrator() {
        return administrator;
    }

    /**
     * Sets new administrator.
     * @param administrator New administrator.
     */
    private void setAdministrator(Account administrator) {
        this.administrator = administrator;
    }

    /**
     * Returns game settings connected to this room.
     * @return Game settings connected to this room.
     */
    public GameSettings getGameSettings() {
        return gameSettings;
    }

    /**
     * Returns all wallets of the room.
     * @return ArrayList of wallets involved to current room.
     */
    HashMap<Account, Wallet> getWallets() {
        return wallets;
    }

    /**
     * Adds new account to the room. If successful, also creates room wallet for account.
     * @param player Account of a player to createWalletForRoom.
     * @param name Wallet name for this room.
     */
    public void addAccount(Account player, String name) throws AlreadyJoinedException {
        if (getWallets().containsKey(player))
            throw new AlreadyJoinedException(player, this);
        Wallet wallet = new Wallet(player, this, name, "#CCCCCC");
        player.getWallets().put(this, wallet);
        getWallets().put(player, wallet);
        wallet.setBalance(gameSettings.getStartBalance());
    }

    /**
     * Returns lobby transaction manager.
     * @return Lobby transaction manager.
     */
    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    /**
     * Package-private constructor. Lobby can be created only by an Account instance in order to have administrator properly.
     * @param administrator Creator of the room.
     * @param name Administrator's wallet name.
     * @param gameSettings GameSettings instance to declare room's rules.
     */
    Lobby(Account administrator, String name, GameSettings gameSettings) throws AccountMembershipException, AlreadyJoinedException {
        this.gameSettings = gameSettings;
        wallets = new HashMap<Account, Wallet>();
        addAccount(administrator, name);
        setAdministrator(administrator);
        transactionManager = new TransactionManager(this);
    }

    /**
     * Returns the wallet of stated account in the room.
     * @param owner Owner who's wallet is to be found.
     * @return Wallet of the account in this room.
     * @throws AccountMembershipException Throws if wallet with specified owner was not found.
     */
    @Deprecated
    Wallet walletOf(Account owner) throws AccountMembershipException {
        for (Wallet w: getWallets().values()) {
            if (w.getOwner() == owner)
                return  w;
        }
        throw new AccountMembershipException(owner, this);
    }

    public void deposit(Account to, int amount) throws AccountMembershipException, LobbyInactiveException, NonpositiveAmountException {
        getTransactionManager().deposit(to, amount);
    }

    public void withdraw(Account from, int amount) throws LobbyInactiveException, WithdrawException, AccountMembershipException, NonpositiveAmountException {
        getTransactionManager().withdraw(from, amount);
    }

    public void transfer(Account from, Account to, int amount) throws LobbyInactiveException, WithdrawException, AccountMembershipException, NonpositiveAmountException {
        getTransactionManager().transfer(from, to, amount);
    }

    public void go(Account to) throws AccountMembershipException, LobbyInactiveException, NonpositiveAmountException {
        getTransactionManager().go(to);
    }

    public void luxuryTax(Account from) throws LobbyInactiveException, WithdrawException, AccountMembershipException, NonpositiveAmountException {
        getTransactionManager().luxuryTax(from);
    }

    public void incomeTax(Account from) throws LobbyInactiveException, WithdrawException, AccountMembershipException, NonpositiveAmountException {
        getTransactionManager().incomeTax(from);
    }

    public void payEach(Account from, int amount) throws LobbyInactiveException, AccountMembershipException, WithdrawException, NonpositiveAmountException {
        getTransactionManager().payEach(from, amount);
    }

    public void collectFromEveryone(Account to, int amount) throws LobbyInactiveException, AccountMembershipException, WithdrawException, NonpositiveAmountException {
        getTransactionManager().collectFromEveryone(to, amount);
    }
}

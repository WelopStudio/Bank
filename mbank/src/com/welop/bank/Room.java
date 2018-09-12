package com.welop.bank;

import java.util.ArrayList;

/**
 * Game room class. This is where several wallets do play Monopoly-like games but not Monopoly.
 */
public class Room {
    private Account administrator;
    private ArrayList<Wallet> wallets;
    private final GameSettings gameSettings;
    private TransactionManager transactionManager;

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
    public void setAdministrator(Account administrator) {
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
    public ArrayList<Wallet> getWallets() {
        return wallets;
    }

    /**
     * Adds new account to the room. If successful, also creates room wallet for account.
     * @param player Account of a player to createWalletForRoom.
     * @param name Wallet name for this room.
     */
    public void addPlayer(Account player, String name) {
        try {
            wallets.add(player.createWalletForRoom(this, name));
            walletOf(player).setBalance(gameSettings.getStartBalance());
        } catch (AlreadyJoinedException e) {
            System.err.println(e.getAccount() + " is already member of room " + e.getRoom());
        } catch (AccountMembershipException e) {
            System.err.println(e);
        }
    }

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    /**
     * Package-private constructor. Room can be created only by an Account instance in order to have administrator properly.
     * @param administrator Creator of the room.
     * @param name Administrator's wallet name.
     * @param gameSettings GameSettings instance to declare room's rules.
     */
    Room(Account administrator, String name, GameSettings gameSettings) {
        this.gameSettings = gameSettings;
        wallets = new ArrayList<>();
        addPlayer(administrator, name);
        setAdministrator(administrator);
        transactionManager = new TransactionManager(this);
    }

    /**
     * Returns the wallet of stated account in the room.
     * @param owner Owner who's wallet is to be found.
     * @return Wallet of the account in this room.
     * @throws WalletNotFoundException Throws if wallet with specified owner was not found.
     */
    Wallet walletOf(Account owner) throws AccountMembershipException {
        for (Wallet w: getWallets()) {
            if (w.getOwner() == owner)
                return  w;
        }
        throw new AccountMembershipException(owner, this);
    }
}

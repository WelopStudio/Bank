package com.welop.bank;

import java.util.HashMap;
import java.util.Map;

/**
 * Account class. Account is a real app user.
 */
public class Account {
    private String name;
    private String email;
    private int passwordHashcode;
    private Map<Lobby, Wallet> wallets;

    /**
     * Returns account's name.
     * @return Account's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets account's name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns account's email.
     * @return Account's email.
     */
    String getEmail() {
        return email;
    }

    /**
     * Sets account's email.
     * @param email Account's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns account password hash code.
     * @return Account password hash code.
     */
    int getPasswordHashcode() {
        return passwordHashcode;
    }

    /**
     * All rooms and appropriate wallets of the account.
     * @return Map of Rooms and Wallets.
     */
    public Map<Lobby, Wallet> getWallets() {
        return wallets;
    }

    /**
     * Public constructor of an Account instance.
     * @param name Username that is visible for other accounts.
     * @param email An email connected to the account.
     * @param passwordHashcode Hash code of account password.
     */
    public Account(String name, String email, int passwordHashcode) {
        setName(name);
        setEmail(email);
        this.passwordHashcode = passwordHashcode;
        this.wallets = new HashMap<>();
    }


    /**
     * Called by Lobby.addAccount(Account). Creates wallet for participating caller-lobby and associates that wallet to the lobby.
     * @param lobby Caller-lobby.
     * @param name Wallet name for this lobby.
     * @throws AlreadyJoinedException Throws if the account is already having lobby-wallet association for the caller-lobby.
     */
    Wallet createWalletForRoom(Lobby lobby, String name) throws AlreadyJoinedException {
        if (wallets.containsKey(lobby))
            throw new AlreadyJoinedException(this, lobby);
        Wallet wallet = new Wallet(this, lobby, name);
        wallets.put(lobby, wallet);
        return wallet;
    }

    /**
     * Creates new room and sets its administrator equals to this account.
     * @param name Administrator's wallet name.
     * @return New room reference.
     */
    public Lobby createRoom(String name, GameSettings gameSettings) {
        return new Lobby(this, name, gameSettings);
    }

    @Override
    public String toString() {
        return name;
    }
}

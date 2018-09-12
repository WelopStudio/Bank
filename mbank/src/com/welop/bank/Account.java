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
    private Map<Room, Wallet> wallets;

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
    public Map<Room, Wallet> getWallets() {
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
     * Called by Room.addPlayer(Account). Creates wallet for participating caller-room and associates that wallet to the room.
     * @param room Caller-room.
     * @param name Wallet name for this room.
     * @throws AlreadyJoinedException Throws if the account is already having room-wallet association for the caller-room.
     */
    Wallet createWalletForRoom(Room room, String name) throws AlreadyJoinedException {
        if (wallets.containsKey(room))
            throw new AlreadyJoinedException(this, room);
        Wallet wallet = new Wallet(this, room, name);
        wallets.put(room, wallet);
        return wallet;
    }

    /**
     * Creates new room and sets its administrator equals to this account.
     * @param name Administrator's wallet name.
     * @return New room reference.
     */
    public Room createRoom(String name, GameSettings gameSettings) {
        return new Room(this, name, gameSettings);
    }

    @Override
    public String toString() {
        return name;
    }
}

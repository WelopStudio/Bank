package com.welop.bank;

public class WalletNotFoundException extends Throwable {
    private Wallet wallet;
    private Room room;

    public Wallet getWallet() {
        return wallet;
    }

    public Room getRoom() {
        return room;
    }

    public WalletNotFoundException(Wallet wallet, Room room) {
        this.wallet = wallet;
        this.room = room;
    }
}

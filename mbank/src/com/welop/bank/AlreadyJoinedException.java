package com.welop.bank;

/**
 * Exception which is thrown when try to add player to a party he'd already joined.
 */
public class AlreadyJoinedException extends Throwable {
    private Account account;
    private Room room;

    /**
     * Returns an account caused exception.
     * @return An account which is already member of the room.
     */
    public Account getAccount() {
        return account;
    }

    /**
     * The room that already contains reference to the account.
     * @return Room.
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Exception constructor.
     * @param account The account which is already member of the room.
     * @param room The room with the joined account.
     */
    public AlreadyJoinedException(Account account, Room room) {
        this.account = account;
        this.room = room;
    }
}

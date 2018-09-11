public class AlreadyJoinedException extends Throwable {
    private Account account;
    private Room room;

    public Account getAccount() {
        return account;
    }

    public Room getRoom() {
        return room;
    }

    public AlreadyJoinedException(Account account, Room room) {
        this.account = account;
        this.room = room;
    }
}

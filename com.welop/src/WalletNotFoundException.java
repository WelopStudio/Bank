public class WalletNotFoundException extends Throwable {
    private Account account;
    private Room room;

    public Account getAccount() {
        return account;
    }

    public Room getRoom() {
        return room;
    }

    public WalletNotFoundException(Account account, Room room) {
        this.account = account;
        this.room = room;
    }
}

public class AccountMembershipException extends Throwable {
    private Account owner;
    private Room room;

    public Account getOwner() {
        return owner;
    }

    public Room getRoom() {
        return room;
    }

    public AccountMembershipException(Account owner, Room room) {
        this.room = room;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Account " + owner + " has no wallet in room " + room;
    }
}

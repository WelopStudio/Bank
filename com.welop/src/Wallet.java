public class Wallet {
    private Account owner;

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public Wallet(Account account) {
        setOwner(account);
    }
}

import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionManager {
    private Room room;
    private GameSettings gameSettings;
    private Logger log;

    public TransactionManager(Room room, GameSettings gameSettings) {
        this.room = room;
        this.gameSettings = gameSettings;
        log = Logger.getLogger(TransactionManager.class.getName());
    }

    public void transfer(Account from, Account to, int amount) {
        try {
            room.walletOf(from).transfer(room.walletOf(to), amount);
            System.out.println("Transaction ($" + amount + ") from " + room.walletOf(from) + " to " + room.walletOf(to) + " successful.");
        } catch (WithdrawException | WalletNotFoundException e) {
            System.out.println("Exception: " + e);
        }
    }

    public void withdraw(Account from, int amount) {
        try {
            room.walletOf(from).withdraw(amount);
            System.out.println("Withdrawal ($" + amount + ") from " + room.walletOf(from) + " successful.");
        } catch (WithdrawException | WalletNotFoundException e) {
            System.out.println("Exception: " + e);
        }
    }

    public void deposit(Account to, int amount) {
        try {
            room.walletOf(to).deposit(amount);
            System.out.println("Deposit ($" + amount + ") to " + room.walletOf(to) + " successful.");
        } catch (WalletNotFoundException e) {
            System.out.println("Exception: " + e);
        }
    }
}

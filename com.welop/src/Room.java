import java.util.ArrayList;

/**
 * Game room class. This is where several players do play Monopoly-like games but not Monopoly.
 */
public class Room {
    private Account administrator;
    private ArrayList<Wallet> players;
    private final GameSettings gameSettings;

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
     * Returns all wallets of the room.
     * @return ArrayList of wallets involved to current room.
     */
    public ArrayList<Wallet> getAccounts() {
        return players;
    }

    /**
     * Adds new account to the room. If successful, also creates room wallet for account.
     * @param player Account of a player to join.
     * @param name Wallet name for this room.
     */
    public void addPlayer(Account player, String name) {
        try {
            players.add(player.join(this, name));
        } catch (AlreadyJoinedException e) {
            System.err.println(e.getAccount() + "is already member of room " + e.getRoom());
        }
    }

    /**
     * Package-private constructor. Room can be created only by an Account instance in order to have administrator properly.
     * @param administrator Creator of the room.
     * @param name Administrator's wallet name.
     * @param gameSettings GameSettings instance to declare room's rules.
     */
    Room(Account administrator, String name, GameSettings gameSettings) {
        this.gameSettings = gameSettings;
        players = new ArrayList<>();
        setAdministrator(administrator);
        try {
            administrator.join(this, name);
        } catch (AlreadyJoinedException e) {
            System.err.println(e.getAccount() + "is already member of room " + e.getRoom());
        }
    }
}

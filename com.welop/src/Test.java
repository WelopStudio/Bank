public class Test {
    public static void main(String[] args) {
        System.out.println("This is test startup class and getting started guide for Bank Library.");

        Account a1 = new Account("Sergei", "sergei@gmail.com", "qwerty".hashCode());
        Account a2 = new Account("Yuri", "yuri@gmail.com", "abc123".hashCode());
        Account a3 = new Account("Anastacia", "anastacia@gmail.com", "asdfgh".hashCode());
        Account a4 = new Account("Christian", "gray@gmail.com", "dominity".hashCode());

        GameSettings gameSettings = new GameSettings();

        Room room = a1.createRoom("Cat", gameSettings);
        room.addPlayer(a2, "Hat");
        room.addPlayer(a3, "Plane");
        room.addPlayer(a4, "Special Toy");

        TransactionManager roomManager = room.getTransactionManager();

        roomManager.transfer(a1, a2, 500);
        roomManager.withdraw(a3, 300);
        roomManager.deposit(a4, 400);
        roomManager.withdraw(a2, 2001);

        roomManager.transfer(a4, a3, 150);

        roomManager.go(a1);
    }
}

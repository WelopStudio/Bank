public class Test {
    public static void main(String[] args) {
        System.out.println("This is test startup class and getting started guide for Bank Library.");

        Account a1 = new Account("Sergei", "sergei@gmail.com", "qwerty".hashCode());
        Account a2 = new Account("Yuri", "yuri@gmail.com", "abc123".hashCode());
        Account a3 = new Account("Anastacia", "anastacia@gmail.com", "asdfgh".hashCode());
        Account a4 = new Account("Christian", "gray@gmail.com", "dominity".hashCode());

        Room room = a1.createRoom();
    }
}

package com.welop.bank;

public class TransactionManager {
    private Room room;
    private GameSettings gameSettings;

    TransactionManager(Room room, GameSettings gameSettings) {
        this.room = room;
        this.gameSettings = gameSettings;
    }

    public void transfer(Account from, Account to, int amount) {
        try {
            room.walletOf(from).transfer(room.walletOf(to), amount);
            System.out.println("Transaction ($" + amount + ") from " + room.walletOf(from) + " to " + room.walletOf(to) + " successful.");
        } catch (WithdrawException | AccountMembershipException e) {
            System.out.println("Exception: " + e);
        }
    }

    public void withdraw(Account from, int amount) {
        try {
            room.walletOf(from).withdraw(amount);
            System.out.println("Withdrawal ($" + amount + ") from " + room.walletOf(from) + " successful.");
        } catch (WithdrawException | AccountMembershipException e) {
            System.out.println("Exception: " + e);
        }
    }

    public void deposit(Account to, int amount) {
        try {
            room.walletOf(to).deposit(amount);
            System.out.println("Deposit ($" + amount + ") to " + room.walletOf(to) + " successful.");
        } catch (AccountMembershipException e) {
            System.out.println("Exception: " + e);
        }
    }

    public void transfer(Wallet from, Wallet to, int amount) {
        try {
            if (!room.getWallets().contains(from))
                throw new AccountMembershipException(from.getOwner(), room);
            if (!room.getWallets().contains(to))
                throw new AccountMembershipException(to.getOwner(), room);

            from.transfer(to, amount);
            System.out.println("Transaction ($" + amount + ") from " + from + " to " + to + " successful.");
        } catch (WithdrawException | AccountMembershipException e) {
            System.out.println("Exception: " + e);
        }
    }

    public void withdraw(Wallet from, int amount) {
        try {
            if (!room.getWallets().contains(from))
                throw new WalletNotFoundException(from, room);

            from.withdraw(amount);
            System.out.println("Withdrawal ($" + amount + ") from " + from + " successful.");
        } catch (WithdrawException | WalletNotFoundException e) {
            System.out.println("Exception: " + e);
        }
    }

    public void deposit(Wallet to, int amount) {
        try {
            if (!room.getWallets().contains(to))
                throw new WalletNotFoundException(to, room);

            to.deposit(amount);
            System.out.println("Deposit ($" + amount + ") to " + to + " successful.");
        } catch (WalletNotFoundException e) {
            System.out.println("Exception: " + e);
        }
    }

    public void go(Wallet to) {
        deposit(to, gameSettings.getGoCost());
    }

    public void luxuryTax(Wallet from) {
        withdraw(from, gameSettings.getLuxuryTaxCost());
    }

    public void incomeTax(Wallet from) {
        withdraw(from, gameSettings.getIncomeTaxCost());
    }

    public void go(Account to) {
        deposit(to, gameSettings.getGoCost());
    }

    public void luxuryTax(Account from) {
        withdraw(from, gameSettings.getLuxuryTaxCost());
    }

    public void incomeTax(Account from) {
        withdraw(from, gameSettings.getIncomeTaxCost());
    }

    public void payEach(Account from, int amount) {
        try {
            Wallet walletFrom =  room.walletOf(from);

            if (walletFrom.getBalance() < amount * (room.getWallets().size() - 1))
                throw new WithdrawException(walletFrom, amount * (room.getWallets().size() - 1) - walletFrom.getBalance());

            for (Wallet to: room.getWallets()) {
                if (walletFrom != to)
                    transfer(walletFrom, to, amount);
            }
        } catch (AccountMembershipException | WithdrawException e) {
            System.out.println("Exception: " + e);
        }
    }

    public void collectFromEveryone(Account to, int amount) {
        try {
            Wallet walletTo = room.walletOf(to);
            for (Wallet w : room.getWallets())
                if (walletTo != w)
                    if (w.getBalance() < amount)
                        throw new WithdrawException(w, amount - w.getBalance());
            for (Wallet w : room.getWallets())
                if (walletTo != w)
                    transfer(w, walletTo, amount);
        } catch (WithdrawException | AccountMembershipException e) {
            System.out.println("Exception: " + e);
        }
    }
}

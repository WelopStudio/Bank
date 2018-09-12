package com.welop.bank;

/**
 * Transaction Manager is a Lobby component that provides account instances usage (instead of wallets) to transfer money inside lobby.
 */
public class TransactionManager {
    private Lobby lobby;

    /**
     * Package-private constructor. Called by Lobby constructor.
     * @param lobby Connected lobby.
     */
    TransactionManager(Lobby lobby) {
        this.lobby = lobby;
    }

    /**
     * Transfers money from one Wallet to another using Account instances.
     * @param from Payer Account instance.
     * @param to Receiver Account instance.
     * @param amount Amount of money to be transferred.
     */
    public void transfer(Account from, Account to, int amount) {
        try {
            if (!lobby.getActive())
                throw new LobbyInactiveException(lobby);

            lobby.walletOf(from).transfer(lobby.walletOf(to), amount);
            System.out.println("Transaction ($" + amount + ") from " + lobby.walletOf(from) + " to " + lobby.walletOf(to) + " successful.");
        } catch (WithdrawException | AccountMembershipException | LobbyInactiveException e) {
            System.out.println("Exception: " + e);
        }
    }

    /**
     * Withdraws money from Wallet by Account.
     * @param from Payer Account instance.
     * @param amount Amount of money.
     */
    public void withdraw(Account from, int amount) {
        try {
            if (!lobby.getActive())
                throw new LobbyInactiveException(lobby);

            lobby.walletOf(from).withdraw(amount);
            System.out.println("Withdrawal ($" + amount + ") from " + lobby.walletOf(from) + " successful.");
        } catch (WithdrawException | AccountMembershipException | LobbyInactiveException e) {
            System.out.println("Exception: " + e);
        }
    }

    /**
     * Deposits money to Wallet by Account
     * @param to Receiver Account instance.
     * @param amount Amount of money.
     */
    public void deposit(Account to, int amount) {
        try {
            if (!lobby.getActive())
                throw new LobbyInactiveException(lobby);

            lobby.walletOf(to).deposit(amount);
            System.out.println("Deposit ($" + amount + ") to " + lobby.walletOf(to) + " successful.");
        } catch (AccountMembershipException | LobbyInactiveException e) {
            System.out.println("Exception: " + e);
        }
    }

    /**
     * Deposits "GO" amount of money to the Wallet by Account.
     * @param to Receiver Account instance.
     */
    public void go(Account to) {
        deposit(to, lobby.getGameSettings().getGoCost());
    }

    /**
     * Withdraws LUXURY TAX amount of money from the Wallet by Account.
     * @param from Payer Account instance.
     */
    public void luxuryTax(Account from) {
        withdraw(from, lobby.getGameSettings().getLuxuryTaxCost());
    }

    /**
     * Withdraws INCOME TAX amount of money from the Wallet by Account.
     * @param from Payer Account instance.
     */
    public void incomeTax(Account from) {
        withdraw(from, lobby.getGameSettings().getIncomeTaxCost());
    }

    /**
     * Makes transactions of an amount of money from one wallet to all other.
     * @param from Payer Account instance.
     * @param amount An amount of money to be received by each other wallet.
     */
    public void payEach(Account from, int amount) {
        try {
            Wallet walletFrom = lobby.walletOf(from);

            if (walletFrom.getBalance() < amount * (lobby.getWallets().size() - 1))
                throw new WithdrawException(walletFrom, amount * (lobby.getWallets().size() - 1) - walletFrom.getBalance());

            for (Wallet to : lobby.getWallets()) {
                if (walletFrom != to)
                    transfer(walletFrom, to, amount);
            }
        } catch (AccountMembershipException | WithdrawException e) {
            System.out.println("Exception: " + e);
        }
    }

    /**
     * Makes transactions of an amount of money from all wallets to specified one.
     * @param to Receiver Account instance.
     * @param amount An amount of money to be received by specified wallet.
     */
    public void collectFromEveryone(Account to, int amount) {
        try {
            Wallet walletTo = lobby.walletOf(to);
            for (Wallet w : lobby.getWallets())
                if (walletTo != w)
                    if (w.getBalance() < amount)
                        throw new WithdrawException(w, amount - w.getBalance());
            for (Wallet w : lobby.getWallets())
                if (walletTo != w)
                    transfer(w, walletTo, amount);
        } catch (WithdrawException | AccountMembershipException e) {
            System.out.println("Exception: " + e);
        }
    }

    void transfer(Wallet from, Wallet to, int amount) {
        try {
            if (!lobby.getActive())
                throw new LobbyInactiveException(lobby);
            if (!lobby.getWallets().contains(from))
                throw new AccountMembershipException(from.getOwner(), lobby);
            if (!lobby.getWallets().contains(to))
                throw new AccountMembershipException(to.getOwner(), lobby);

            from.transfer(to, amount);
            System.out.println("Transaction ($" + amount + ") from " + from + " to " + to + " successful.");
        } catch (WithdrawException | AccountMembershipException | LobbyInactiveException e) {
            System.out.println("Exception: " + e);
        }
    }

    void withdraw(Wallet from, int amount) {
        try {
            if (!lobby.getActive())
                throw new LobbyInactiveException(lobby);
            if (!lobby.getWallets().contains(from))
                throw new WalletNotFoundException(from, lobby);

            from.withdraw(amount);
            System.out.println("Withdrawal ($" + amount + ") from " + from + " successful.");
        } catch (WithdrawException | WalletNotFoundException | LobbyInactiveException e) {
            System.out.println("Exception: " + e);
        }
    }

    void deposit(Wallet to, int amount) {
        try {
            if (!lobby.getActive())
                throw new LobbyInactiveException(lobby);
            if (!lobby.getWallets().contains(to))
                throw new WalletNotFoundException(to, lobby);

            to.deposit(amount);
            System.out.println("Deposit ($" + amount + ") to " + to + " successful.");
        } catch (WalletNotFoundException | LobbyInactiveException e) {
            System.out.println("Exception: " + e);
        }
    }

    @Deprecated
    void go(Wallet to) {
        deposit(to, lobby.getGameSettings().getGoCost());
    }

    @Deprecated
    void luxuryTax(Wallet from) {
        withdraw(from, lobby.getGameSettings().getLuxuryTaxCost());
    }

    @Deprecated
    void incomeTax(Wallet from) {
        withdraw(from, lobby.getGameSettings().getIncomeTaxCost());
    }
}

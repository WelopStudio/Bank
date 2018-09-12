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
    public void transfer(Account from, Account to, int amount) throws LobbyInactiveException, AccountMembershipException, WithdrawException, NonpositiveAmountException {
            if (!lobby.getActive())
                throw new LobbyInactiveException(lobby);

            lobby.walletOf(from).transfer(lobby.walletOf(to), amount);
    }

    /**
     * Withdraws money from Wallet by Account.
     * @param from Payer Account instance.
     * @param amount Amount of money.
     */
    public void withdraw(Account from, int amount) throws LobbyInactiveException, AccountMembershipException, WithdrawException, NonpositiveAmountException {
            if (!lobby.getActive())
                throw new LobbyInactiveException(lobby);

            lobby.walletOf(from).withdraw(amount);
    }

    /**
     * Deposits money to Wallet by Account
     * @param to Receiver Account instance.
     * @param amount Amount of money.
     */
    public void deposit(Account to, int amount) throws LobbyInactiveException, AccountMembershipException, NonpositiveAmountException {
            if (!lobby.getActive())
                throw new LobbyInactiveException(lobby);

            lobby.walletOf(to).deposit(amount);
    }

    /**
     * Deposits "GO" amount of money to the Wallet by Account.
     * @param to Receiver Account instance.
     */
    public void go(Account to) throws AccountMembershipException, LobbyInactiveException, NonpositiveAmountException {
        deposit(to, lobby.getGameSettings().getGoCost());
    }

    /**
     * Withdraws LUXURY TAX amount of money from the Wallet by Account.
     * @param from Payer Account instance.
     */
    public void luxuryTax(Account from) throws LobbyInactiveException, WithdrawException, AccountMembershipException, NonpositiveAmountException {
        withdraw(from, lobby.getGameSettings().getLuxuryTaxCost());
    }

    /**
     * Withdraws INCOME TAX amount of money from the Wallet by Account.
     * @param from Payer Account instance.
     */
    public void incomeTax(Account from) throws LobbyInactiveException, WithdrawException, AccountMembershipException, NonpositiveAmountException {
        withdraw(from, lobby.getGameSettings().getIncomeTaxCost());
    }

    /**
     * Makes transactions of an amount of money from one wallet to all other.
     * @param from Payer Account instance.
     * @param amount An amount of money to be received by each other wallet.
     */
    public void payEach(Account from, int amount) throws AccountMembershipException, WithdrawException, LobbyInactiveException, NonpositiveAmountException {
            Wallet walletFrom = lobby.walletOf(from);

            if (walletFrom.getBalance() < amount * (lobby.getWallets().size() - 1))
                throw new WithdrawException(walletFrom, amount * (lobby.getWallets().size() - 1) - walletFrom.getBalance());

            for (Wallet to : lobby.getWallets()) {
                if (walletFrom != to)
                    transfer(walletFrom, to, amount);
            }
    }

    /**
     * Makes transactions of an amount of money from all wallets to specified one.
     * @param to Receiver Account instance.
     * @param amount An amount of money to be received by specified wallet.
     */
    public void collectFromEveryone(Account to, int amount) throws AccountMembershipException, WithdrawException, LobbyInactiveException, NonpositiveAmountException {
            Wallet walletTo = lobby.walletOf(to);
            for (Wallet w : lobby.getWallets())
                if (walletTo != w)
                    if (w.getBalance() < amount)
                        throw new WithdrawException(w, amount - w.getBalance());
            for (Wallet w : lobby.getWallets())
                if (walletTo != w)
                    transfer(w, walletTo, amount);
    }

    void transfer(Wallet from, Wallet to, int amount) throws LobbyInactiveException, AccountMembershipException, WithdrawException, NonpositiveAmountException {
            if (!lobby.getActive())
                throw new LobbyInactiveException(lobby);
            if (!lobby.getWallets().contains(from))
                throw new AccountMembershipException(from.getOwner(), lobby);
            if (!lobby.getWallets().contains(to))
                throw new AccountMembershipException(to.getOwner(), lobby);

            from.transfer(to, amount);
    }

    void withdraw(Wallet from, int amount) throws LobbyInactiveException, WalletNotFoundException, WithdrawException, NonpositiveAmountException {
            if (!lobby.getActive())
                throw new LobbyInactiveException(lobby);
            if (!lobby.getWallets().contains(from))
                throw new WalletNotFoundException(from, lobby);

            from.withdraw(amount);
    }

    void deposit(Wallet to, int amount) throws LobbyInactiveException, WalletNotFoundException, NonpositiveAmountException {
            if (!lobby.getActive())
                throw new LobbyInactiveException(lobby);
            if (!lobby.getWallets().contains(to))
                throw new WalletNotFoundException(to, lobby);

            to.deposit(amount);
    }

    @Deprecated
    void go(Wallet to) throws LobbyInactiveException, WalletNotFoundException, NonpositiveAmountException {
        deposit(to, lobby.getGameSettings().getGoCost());
    }

    @Deprecated
    void luxuryTax(Wallet from) throws WithdrawException, LobbyInactiveException, WalletNotFoundException, NonpositiveAmountException {
        withdraw(from, lobby.getGameSettings().getLuxuryTaxCost());
    }

    @Deprecated
    void incomeTax(Wallet from) throws WithdrawException, LobbyInactiveException, WalletNotFoundException, NonpositiveAmountException {
        withdraw(from, lobby.getGameSettings().getIncomeTaxCost());
    }
}

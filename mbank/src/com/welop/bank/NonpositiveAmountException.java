package com.welop.bank;

public class NonpositiveAmountException extends Throwable {
    private int amount;

    public int getAmount() {
        return amount;
    }

    public NonpositiveAmountException(int amount) {
        this.amount = amount;
    }
}

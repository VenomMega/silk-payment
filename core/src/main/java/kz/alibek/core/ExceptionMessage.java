package kz.alibek.core;

public enum ExceptionMessage {
    CLIENT_NOT_FOUND("Not found client with this ID = " ),
    NOT_ENOUGH_BALANCE_ON_ACCOUNT("Transaction sum is bigger than account's balance");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

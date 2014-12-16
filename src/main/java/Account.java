public class Account {
    private final String accountNumber;
    private final String accountStatus;

    private Account(String accountNumber, String accountStatus) {
        this.accountNumber = accountNumber;
        this.accountStatus = accountStatus;
    }

    public static Account createAccount(final String accountNumber) {
        if (accountNumber == null) throw new IllegalArgumentException();
        if (accountNumber.length() < 9) throw new IllegalArgumentException();
        return new Account(accountNumber, AccountManager.getStatus(accountNumber));
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public String getAccountNumber() {
        return accountNumber;
    }


}

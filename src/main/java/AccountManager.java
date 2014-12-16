import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountManager {
    private static int calculateChecksum(String accountNumber) {
        int sum = 0, m = 0;
        for (int i = accountNumber.length() - 1; i >= 0; i--) {
            sum += Character.getNumericValue(accountNumber.charAt(i)) * (++m);
        }
        return sum % 11;
    }

    protected static String getStatus(String accountNumber) {
        if (!isValid(accountNumber)) return "ILL";
        return calculateChecksum(accountNumber) == 0 ? "VAL" : "ERR";
    }

    private static boolean isValid(String accountNumber) {
        return !accountNumber.contains("?");
    }

    public static List<Account> manipulateAccountNumbers(final List<Account> accounts) {
        final ArrayList<Account> newAccounts = new ArrayList<>();
        for (final Account account : Collections.unmodifiableList(accounts)) {
            if (account.getAccountStatus().equals("ILL")) {
                final String accountNumber = account.getAccountNumber();
                final int firstIndex = accountNumber.indexOf("?");
                final int lastIndex = accountNumber.lastIndexOf("?");
                final int sizeOfDataReplacement = lastIndex < 0 ? 1 : lastIndex - firstIndex + 1;
                int k = 0;
                StringBuilder sb = new StringBuilder();
                while (k < sizeOfDataReplacement) {
                    sb.append("\\?");
                    k++;
                }
                final String stringToSplitUpon = sb.toString();
                final double numberTillIterate = Math.pow(10, sizeOfDataReplacement) - 1;
                final String[] split = accountNumber.split(stringToSplitUpon);
                boolean iterate = true; int numberFromIterate = 0;
                while (numberFromIterate <= numberTillIterate && iterate) {
                    String generatedAccountNumber = split[0] + String.format("%0" + sizeOfDataReplacement + "d", numberFromIterate) + split[1];
                    if (calculateChecksum(generatedAccountNumber) == 0) {
                        newAccounts.add(Account.createAccount(generatedAccountNumber));
                        iterate = false;
                    } else {
                        numberFromIterate++;
                    }
                }

            }
        }
        return Collections.unmodifiableList(newAccounts);
    }
}

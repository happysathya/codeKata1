import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AccountTest {
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowRunTimeException_ifAccountNumberIsNull() {
        Account.createAccount(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowRunTimeException_ifTheLengthOfAccountNumberIsLessThan9() {
        Account.createAccount("");
    }

    @Test
    public void shouldStoreTheStatusAs_ILL_ifAccountNumberContainsNonNumericCharacters() {
        Account account = Account.createAccount("45750800?");
        assertEquals("ILL", account.getAccountStatus());
    }

    @Test
    public void shouldStoreTheStatusAs_VAL_ifAccountNumberChecksumIsNonZero() {
        Account account = Account.createAccount("345882865");
        assertEquals("VAL", account.getAccountStatus());
    }

    @Test
    public void shouldStoreTheStatusAs_ERR_ifAccountNumberChecksumIsNonZero() {
        assertEquals("ERR", Account.createAccount("664371495").getAccountStatus());
    }

    @Test
    public void testDataValidation() {
        assertEquals("VAL", Account.createAccount("457508000").getAccountStatus());
        assertEquals("ERR", Account.createAccount("664371495").getAccountStatus());
        assertEquals("ILL", Account.createAccount("86110??36").getAccountStatus());
        assertEquals("VAL", Account.createAccount("345882865").getAccountStatus());
        assertEquals("ERR", Account.createAccount("490067715").getAccountStatus());
        assertEquals("VAL", Account.createAccount("711111111").getAccountStatus());
        assertEquals("VAL", Account.createAccount("123456789").getAccountStatus());
        assertEquals("VAL", Account.createAccount("490867715").getAccountStatus());
        assertEquals("ILL", Account.createAccount("88888?888").getAccountStatus());
        assertEquals("ERR", Account.createAccount("012345678").getAccountStatus());
        assertEquals("ILL", Account.createAccount("666?66666").getAccountStatus());
        assertEquals("VAL", Account.createAccount("861100336").getAccountStatus());
        assertEquals("VAL", Account.createAccount("457500700").getAccountStatus());
        assertEquals("VAL", Account.createAccount("999004999").getAccountStatus());
    }

    @Test
    public void shouldAccountManipulator_findTheMissingValueAndMakeItCorrectAccountNumber() {
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(Account.createAccount("86110??36"));
        accounts.add(Account.createAccount("45750?000"));
        accounts.add(Account.createAccount("45750??00"));
        accounts.add(Account.createAccount("91??????0"));

        List<Account> manipulatedAccounts = AccountManager.manipulateAccountNumbers(accounts);

        assertEquals(accounts.size(), manipulatedAccounts.size());
        assertEquals("861100336", manipulatedAccounts.get(0).getAccountNumber());
        assertEquals("VAL", manipulatedAccounts.get(0).getAccountStatus());

        assertEquals("457508000", manipulatedAccounts.get(1).getAccountNumber());
        assertEquals("VAL", manipulatedAccounts.get(1).getAccountStatus());

        assertEquals("457500700", manipulatedAccounts.get(2).getAccountNumber());
        assertEquals("VAL", manipulatedAccounts.get(2).getAccountStatus());

        assertEquals("910000050", manipulatedAccounts.get(3).getAccountNumber());
        assertEquals("VAL", manipulatedAccounts.get(3).getAccountStatus());
    }
}

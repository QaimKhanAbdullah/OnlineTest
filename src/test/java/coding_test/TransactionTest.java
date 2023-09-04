package coding_test;

import com.smallworld.data.Transaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {
    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        // Create a new Transaction object before each test
        transaction = new Transaction();
    }

    @Test
    public void testGetSetMtn() {
        Long mtn = 123456L;
        transaction.setMtn(mtn);
        assertEquals(mtn, transaction.getMtn());
    }

    @Test
    public void testGetSetAmount() {
        Double amount = 100.0;
        transaction.setAmount(amount);
        assertEquals(amount, transaction.getAmount());
    }

    @Test
    public void testGetSetSenderFullName() {
        String senderFullName = "John Doe";
        transaction.setSenderFullName(senderFullName);
        assertEquals(senderFullName, transaction.getSenderFullName());
    }

    @Test
    public void testGetSetSenderAge() {
        String senderAge = "30";
        transaction.setSenderAge(senderAge);
        assertEquals(senderAge, transaction.getSenderAge());
    }

    @Test
    public void testGetSetBeneficiaryFullName() {
        String beneficiaryFullName = "Alice Smith";
        transaction.setBeneficiaryFullName(beneficiaryFullName);
        assertEquals(beneficiaryFullName, transaction.getBeneficiaryFullName());
    }

    @Test
    public void testGetSetBeneficiaryAge() {
        Integer beneficiaryAge = 25;
        transaction.setBeneficiaryAge(beneficiaryAge);
        assertEquals(beneficiaryAge, transaction.getBeneficiaryAge());
    }

    @Test
    public void testGetSetIssueId() {
        Long issueId = 789L;
        transaction.setIssueId(issueId);
        assertEquals(issueId, transaction.getIssueId());
    }

    @Test
    public void testGetSetIssueSolved() {
        Boolean issueSolved = true;
        transaction.setIssueSolved(issueSolved);
        assertEquals(issueSolved, transaction.getIssueSolved());
    }

    @Test
    public void testGetSetIssueMessage() {
        String issueMessage = "Issue resolved";
        transaction.setIssueMessage(issueMessage);
        assertEquals(issueMessage, transaction.getIssueMessage());
    }
}


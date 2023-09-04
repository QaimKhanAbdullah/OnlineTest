package coding_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.smallworld.TransactionDataFetcher;
import com.smallworld.data.Transaction;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionDataFetcherTest {

    private TransactionDataFetcher dataFetcher;
    private List<Transaction> transactions;

    @BeforeEach
    void setUp() {
        // Create a list of transactions for testing
        transactions = new ArrayList<>();
        transactions = new ArrayList<>();
        transactions.add(new Transaction(663458L, 430.2, "Tom Shelby",22, "Alfie Solomons",33, 1L, false, "Looks like money laundering"));
        transactions.add(new Transaction(1284564L, 150.2, "Tom Shelby",22, "Arthur Shelby",60, 2L, true, "Never gonna give you up"));
        transactions.add(new Transaction(1284564L, 150.2, "Tom Shelby",22, "Arthur Shelby",60, 3L, false, "Looks like money laundering"));

        // Create the data fetcher with the test transactions
        dataFetcher = new TransactionDataFetcher(transactions);
    }

    @Test
    void testGetTotalTransactionAmount() {
        assertEquals(600.0, dataFetcher.getTotalTransactionAmount());
    }

    @Test
    void testGetTotalTransactionAmountSentBy() {
        assertEquals(350.0, dataFetcher.getTotalTransactionAmountSentBy("Sender1"));
    }

    @Test
    void testGetMaxTransactionAmount() {
        assertEquals(300.0, dataFetcher.getMaxTransactionAmount());
    }

    @Test
    void testCountUniqueClients() {
        assertEquals(6, dataFetcher.countUniqueClients());
    }

    @Test
    void testHasOpenComplianceIssues() {
        assertTrue(dataFetcher.hasOpenComplianceIssues("Sender1"));
        assertFalse(dataFetcher.hasOpenComplianceIssues("Sender3"));
    }

    @Test
    void testGetTransactionsByBeneficiaryName() {
        Map<String, Transaction> transactionsByBeneficiary = dataFetcher.getTransactionsByBeneficiaryName();
        assertEquals(3, transactionsByBeneficiary.size());
        assertTrue(transactionsByBeneficiary.containsKey("Beneficiary1"));
        assertTrue(transactionsByBeneficiary.containsKey("Beneficiary2"));
        assertTrue(transactionsByBeneficiary.containsKey("Beneficiary3"));
    }

    @Test
    void testGetUnsolvedIssueIds() {
        Set<Long> unsolvedIssueIds = dataFetcher.getUnsolvedIssueIds();
        assertEquals(2, unsolvedIssueIds.size());
        assertTrue(unsolvedIssueIds.contains(2L));
        assertTrue(unsolvedIssueIds.contains(4L));
    }

    @Test
    void testGetAllSolvedIssueMessages() {
        List<String> solvedIssueMessages = dataFetcher.getAllSolvedIssueMessages();
        assertEquals(3, solvedIssueMessages.size());
        assertTrue(solvedIssueMessages.containsAll(Arrays.asList("Solved1", "Solved2", "Solved3")));
    }

    @Test
    void testGetTop3TransactionsByAmount() {
        List<Transaction> top3Transactions = dataFetcher.getTop3TransactionsByAmount();
        assertEquals(3, top3Transactions.size());
        assertEquals(300.0, top3Transactions.get(0).getAmount());
        assertEquals(200.0, top3Transactions.get(1).getAmount());
        assertEquals(150.0, top3Transactions.get(2).getAmount());
    }

    @Test
    void testGetTopSender() {
        Optional<String> topSender = dataFetcher.getTopSender();
        assertTrue(topSender.isPresent());
        assertEquals("Sender2", topSender.get());
    }
}


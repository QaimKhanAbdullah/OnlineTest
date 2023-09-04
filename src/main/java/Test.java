import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallworld.TransactionDataFetcher;
import com.smallworld.data.Transaction;
import com.smallworld.data.Transactions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Test {
    public static final ObjectMapper MAPPER = new ObjectMapper();
    public static void main(String[] agrs) throws IOException {
        //File file = new File("transactions.json");
    	InputStream input = new FileInputStream("transactions.json");
    	
    	List<Transaction> transactionList = null;
        ObjectMapper objectMapper = new ObjectMapper();
        transactionList = objectMapper.readValue(input, new TypeReference<>(){});
        
        TransactionDataFetcher myObject = new TransactionDataFetcher(transactionList);
        //myObject.getTop3TransactionsByAmount(); Working
        //myObject.getAllSolvedIssueMessages(); Working
        //System.out.println("TopSender is " + myObject.getTopSender());//working
        //System.out.println("TotalTransactionAmount is " + myObject.getTotalTransactionAmount());
        //System.out.println("getTotalTransactionAmountSentBy is " + myObject.getTotalTransactionAmountSentBy("Tom Shelby")); //NOT WORKING
        //System.out.println("hasOpenComplianceIssues  " + myObject.hasOpenComplianceIssues("Aunt Polly"));
        //System.out.println("getMaxTransactionAmount  " + myObject.getMaxTransactionAmount()); //working
        //System.out.println("Unique Clients are " + myObject.countUniqueClients());// working
        //System.out.println(transaction.toString());
    }
}

package com.smallworld;

import com.smallworld.data.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionDataFetcher {

	
	private List<Transaction> myList; 
	
	public TransactionDataFetcher(List<Transaction> transactionList ){
		myList = transactionList;		
	}
    /**
     * Returns the sum of the amounts of all transactions
     */
    public double getTotalTransactionAmount() {
    	Set<Long> uniqueMTNNumbers = new HashSet<>();
        double totalAmount = 0.0;
        for (Transaction transaction : myList) {
        	Long mtn = transaction.getMtn();
        	// Check if this MTN number is unique
        	if (uniqueMTNNumbers.add(mtn)) {
        		// If it's unique, add the transaction's amount to the total
        		totalAmount += transaction.getAmount();
        	}
        }
        return totalAmount;
    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public double getTotalTransactionAmountSentBy(String senderFullName) {
        double totalAmount = 0;
    	
        for (Transaction trans : myList ) {
        	if(trans.getSenderFullName().equals(senderFullName)) {
        		totalAmount += trans.getAmount();
        	}
        }
        return totalAmount;
  
    }

    /**
     * Returns the highest transaction amount
     */
    public double getMaxTransactionAmount() {
        double maxAmount = 0;
        for (Transaction trans : myList) {
        	//Check if the current transaction amount is greater than the maxAmount
        	if(trans.getAmount() > maxAmount) {
        		maxAmount = trans.getAmount();
            }
        }
        return maxAmount;
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public long countUniqueClients() {
    	Set<String> uniqueCombinations = new HashSet<>();
    	for (Transaction transaction : myList) {
    		String senderFullName = transaction.getSenderFullName();
    		String beneficiaryFullName = transaction.getBeneficiaryFullName();
    		// Create a unique combination key by concatenating sender and beneficiary names
    		String combination = senderFullName + "-" + beneficiaryFullName;
    		// Add the combination to the set, which automatically ensures uniqueness
    		uniqueCombinations.add(combination);
           }
    	// The size of the set represents the count of unique sender-beneficiary combinations
    	return uniqueCombinations.size();
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(String clientFullName) {
        int clientNameCount = 0;
        int mtnDuplicateCount = 0;

        for (Transaction transaction : myList) {
            if (transaction.getSenderFullName().equals(clientFullName) ||
                transaction.getBeneficiaryFullName().equals(clientFullName)) {
                
                // Check if this transaction has the clientFullName
                clientNameCount++;

                // Check if the MTN number is already seen before for this clientFullName
                if (isDuplicateMTN(transaction.getMtn(), clientFullName, myList)) {
                    mtnDuplicateCount++;
                }
            }

            // If both conditions are met, we can return true immediately
            if (clientNameCount >= 2 && mtnDuplicateCount >= 2) {
                return true;
            }
        }

        // If we didn't find two entries with the same MTN number, return false
        return false;
    }
    
    private boolean isDuplicateMTN(Long mtn, String clientFullName, List<Transaction> myList) {
        int count = 0;
        for (Transaction transaction : myList) {
        	
            if ((transaction.getSenderFullName().equals(clientFullName) ||
                transaction.getBeneficiaryFullName().equals(clientFullName)) &&
                transaction.getMtn().equals(mtn)) {
                
                count++;
                if (count >= 2) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public Map<String, Transaction> getTransactionsByBeneficiaryName() {
        Map<String,Transaction> myMap = new HashMap<>();
        for (Transaction trans : myList ) {
        	myMap.put(trans.getBeneficiaryFullName(), trans); 
        }
        return myMap;
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Long> getUnsolvedIssueIds() {
        Set<Long> unsolvedIssueIds = new HashSet<>();
    	for (Transaction trans : myList) {
        	if(!trans.getIssueSolved()) {
        		unsolvedIssueIds.add(trans.getIssueId());
        	}
        }
    	
    	return unsolvedIssueIds;
    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages() {
    	List<String> solvedMessages =  new ArrayList<>();
    	for (Transaction trans : myList ) {
    		if(trans.getIssueSolved() && (trans.getIssueMessage() != null) ) {
    			solvedMessages.add(trans.getIssueMessage()); 
    		}
    	}
    	return solvedMessages;	
    }

    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */
    public List<Transaction> getTop3TransactionsByAmount() {
    	  List<Transaction> top3TransactionsByAmount = myList.stream()
                  .collect(Collectors.groupingBy(Transaction::getMtn, Collectors.toList()))
                  .entrySet()
                  .stream()
                  .filter(entry -> entry.getValue().size() == 1) // Filter unique MTN numbers
                  .flatMap(entry -> entry.getValue().stream())
                  .sorted((t1, t2) -> Double.compare(t2.getAmount(), t1.getAmount())) // Sort by amount in descending order
                  .limit(3) // Limit to the top 3 transactions
                  .collect(Collectors.toList());
    	return top3TransactionsByAmount;
    }

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    public Optional<String> getTopSender() {
    	Map<String, Double> senderAmountMap = myList.stream()
                   .collect(Collectors.groupingBy(Transaction::getSenderFullName,
                           Collectors.summingDouble(Transaction::getAmount)));
    	return senderAmountMap.entrySet()
                   .stream()
                   .max(Map.Entry.comparingByValue())
                   .map(Map.Entry::getKey);
    }

}

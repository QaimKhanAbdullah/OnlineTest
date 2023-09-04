package com.smallworld.data;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {
    // Represent your transaction data here.
    @JsonProperty("mtn")
    private Long mtn;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("senderFullName")
    private String senderFullName;
    @JsonProperty("senderAge")
    private Integer senderAge;
    @JsonProperty("beneficiaryFullName")
    private String beneficiaryFullName;
    @JsonProperty("beneficiaryAge")
    private Integer beneficiaryAge;
    @JsonProperty("issueId")
    private Long issueId;
    @JsonProperty("issueSolved")
    private Boolean issueSolved;
    @JsonProperty("issueMessage")
    private String issueMessage;
    
    public Transaction(Long mtn, Double amount, String senderFullName, Integer senderAge, String benFullName, 
    		           Integer benAge, Long issueId, Boolean issueSolved, String issueMessage  ) {
    	this.mtn = mtn;
    	this.amount = amount;
    	this.senderFullName = senderFullName;
    	this.senderAge = senderAge;
    	this.beneficiaryFullName = benFullName;
    	this.beneficiaryAge = benAge;
    	this.issueId = issueId;
    	this.issueSolved = issueSolved;
    	this.issueMessage = issueMessage;
    }

    public Long getMtn() {
        return mtn;
    }

    public void setMtn(Long mtn) {
        this.mtn = mtn;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSenderFullName() {
        return senderFullName;
    }

    public void setSenderFullName(String senderFullName) {
        this.senderFullName = senderFullName;
    }

    public Integer getSenderAge() {
        return senderAge;
    }

    public void setSenderAge(Integer senderAge) {
        this.senderAge = senderAge;
    }

    public String getBeneficiaryFullName() {
        return beneficiaryFullName;
    }

    public void setBeneficiaryFullName(String beneficiaryFullName) {
        this.beneficiaryFullName = beneficiaryFullName;
    }

    public Integer getBeneficiaryAge() {
        return beneficiaryAge;
    }

    public void setBeneficiaryAge(Integer beneficiaryAge) {
        this.beneficiaryAge = beneficiaryAge;
    }

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public Boolean getIssueSolved() {
        return issueSolved;
    }

    public void setIssueSolved(Boolean issueSolved) {
        this.issueSolved = issueSolved;
    }

    public String getIssueMessage() {
        return issueMessage;
    }

    public void setIssueMessage(String issueMessage) {
        this.issueMessage = issueMessage;
    }
}

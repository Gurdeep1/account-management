package com.test.accountmanagement.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.accountmanagement.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentsCreationReq implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("account")
    private String account;

    @JsonProperty("to_account")
    private String toAccount;

    @JsonProperty("payment_type")
    private PaymentType paymentType;

    @JsonProperty("amount")
    private Double amount;

}

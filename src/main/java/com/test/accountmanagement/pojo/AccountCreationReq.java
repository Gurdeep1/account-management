package com.test.accountmanagement.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountCreationReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("account_id")
    private String accountId;
    @JsonProperty("owner_name")
    private String ownerName;
    @JsonProperty("currency")
    private String currency;
}

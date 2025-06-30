package org.rhydo.superecom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StripePaymentDTO {
    private Long amount;
    private String currency;
}

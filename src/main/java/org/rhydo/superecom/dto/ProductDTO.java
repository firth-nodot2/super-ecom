package org.rhydo.superecom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long productId;
    private String productName;
    private String image;
    private String Description;
    private Integer quantity;
    private double price;
    private double discount;
    private double specialPrice;
}

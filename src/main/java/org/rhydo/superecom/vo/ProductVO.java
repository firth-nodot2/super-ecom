package org.rhydo.superecom.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rhydo.superecom.dto.ProductDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO {
    private List<ProductDTO> content;
}

package org.rhydo.superecom.service;

import org.rhydo.superecom.dto.ProductDTO;

public interface ProductService {
    ProductDTO addProduct(Long categoryId, ProductDTO productDTO);
}

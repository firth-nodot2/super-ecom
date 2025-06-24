package org.rhydo.superecom.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.rhydo.superecom.constant.AppConstants;
import org.rhydo.superecom.dto.ProductDTO;
import org.rhydo.superecom.service.ProductService;
import org.rhydo.superecom.vo.ProductVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO, @PathVariable Long categoryId) {
        ProductDTO savedProductDTO = productService.addProduct(categoryId, productDTO);
        return new ResponseEntity<>(savedProductDTO, HttpStatus.CREATED);
    }

    @GetMapping("public/products")
    public ResponseEntity<ProductVO> getAllProducts(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR) String sortOrder) {
        ProductVO productVO = productService.getAllProducts(pageNumber, pageSize, sortBy, sortOrder, keyword, category);
        return new ResponseEntity<>(productVO, HttpStatus.OK);
    }

    @GetMapping("public/categories/{categoryId}/products")
    public ResponseEntity<ProductVO> getProductsByCategory(@PathVariable Long categoryId,
                                                           @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
                                                           @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
                                                           @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY) String sortBy,
                                                           @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR) String sortOrder) {
        ProductVO productVO = productService.searchByCategory(categoryId, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(productVO, HttpStatus.OK);
    }

    @GetMapping("public/products/keyword/{keyword}")
    public ResponseEntity<ProductVO> getProductByKeyword(@PathVariable String keyword,
                                                         @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
                                                         @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
                                                         @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY) String sortBy,
                                                         @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR) String sortOrder) {
        ProductVO productVO = productService.searchByKeyword(keyword, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(productVO, HttpStatus.OK);
    }

    @PutMapping("admin/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO, @PathVariable Long productId) {
        ProductDTO updateProductDTO = productService.updateProduct(productId, productDTO);
        return new ResponseEntity<>(updateProductDTO, HttpStatus.OK);
    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long productId) {
        ProductDTO deleteProductDTO = productService.deleteProduct(productId);
        return new ResponseEntity<>(deleteProductDTO, HttpStatus.OK);
    }

    @PutMapping("/products/{productId}/image")
    public ResponseEntity<ProductDTO> updateProductImage(@PathVariable Long productId, @RequestParam("image") MultipartFile image) throws IOException {
        ProductDTO updatedProduct = productService.updateProductImage(productId, image);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
}

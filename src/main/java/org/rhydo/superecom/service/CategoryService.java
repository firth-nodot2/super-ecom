package org.rhydo.superecom.service;

import org.rhydo.superecom.dto.CategoryDTO;
import org.rhydo.superecom.vo.CategoryVO;


public interface CategoryService {
    CategoryVO getAllCategories(Integer pageNumber, Integer pageSize);

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}

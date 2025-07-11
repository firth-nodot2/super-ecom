package org.rhydo.superecom.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.rhydo.superecom.dto.CategoryDTO;
import org.rhydo.superecom.exception.APIException;
import org.rhydo.superecom.exception.ResourceNotFoundException;
import org.rhydo.superecom.model.Category;
import org.rhydo.superecom.vo.CategoryVO;
import org.rhydo.superecom.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public CategoryVO getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);

        List<Category> categories = categoryPage.getContent();
        if (categories.isEmpty()) {
            throw new APIException("No category created till now.");
        }

        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();

        CategoryVO categoryVO = new CategoryVO();
        categoryVO.setContent(categoryDTOS);
        categoryVO.setPageNumber(categoryPage.getNumber());
        categoryVO.setPageSize(categoryPage.getSize());
        categoryVO.setTotalElements(categoryPage.getTotalElements());
        categoryVO.setTotalPages(categoryPage.getTotalPages());
        categoryVO.setLastPage(categoryPage.isLast());

        return categoryVO;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);

        Category ExistedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if (ExistedCategory != null) {
            throw new APIException("Category with the name " + category.getCategoryName() + " already exists !!!");
        }
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
        Category existedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));

        categoryRepository.delete(existedCategory);
        return modelMapper.map(existedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
        Category existedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));

        Category category = modelMapper.map(categoryDTO, Category.class);
        existedCategory.setCategoryName(category.getCategoryName());

        Category updatedCategory = categoryRepository.save(existedCategory);
        return modelMapper.map(updatedCategory, CategoryDTO.class);
    }
}

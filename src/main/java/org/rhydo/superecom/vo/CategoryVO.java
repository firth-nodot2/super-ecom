package org.rhydo.superecom.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rhydo.superecom.dto.CategoryDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVO {
    private List<CategoryDTO> content;

    private Integer pageNumber;
    private Integer pageSize;
    private Long TotalElements;
    private Integer totalPages;
    private boolean lastPage;
}

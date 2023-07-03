package org.example.repository;
import org.example.dto.CategoryDto;
import org.springframework.stereotype.Repository;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CategoryRepository {
    private List<CategoryDto> categoryDtoList= new LinkedList<>();
    public CategoryDto getCategoryByName(String categoryName) {
       return categoryDtoList.stream().filter(s->s.getCategoryName().equals(categoryName)).findAny().orElse(null);
    }

    public List<CategoryDto> getAllCategory() {
        return categoryDtoList;
    }

    public void addCategory(CategoryDto category) {
       categoryDtoList.add(category);
    }
    public boolean removeCategory(String id) {
       return categoryDtoList.removeIf(s->s.getId().equals(id));
    }
}

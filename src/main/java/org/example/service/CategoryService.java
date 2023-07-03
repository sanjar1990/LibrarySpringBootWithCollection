package org.example.service;
import org.example.dto.CategoryDto;
import org.example.exception.IsAlreadyExistsException;
import org.example.exception.ItemNotFoundException;
import org.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public CategoryDto getCategoryByName(String categoryName) {
        return  categoryRepository.getCategoryByName(categoryName);
    }

    public List<CategoryDto> getAllCategory() {
        List<CategoryDto> list=categoryRepository.getAllCategory();
        if(list.isEmpty()){
           throw new ItemNotFoundException("There is no category was found!");
        }else {
            return list;
        }
    }

    public CategoryDto addCategory(CategoryDto categoryDto) {
        CategoryDto isExists=categoryRepository.getCategoryByName(categoryDto.getCategoryName());
        if(isExists !=null && isExists.isVisible()){
           throw new IsAlreadyExistsException("This type of category is exists!");
        }
        categoryDto.setVisible(true);
        categoryDto.setId(UUID.randomUUID().toString());
        categoryDto.setCreatedDate(LocalDateTime.now());
        categoryRepository.addCategory(categoryDto);
      return categoryDto;
    }

    public String removeCategory(String id) {
        boolean result=categoryRepository.removeCategory(id);
        if(result){
           return "Category removed successfully!";
        }else {
           throw new ItemNotFoundException("Category not found!");
        }
    }
}

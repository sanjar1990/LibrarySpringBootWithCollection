package org.example.service;

import org.example.dto.BookDto;
import org.example.dto.CategoryDto;
import org.example.exception.ItemNotFoundException;
import org.example.exception.NothingFoundException;
import org.example.repository.BookRepository;
import org.example.util.CheckValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CheckValidationUtil checkValidationUtil;
    @Autowired
    private CategoryService categoryService;
    public List<BookDto> getAllBook() {
        List<BookDto> bookDtoList=bookRepository.getAllBook();
        if (bookDtoList.isEmpty()){
            throw new NothingFoundException("Library is empty");
        }else {
          return bookDtoList;
        }
    }

    public List<BookDto> searchByData(String data) {
        Integer id=checkValidationUtil.isId(data);
        List<BookDto> list=bookRepository.searchByData(data,id);
        if (list.isEmpty()){
           throw new NothingFoundException("Library is empty");
        }else {
           return list;
        }
    }

    public List<BookDto> searchBookByCategory(String category) {
        List<BookDto> list=bookRepository.searchByCategory(category);
        if (list.isEmpty()){
            throw new NothingFoundException("No book was found by this category!");
        }else {
           return list;
        }
    }


    public BookDto addBook(BookDto bookDto) {
        CategoryDto category=categoryService.getCategoryByName(bookDto.getCategoryName());
        if(category==null){
            throw new ItemNotFoundException("Category is not found");
        }
        bookDto.setId(UUID.randomUUID().toString());
        bookDto.setCreatedDate(LocalDateTime.now());
        bookRepository.addBook(bookDto);
       return bookDto;

    }

    public String removeBook(String id) {
       boolean response= bookRepository.removeBook(id);
        if(response){
          return "Book was removed successfully!";
        }else {
           throw new ItemNotFoundException("Book not found!");
        }
    }
}

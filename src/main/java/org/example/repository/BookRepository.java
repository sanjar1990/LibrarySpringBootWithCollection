package org.example.repository;
import org.example.dto.BookDto;
import org.springframework.stereotype.Repository;
import java.util.LinkedList;
import java.util.List;

@Repository
public class BookRepository {
  private List<BookDto> bookDtoList= new LinkedList<>();
    public List<BookDto> getAllBook(){
        return bookDtoList;
    }

    public List<BookDto> searchByData(String data, Integer id) {
        List<BookDto> list=new LinkedList<>();
       for (BookDto b: bookDtoList){
           if(b.getAuthor().equals(data)||b.getTitle().equals(data)||b.getCategoryName().equals(data) || b.getId().equals(id)){
               list.add(b);
           }
       }
       return list;
            }
    public List<BookDto> searchByCategory(String category) {
        List<BookDto> list=new LinkedList<>();
        for (BookDto b: bookDtoList){
            if(b.getCategoryName().equals(category)){
                list.add(b);
            }
        }
        return list;
    }

    public BookDto getBookById(String bookId) {
      return bookDtoList.stream().filter(s->s.getId().equals(bookId)).findAny().orElse(null);
    }


    public void addBook(BookDto bookDto) {
        bookDtoList.add(bookDto);
    }

    public boolean removeBook(String id) {
       return bookDtoList.removeIf(s->s.getId().equals(id));
    }
}

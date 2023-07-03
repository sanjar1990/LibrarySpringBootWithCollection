package org.example.repository;
import org.example.dto.TakenBookDto;
import org.example.enums.TakenBookStatus;
import org.springframework.stereotype.Repository;
import java.util.LinkedList;
import java.util.List;

@Repository
public class TakenBookRepository {
   private List<TakenBookDto> takenBookDtoList= new LinkedList<>();

    public TakenBookDto getBookInfo(String bookId) {
        for (TakenBookDto t: takenBookDtoList){
            if(t.getBookId().equals(bookId)){
                return t;
            }
        }
        return null;
    }
    public void addTakenBookInfo(TakenBookDto takenBookDto) {
      takenBookDtoList.add(takenBookDto);
    }

    public void returnBook(String takenBookId, TakenBookStatus takenBookStatus) {
    takenBookDtoList.forEach(s->{
        if(s.getId().equals(takenBookId)){
            s.setStatus(takenBookStatus);
        }
    });
    }

    public List<TakenBookDto> getAllBooksOnHandById(String id) {
        List<TakenBookDto> list=new LinkedList<>();
        takenBookDtoList.forEach(s-> {
            if(s.getStudentId().equals(id) && s.getStatus().equals(TakenBookStatus.TOOK)){
                list.add(s);
            }
        });
        return list;

    }

    public List<TakenBookDto> getAllTakenBookList(String id) {
     List<TakenBookDto>list=new LinkedList<>();
     takenBookDtoList.forEach(s->{
         if(s.getStudentId().equals(id)){
             list.add(s);
         }
     });
     return list;
    }

    public List<TakenBookDto> getAllBooksOnHand() {
       return takenBookDtoList;
    }

    public List<TakenBookDto> getBookHistoryById(String id) {
        List<TakenBookDto> list=new LinkedList<>();
        takenBookDtoList.forEach(s->{
            if(s.getBookId().equals(id)){
                list.add(s);
            }
        });

        return list;
    }

    public List<TakenBookDto> getStudentBookInfo(String id) {
        List<TakenBookDto> list= new LinkedList<>();
        for (TakenBookDto t: takenBookDtoList){
            if(t.getStudentId().equals(id)){
                list.add(t);
            }
        }
        return list;
    }
}

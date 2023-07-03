package org.example.service;

import org.example.container.ComponentContainer;
import org.example.dto.BookDto;
import org.example.dto.TakenBookDto;
import org.example.enums.TakenBookStatus;
import org.example.exception.BookIsNotExistsException;
import org.example.exception.ItemNotFoundException;
import org.example.exception.NothingFoundException;
import org.example.mapper.*;
import org.example.repository.BookRepository;
import org.example.repository.TakenBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class TakeAcceptBookService {
    @Autowired
    private TakenBookRepository takenBookRepository;
    @Autowired
    private BookRepository bookRepository;
    public TakenBookDto takeBook(String bookId) {
        //check
        BookDto bookDto=bookRepository.getBookById(bookId);
        if(bookDto==null){
            throw new BookIsNotExistsException("Book is not find");
        }
        TakenBookDto takenBook=takenBookRepository.getBookInfo(bookId);
        if(takenBook!=null && takenBook.getStatus().equals(TakenBookStatus.TOOK)){
            throw new BookIsNotExistsException("This book is already taken!");
        }
            TakenBookDto takenBookDto= new TakenBookDto();
            takenBookDto.setBookId(UUID.randomUUID().toString());
            takenBookDto.setStudentId(ComponentContainer.profileDto.getId());
            takenBookDto.setStatus(TakenBookStatus.TOOK);
            takenBookDto.setCreatedDate(LocalDateTime.now());
            LocalDate localDate=LocalDate.now().plusDays(bookDto.getAvailableDay());
            takenBookDto.setNote("No info");
            takenBookDto.setReturnDate(localDate);
            takenBookRepository.addTakenBookInfo(takenBookDto);
           return takenBookDto;
    }

    public String returnBook(String bookId) {
        BookDto bookDto=bookRepository.getBookById(bookId);
        TakenBookDto takenBookDto= takenBookRepository.getBookInfo(bookId);
        if(bookDto==null){
            throw new NothingFoundException("Book is not found");
        }
        if(takenBookDto==null ||
                !takenBookDto.getStudentId().equals(ComponentContainer.profileDto.getId())){
            throw new ItemNotFoundException("You did not take this book !");
        }else if(takenBookDto.getStatus().equals(TakenBookStatus.RETURNED)){
           throw new ItemNotFoundException("You have already returned this book! ");
        }

        takenBookRepository.returnBook(takenBookDto.getId(),TakenBookStatus.RETURNED);
        return "You have successfully returned book !";
    }
    public List<TakenBookDto> getAllTakenBookById(String id) {
        List<TakenBookDto> takenBookDtoList=takenBookRepository.getAllBooksOnHandById(id);
        if (takenBookDtoList.isEmpty()){
            throw new ItemNotFoundException("You don't have any books on your hand!");
        }else {
           return takenBookDtoList;
        }

    }

    public List<BooksOnHand> getTodayReturnBookList(Integer id) {
        List<BooksOnHand> booksOnHandList=takenBookRepository.getAllBooksOnHandById(id);
        List<BooksOnHand> deadlineEnteredBookList=new LinkedList<>();

        booksOnHandList.forEach(s->{
            if(s.getReturnDate().equals(LocalDate.now())){
                System.out.println("You should return this book today: "+ s);
                deadlineEnteredBookList.add(s);
            }
        });
        if(deadlineEnteredBookList.isEmpty()){
            throw new NothingFoundException("You don't have any book to return today!");
        }
        return deadlineEnteredBookList;
    }

    public List<TakenBookDto> getAllTakenBookHistory(String id) {
        List<TakenBookDto> list=takenBookRepository.getAllTakenBookList(id);
        if(list==null){
           throw new ItemNotFoundException("You did not take any book from library!");
        }else {
            return list;
        }
    }
    public List<TakenBookDto> getAllTakenBook() {
        List<TakenBookDto> list=takenBookRepository.getAllBooksOnHand();
        if(list.isEmpty()){
            throw new ItemNotFoundException("There are no book on hand!");
        }else {
            return list;
        }
    }
    public List<TakenBookDto> getAllBookHistoryByBookId(String  id) {
       BookDto bookDto=bookRepository.getBookById(id);
        if(bookDto==null){
            throw new ItemNotFoundException("Book is not found");
        }
        List<TakenBookDto> list=takenBookRepository.getBookHistoryById(id);
        if(list.isEmpty()){
            throw new ItemNotFoundException("Book history is not found!");
        }
        return  list;
    }
}

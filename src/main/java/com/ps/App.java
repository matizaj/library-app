package com.ps;

import com.ps.model.Book;
import com.ps.repository.BookRepository;
import com.ps.repository.Repository;

import java.util.ArrayList;
import java.util.Optional;

public class App
{
    public static void main( String[] args ){
        System.out.println( "Hello Library App!" );

        Repository<Book> repo = new BookRepository();
        var books = repo.findAll();

        for(var book : books){
            System.out.printf("Book: %s\n", book.getTitle());
        }


        Optional<Book> optBook = repo.findById(1);
        if(optBook.isPresent()){
            System.out.println(optBook.get().getTitle());
            optBook.get().setTitle("Effective Java: Second Edition");

            repo.update(optBook.get());
        }

        for(var book : books){
            System.out.printf("Book: %s\n", book.getTitle());
        }

//        books = repo.findAll();
//
//        var updatedEntries = books.stream().peek(x -> x.setRating(5)).toList();
//
//        var result = repo.update(updatedEntries);

        var removedBook = repo.findById(3);
        repo.delete(removedBook.get());
//        var newBook  = new Book();
//        newBook.setTitle("Java Cookbook");
//        newBook = repo.save(newBook);
//        System.out.println(newBook.getId());
//        System.out.println(newBook.getTitle());
    }
}

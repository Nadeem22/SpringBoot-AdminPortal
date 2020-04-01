package com.nadeem.bookstore.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nadeem.bookstore.domain.Book;



public interface BookService {
List <Book>  findAll();
Book findOne(Long id);
Book save(Book book);
List<Book> blurrySearch(String title);
void removeOne(Long id);
void store(MultipartFile file, long id);

}

package com.nadeem.bookstore.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.nadeem.bookstore.constants.Constants;
import com.nadeem.bookstore.domain.Book;
import com.nadeem.bookstore.repository.BookRepository;
import com.nadeem.bookstore.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepository;

	private Path uploadLocation;

	@PostConstruct
	public void init() {
		this.uploadLocation = Paths.get(Constants.UPLOAD_LOCATION);
		try {
			Files.createDirectories(uploadLocation);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage", e);
		}
	}

	@Override
	public List<Book> findAll() {
		List<Book> books = bookRepository.findAll();
		List<Book> activeBookList = new ArrayList<Book>();
		for (Book book : books) {
			if (book.isActive()) {
				activeBookList.add(book);
			}
		}
		return activeBookList;
	}

	@Override
	public Book findOne(Long id) {
		Optional<Book> bookResponse = bookRepository.findById(id);
		Book book = bookResponse.get();
		return book;
	}

	@Override
	public Book save(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public List<Book> blurrySearch(String keywString) {
		List<Book> books = bookRepository.findByTitle(keywString);
		List<Book> activeBookList = new ArrayList<Book>();
		for (Book book : books) {
			if (book.isActive()) {
				activeBookList.add(book);
			}
		}
		return activeBookList;
	}

	@Override
	public void removeOne(Long id) {
		bookRepository.deleteById(id);
		String modifiedFilename = id + ".png";
		Path pathOfFile = Paths.get("D:\\Angular-Spring-E-Commerce\\\\fileUploade\\"+modifiedFilename);
		 try { 
	            boolean result 
	                = Files.deleteIfExists(pathOfFile); 
	  
	            if (result) 
	                System.out.println("File is deleted"); 
	            else
	                System.out.println("File does not exists"); 
	        } 
	        catch (IOException e) { 
	  
	            e.printStackTrace(); 
	        } 
	}

	@Override
	public void store(MultipartFile file, long id) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		String modifiedFilename = id + ".png";


		try {
			if (file.isEmpty()) {
				throw new RuntimeException("Failed to store empty file " + filename);
			}

			if (filename.contains("..")) {
				throw new RuntimeException(
						"Cannot store file with relative path outside current directory " + filename);
			}

			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, this.uploadLocation.resolve(modifiedFilename),
						StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			throw new RuntimeException("Failed to store file " + filename, e);
		}

	}

}

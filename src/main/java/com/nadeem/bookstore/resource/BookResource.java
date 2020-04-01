package com.nadeem.bookstore.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nadeem.bookstore.domain.Book;
import com.nadeem.bookstore.service.BookService;

@RestController
@RequestMapping("/book")
public class BookResource {
	@Autowired
	private BookService bookService;

	@PostMapping("/add")
	public Book addBookPost(@RequestBody Book book) {
		System.out.println("---------Save Book----------");
		return bookService.save(book);

	}

	@PostMapping("/add/image/{id}")
	public ResponseEntity<Void> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable long id) {
		bookService.store(file, id);
		try {

			ResponseEntity.ok().build();
		} catch (Exception e) {

			ResponseEntity.badRequest().build();
		}
		return null;
	}

	@GetMapping("/bookList")
	public List<Book> getBookList() {
		return bookService.findAll();
	}

	@GetMapping("/{id}")
	public Book getBook(@PathVariable("id") Long id) {
		System.out.println("========in Get Book==============");
		Book book = bookService.findOne(id);
		return book;
	}

	@PostMapping("/update")
	public Book updateBook(@RequestBody Book book) {
		System.out.println("In Update Book ");
		return bookService.save(book);
	}

	@PostMapping
	public ResponseEntity<Void> modifyFile(@RequestParam("file") MultipartFile file, @PathVariable long id) {
		bookService.store(file, id);
		try {

			ResponseEntity.ok().build();
		} catch (Exception e) {

			ResponseEntity.badRequest().build();
		}
		return null;
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Long> remove(@PathVariable Long id) {
		System.out.println("In Delete Mapping");
		bookService.removeOne(id);
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}

}

package com.example.hzm.book.controller;

import com.example.hzm.book.entity.Book;
import com.example.hzm.book.properties.AmazonProperties;
import com.example.hzm.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class BookListController {

    private BookRepository bookRepository;
    private AmazonProperties amazonProperties;

    @Autowired
    public BookListController(BookRepository bookRepository,AmazonProperties amazonProperties) {
        this.bookRepository = bookRepository;
        this.amazonProperties = amazonProperties;
    }

    @RequestMapping(value="/{reader}", method= RequestMethod.GET)
    public String readersBooks(@PathVariable("reader") String reader, Model model) {
        List<Book> bookList = bookRepository.findByReader(reader);
        if (bookList != null) {
            model.addAttribute("books", bookList);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonID", amazonProperties.getAssociateId());
        }
        return "bookList";
    }

    @RequestMapping(value="/{reader}", method=RequestMethod.POST)
    public String addToBookList(@PathVariable("reader") String reader, Book book) {
        book.setReader(reader);
        bookRepository.save(book);
        return "redirect:/{reader}";
    }
}

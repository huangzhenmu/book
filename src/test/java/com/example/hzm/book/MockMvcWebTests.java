package com.example.hzm.book;

import com.example.hzm.book.entity.Book;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BookApplication.class)
@WebAppConfiguration//表明测试上下文是web上下文
public class MockMvcWebTests {

    @Autowired
    public WebApplicationContext webApplicationContext;

    public MockMvc mockMvc;

    @Before//表明在test之前
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void homePage() throws Exception {
        mockMvc.perform(get("/bookList"))
                .andExpect(status().isOk())
                .andExpect(view().name("bookList"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", is(empty())));
    }

    @Test
    public void postBook() throws Exception {
        //测试添加一本书的需求
        mockMvc.perform(post("/bookList")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "TITLE")
                .param("author", "THOR")
                .param("isbn", "7890")
                .param("description", "TION"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/bookList"));

        /*//创建一本书
        Book expectedBook = new Book();
        expectedBook.setId(2L);
        expectedBook.setReader("craig");
        expectedBook.setTitle("BOOK TITLE");
        expectedBook.setAuthor("BOOK AUTHOR");
        expectedBook.setIsbn("1234567890");
        expectedBook.setDescription("DESCRIPTION");

        //将创建的书存入model中，然后查询书列表
        mockMvc.perform(get("/bookList"))
                .andExpect(status().isOk())
                .andExpect(view().name("bookList"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", hasSize(1)))
                .andExpect(model().attribute("books", contains(samePropertyValuesAs(expectedBook))));*/
    }
}

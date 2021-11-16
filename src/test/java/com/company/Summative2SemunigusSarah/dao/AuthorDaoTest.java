package com.company.Summative2SemunigusSarah.dao;

import com.company.Summative2SemunigusSarah.model.Author;
import com.company.Summative2SemunigusSarah.model.Book;
import com.company.Summative2SemunigusSarah.model.Publisher;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import static org.junit.Assert.*;
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthorDaoTest {
    @Autowired
    AuthorDao authorDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    PublisherDao publisherDao;

    @BeforeEach
    public void setUp() throws Exception {
        // Clean up the test db
        List<Author> tList = authorDao.getAllAuthor();
        for (Author t : tList) {
            authorDao.deleteAuthor(t.getId());
        }

        List<Book> aList = bookDao.getAllBook();

        for (Book a : aList) {
            bookDao.deleteBook(a.getId());
        }

        List<Publisher> pList = publisherDao.getAllPublisher();

        for (Publisher p : pList) {
            publisherDao.deletePublisher(p.getId());
        }

    }
    @Test
    public void addGetDeleteAuthor() {

        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Brown");
        author.setStreet("sdsf");
        author.setCity("DC");
        author.setState("VA");
        author.setPostalCode("33392");
        author.setPhone("800-393-3030");
        author.setEmail("jbrown@gmail.com");
        author = authorDao.addAuthor(author);

        Author author1 = authorDao.getAuthor(author.getId());
        assertEquals(author1, author);
        authorDao.deleteAuthor(author.getId());

        author1 = authorDao.getAuthor(author.getId());

        assertNull(author1);

    }

    @Test
    public void getAllAuthor() {

        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Brown");
        author.setStreet("sdsf");
        author.setCity("DC");
        author.setState("VA");
        author.setPostalCode("33392");
        author.setPhone("800-393-3030");
        author.setEmail("jbrown@gmail.com");
        author = authorDao.addAuthor(author);

        Author author1 = new Author();
        author1.setFirstName("Max");
        author1.setLastName("Johnson ");
        author1.setStreet("sdsf");
        author1.setCity("Seattle");
        author1.setState("VA");
        author1.setPostalCode("32344");
        author1.setPhone("800-993-3333");
        author1.setEmail("mjohn@gmail.com");
        authorDao.addAuthor(author1);

        List<Author> tList = authorDao.getAllAuthor();

        assertEquals(tList.size(), 2);

    }


    @Test
    public void updateAuthor() {

        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Brown");
        author.setStreet("sdsf");
        author.setCity("Alexandria");
        author.setState("VA");
        author.setPostalCode("33392");
        author.setPhone("800-393-3030");
        author.setEmail("jbrown@gmail.com");
        authorDao.addAuthor(author);

        Author author1 = authorDao.getAuthor(author.getId());
        author1.setFirstName("New Name");
        author1.setLastName("New LName");
        author1.setStreet("kljl");
        author1.setCity("New City");
        author1.setState("MD");
        author1.setPostalCode("New Code");
        author1.setPhone("New Number");
        author1.setEmail("New Email");

        authorDao.updateAuthor(author1);



        Assertions.assertEquals(author1,authorDao.getAuthor(author1.getId()));
    }

}
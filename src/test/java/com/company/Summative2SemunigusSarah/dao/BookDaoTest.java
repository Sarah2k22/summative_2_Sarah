package com.company.Summative2SemunigusSarah.dao;

import com.company.Summative2SemunigusSarah.model.Author;
import com.company.Summative2SemunigusSarah.model.Book;
import com.company.Summative2SemunigusSarah.model.Publisher;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
public class BookDaoTest {
    @Autowired
    AuthorDao authorDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    PublisherDao publisherDao;

    @Before
    public void setUp() throws Exception {
        // Clean up the test db
        List<Book> aList = bookDao.getAllBook();

        for (Book a : aList) {
            bookDao.deleteBook(a.getId());
        }

        List<Author> tList = authorDao.getAllAuthor();
        for (Author t : tList) {
            authorDao.deleteAuthor(t.getId());
        }



        List<Publisher> pList = publisherDao.getAllPublisher();

        for (Publisher p : pList) {
            publisherDao.deletePublisher(p.getId());
        }


    }
    @Test
    public void addGetDeleteBook() {
        //need to create publisher and author

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

        Publisher publisher = new Publisher();
        publisher.setName("Alpha Book Publisher");
        publisher.setStreet("1101 Finley Ln, Alexandria, VA 22304");
        publisher.setCity("Alexandria");
        publisher.setState("VS");
        publisher.setPostal_code("22304");
        publisher.setPhone("323-323-3233");
        publisher.setEmail("abp@gmail.com");
        publisher = publisherDao.addPublisher(publisher);


        Book book = new Book();
        book.setIsbn("20934");
        book.setPublishDate(LocalDate.of(2010, 1, 5));
        book.setAuthorId(author.getId());
        book.setTitle("Over the mountains");
        book.setPublisherId(publisher.getId());
        book.setPrice(new BigDecimal("21.95"));
        bookDao.addBook(book);


        Book book1 = bookDao.getBook(book.getId());
        book.setId(book1.getId());
        assertEquals(book1, book);

        bookDao.deleteBook(book.getId());

        book1 = bookDao.getBook(book.getId());

        assertNull(book1);
    }

    @Test
    public void updateBook() {

        // Need to create a Author and publisher first
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

        Publisher publisher = new Publisher();
        publisher.setName("Alpha Book Publisher");
        publisher.setStreet("1101 Finley Ln, Alexandria, VA 22304");
        publisher.setCity("Alexandria");
        publisher.setState("VS");
        publisher.setPostal_code("22304");
        publisher.setPhone("323-323-3233");
        publisher.setEmail("abp@gmail.com");
        publisher = publisherDao.addPublisher(publisher);


        Book book = new Book();
        book.setIsbn("20934");
        book.setPublishDate(LocalDate.of(2010, 1, 5));
        book.setAuthorId(author.getId());
        book.setTitle("Over the mountains");
        book.setPublisherId(publisher.getId());
        book.setPrice(new BigDecimal("21.95"));
        book = bookDao.addBook(book);

        book.setIsbn("393948");
        book.setPublishDate(LocalDate.of(2020, 3, 5));
        book.setTitle("The war");
        book.setPrice(new BigDecimal("40.99"));

        bookDao.updateBook(book);

        Book book1 = bookDao.getBook(book.getId());

        assertEquals(book1, book);

    }

    @Test
    public void getAllBook() {

        Publisher publisher = new Publisher();
        publisher.setName("Alpha Book Publisher");
        publisher.setStreet("1101 Finley Ln, Alexandria, VA 22304");
        publisher.setCity("Alexandria");
        publisher.setState("VS");
        publisher.setPostal_code("22304");
        publisher.setPhone("323-323-3233");
        publisher.setEmail("abp@gmail.com");
        publisher = publisherDao.addPublisher(publisher);

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
        author1.setFirstName("New Name");
        author1.setLastName("New LName");
        author1.setStreet("kljl");
        author1.setCity("New City");
        author1.setState("MD");
        author1.setPostalCode("New Code");
        author1.setPhone("New Number");
        author1.setEmail("New Email");
        author1 = authorDao.addAuthor(author1);


        Book book = new Book();
        book.setIsbn("20934");
        book.setPublishDate(LocalDate.of(2010, 1, 5));
        book.setAuthorId(author.getId());
        book.setTitle("Over the mountains");
        book.setPublisherId(publisher.getId());
        book.setPrice(new BigDecimal("21.95"));
        book = bookDao.addBook(book);

        Book book1= new Book();
        book1.setIsbn("393948");
        book1.setPublishDate(LocalDate.of(2020, 3, 5));
        book1.setAuthorId(author1.getId());
        book1.setTitle("The war");
        book1.setPublisherId(publisher.getId());
        book1.setPrice(new BigDecimal("40.99"));
        book1 = bookDao.addBook(book1);

    }
    @Test
    public void getBookByAuthor(){

        Publisher publisher = new Publisher();
        publisher.setName("Alpha Book Publisher");
        publisher.setStreet("1101 Finley Ln, Alexandria, VA 22304");
        publisher.setCity("Alexandria");
        publisher.setState("VS");
        publisher.setPostal_code("22304");
        publisher.setPhone("323-323-3233");
        publisher.setEmail("abp@gmail.com");
        publisher = publisherDao.addPublisher(publisher);

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
        author1.setFirstName("New Name");
        author1.setLastName("New LName");
        author1.setStreet("kljl");
        author1.setCity("New City");
        author1.setState("MD");
        author1.setPostalCode("New Code");
        author1.setPhone("New Number");
        author1.setEmail("New Email");
        author1 = authorDao.addAuthor(author1);

        Book book = new Book();

    }

}
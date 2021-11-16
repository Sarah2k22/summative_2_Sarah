package com.company.Summative2SemunigusSarah.dao;

import com.company.Summative2SemunigusSarah.model.Author;
import com.company.Summative2SemunigusSarah.model.Book;
import com.company.Summative2SemunigusSarah.model.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PublisherDaoTest {
    @Autowired
    AuthorDao authorDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    PublisherDao publisherDao;


    @BeforeEach
    public void setUp() throws Exception {
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
    public void addGetDeletePublisher(){

        Publisher publisher = new Publisher();
        publisher.setName("Alpha Book Publisher");
        publisher.setStreet("1101 Finley Ln, Alexandria, VA 22304");
        publisher.setCity("Alexandria");
        publisher.setState("VS");
        publisher.setPostal_code("22304");
        publisher.setPhone("292-212-2323");
        publisher.setEmail("abp@gmail.com");
        publisher = publisherDao.addPublisher(publisher);

        Publisher publisher1= publisherDao.getPublisher(publisher.getId());
        assertEquals(publisher1,publisher);
        publisherDao.deletePublisher(publisher.getId());
        publisher1 = publisherDao.getPublisher(publisher.getId());
        assertNull(publisher1);

    }
    @Test
    public void getAllPublisher(){
        Publisher publisher= new Publisher();
        publisher.setName("Alpha Book Publisher");
        publisher.setStreet("1101 Finley Ln, Alexandria, VA 22304");
        publisher.setCity("Alexandria");
        publisher.setState("VA");
        publisher.setPostal_code("22304");
        publisher.setPhone("292-212-2323");
        publisher.setEmail("abp@gmail.com");
        publisher= publisherDao.addPublisher(publisher);

        publisher = new Publisher();
        publisher.setName("New Publisher");
        publisher.setStreet("1478 Angel Wing Ct");
        publisher.setCity("Arlington");
        publisher.setState("VA");
        publisher.setPostal_code("20146");
        publisher.setPhone("703-154-4546");
        publisher.setEmail("newpublisher@gmail.com");
        publisherDao.addPublisher(publisher);

        List<Publisher> pList = publisherDao.getAllPublisher();

        assertEquals(pList.size(), 2);

//        Publisher publisher1= new Publisher();
//        publisher1.setName("New Name");
//        publisher1.setStreet("New address");
//        publisher1.setCity("New city");
//        publisher1.setState("MS");
//        publisher1.setPostal_code("New Code");
//        publisher1.setPhone("987-976-9877");
//        publisher1.setEmail("New email");
//
//        Publisher publisher1 = publisherDao.getPublisher(publisher.getId());
//        publisherDao.addPublisher(publisher1);
//
//        List<Publisher> pList = publisherDao.getAllPublisher();
//
//        assertEquals(pList.size(), 2);


    }

    @Test
    public void updatePublisher(){

        Publisher publisher= new Publisher();
        publisher.setName("Alpha Book Publisher");
        publisher.setStreet("1101 Finley Ln, Alexandria, VA 22304");
        publisher.setCity("Alexandria");
        publisher.setState("VA");
        publisher.setPostal_code("22304");
        publisher.setPhone("292-212-2323");
        publisher.setEmail("abp@gmail.com");
        publisherDao.addPublisher(publisher);

        Publisher publisher1 = publisherDao.getPublisher(publisher.getId());
        publisher1.setName("New Name");
        publisher1.setStreet("New address");
        publisher1.setCity("New city");
        publisher1.setState("MS");
        publisher1.setPostal_code("New Code");
        publisher1.setPhone("987-976-9877");
        publisher1.setEmail("New email");

        publisherDao.updatePublisher(publisher1);

        Assertions.assertEquals(publisher1,publisherDao.getPublisher(publisher1.getId()));



    }

}
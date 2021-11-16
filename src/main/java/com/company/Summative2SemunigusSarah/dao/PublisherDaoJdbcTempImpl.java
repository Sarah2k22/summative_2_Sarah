package com.company.Summative2SemunigusSarah.dao;

import com.company.Summative2SemunigusSarah.model.Publisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
@Repository
public class PublisherDaoJdbcTempImpl implements PublisherDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_PUBLISHER_SQL =
            "insert into publisher ( name, street, city, state, postal_code,phone, email) values ( ?, ?, ?, ?,? ,?,?)";

    private static final String SELECT_PUBLISHER_SQL =
            "select * from publisher where publisher_id = ?";

    private static final String SELECT_ALL_PUBLISHER_SQL =
            "select * from publisher";

    private static final String UPDATE_PUBLISHER_SQL =
            "update publisher set name = ?, street = ?, city = ?, state = ?, postal_code = ?, phone =?,email = ? where publisher_id = ?";

    private static final String DELETE_PUBLISHER =
            "delete from publisher where publisher_id = ?";

    public PublisherDaoJdbcTempImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Publisher addPublisher(Publisher publisher) {

        jdbcTemplate.update(
                INSERT_PUBLISHER_SQL,
                publisher.getName(),
                publisher.getStreet(),
                publisher.getCity(),
                publisher.getState(),
                publisher.getPostal_code(),
                publisher.getPhone(),
                publisher.getEmail());


        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        publisher.setId(id);
        return publisher;
    }

    @Override
    public Publisher getPublisher(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_PUBLISHER_SQL, this::mapRowToPublisher, id);
        } catch (EmptyResultDataAccessException e) {

            return null;
        }
    }

    @Override

    public List<Publisher> getAllPublisher() {
        List<Publisher> publishers =  jdbcTemplate.query(SELECT_ALL_PUBLISHER_SQL, this::mapRowToPublisher);
        if (publishers == null){
            return Collections.EMPTY_LIST;
        }
        return publishers;
    }
//    public List<Publisher> getAllPublisher() {
//        return jdbcTemplate.query(SELECT_ALL_PUBLISHER_SQL, this::mapRowToPublisher);
//    }


    @Override
    public void updatePublisher(Publisher publisher) {
        jdbcTemplate.update(
                UPDATE_PUBLISHER_SQL,
                publisher.getName(),
                publisher.getStreet(),
                publisher.getCity(),
                publisher.getState(),
                publisher.getPostal_code(),
                publisher.getPhone(),
                publisher.getEmail(),
                publisher.getId());

    }

    @Override
    public void deletePublisher(int id) {
        jdbcTemplate.update(DELETE_PUBLISHER, id);

    }
    private Publisher mapRowToPublisher(ResultSet rs, int rowNum) throws SQLException {

        Publisher publisher= new Publisher();
        publisher.setId(rs.getInt("publisher_id"));
        publisher.setName(rs.getString("name"));
        publisher.setStreet(rs.getString("street"));
        publisher.setCity(rs.getString("city"));
        publisher.setState(rs.getString("state"));
        publisher.setPostal_code(rs.getString("postal_code"));
        publisher.setPhone(rs.getString("phone"));
        publisher.setEmail(rs.getString("email"));
        return publisher;
    }


}

package com.bob.springbootsecurity.dao.implement;

import com.bob.springbootsecurity.dao.NewBookDao;
import com.bob.springbootsecurity.model.NewBook;
import com.bob.springbootsecurity.rm.NewBookRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NewBookDaoImpl implements NewBookDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<NewBook> findBooks(){
        String sql="SELECT * FROM newbook WHERE 1=1";

        Map<String,Object> map=new HashMap<>();

        List<NewBook> booksList=namedParameterJdbcTemplate.query(sql,map,new NewBookRowMapper());
        return booksList;
    }

    @Override
    public void saveBook(NewBook entity){
        String sql="INSERT INTO newbook(title, author, call_number, " +
                "barcode, exh_fr, exh_end, act_yn) " +
                "VALUES ( :title, :author, :callNumber, " +
                ":barcode, :exhFr, :exhEnd, :actYn)";

        Map<String,Object> map=new HashMap<>();
        map.put("title",entity.getTitle());
        map.put("author",entity.getAuthor());
        map.put("callNumber",entity.getCallNumber());
        map.put("barcode",entity.getBarcode());
        map.put("exhFr",entity.getExhFr());
        map.put("exhEnd",entity.getExhEnd());
        map.put("actYn",0);


        KeyHolder keyHolder=new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
    }

    @Override
    public void deleteBookById(Integer bookId){
        String sql="DELETE FROM newbook WHERE book_id= :bookId";

        Map<String,Object> map=new HashMap<>();
        map.put("bookId",bookId);

        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public NewBook findBookById(Integer bookId) {
        String sql="SELECT * FROM newbook WHERE book_id= :bookId";

        Map<String,Object> map=new HashMap<>();
        map.put("bookId",bookId);

        List<NewBook> booksList=namedParameterJdbcTemplate.query(sql,map,new NewBookRowMapper());
        NewBook book=new NewBook();
        book.setBookId(booksList.get(0).getBookId());
        book.setTitle(booksList.get(0).getTitle());
        book.setAuthor(booksList.get(0).getAuthor());
        book.setCallNumber(booksList.get(0).getCallNumber());
        book.setBarcode(booksList.get(0).getBarcode());
        book.setExhEnd(booksList.get(0).getExhFr());
        book.setExhFr(booksList.get(0).getExhEnd());
        book.setReviseDate(booksList.get(0).getReviseDate());
        book.setActYn(booksList.get(0).getActYn());

        return book;
    }

    @Override
    public void updateBook(NewBook entity) {
        String sql="UPDATE newbook " +
                "SET title= :title, author= :author, call_number= :callNumber, barcode= :barcode, " +
                "exh_fr= :exhFr, exh_end= :exhEnd, act_yn= :actYn " +
                "WHERE book_id= :bookId";

        Map<String, Object> map=new HashMap<>();
        map.put("bookId",entity.getBookId());
        map.put("title",entity.getTitle());
        map.put("author",entity.getAuthor());
        map.put("callNumber",entity.getCallNumber());
        map.put("barcode",entity.getBarcode());
        map.put("exhFr",entity.getExhFr());
        map.put("exhEnd",entity.getExhEnd());
        map.put("actYn",entity.getActYn());

        namedParameterJdbcTemplate.update(sql,map);
    }
}

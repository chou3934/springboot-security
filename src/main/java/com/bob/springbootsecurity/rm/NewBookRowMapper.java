package com.bob.springbootsecurity.rm;

import com.bob.springbootsecurity.model.NewBook;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewBookRowMapper implements RowMapper<NewBook> {
    @Override
    public NewBook mapRow(ResultSet rs, int rowNum) throws SQLException {
        NewBook newBook=new NewBook();
        newBook.setBookId(rs.getLong("book_id"));
        newBook.setTitle(rs.getString("title"));
        newBook.setAuthor(rs.getString("author"));
        newBook.setCallNumber(rs.getString("call_number"));
        newBook.setBarcode(rs.getString("barcode"));
        newBook.setExhFr(rs.getDate("exh_fr"));
        newBook.setExhEnd(rs.getDate("exh_end"));
        newBook.setReviseDate(rs.getDate("revise_date"));
        newBook.setActYn(rs.getShort("act_yn"));

        return newBook;
    }
}

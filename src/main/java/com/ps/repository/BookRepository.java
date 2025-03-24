package com.ps.repository;

import com.ps.model.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BookRepository extends BaseRepository implements Repository<Book> {
    @Override
    public Optional<Book> findById(int id) {
        Optional<Book> book = Optional.empty();
        String sql = "SELECT * FROM book WHERE id = ?";

        try(var con = getConnection();
            var prepStmt = con.prepareStatement(sql);
        ){
            prepStmt.setInt(1, id);

            try(ResultSet rs = prepStmt.executeQuery()) {
               if(rs.next()) {
                   Book resBook = new Book();
                   resBook.setId(rs.getInt("id"));
                   resBook.setTitle(rs.getString("title"));
                   book = Optional.of(resBook);
               }
            }

        }catch (SQLException e) {e.printStackTrace();}

        return book;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = Collections.emptyList();
        String sql = "select * from book";
        try(
                var conn = getConnection();
                var stmt = conn.createStatement();
                var rs = stmt.executeQuery(sql);
                ) {
            books = new ArrayList<>();
            while(rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                books.add(book);
            }


        } catch (SQLException sqe){sqe.printStackTrace();}
        return books;
    }

    @Override
    public Book save(Book book) {
        String sql = "INSERT INTO book (title) VALUES (?)";

        try (var con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if(generatedKeys.next()) {
                    book.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {e.printStackTrace();}
        return book;
    }

    @Override
    public void delete(Book book) {
        String sql = "delete from book where id = ?";
        try (var con = getConnection();
            PreparedStatement prepStmt  = con.prepareStatement(sql);
        ) {
            prepStmt.setInt(1, book.getId());
            prepStmt.execute();
        } catch (SQLException e){e.printStackTrace();}
    }

    @Override
    public void update(Book book) {
        String sql = "UPDATE book SET title = ? WHERE id = ?";
        try ( var con = getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {e.printStackTrace();}

    }

    @Override
    public int[] update(List<Book> books) {
        int[] records = {};
        String sql = "UPDATE book SET title = ?, rating = ? WHERE id = ?";
        try (var con = getConnection();
        PreparedStatement prepStmt = con.prepareStatement(sql)
        ) {
            for (var book: books) {
                prepStmt.setString(1, book.getTitle());
                prepStmt.setInt(2, book.getRating());
                prepStmt.setInt(3, book.getId());

                prepStmt.addBatch();
            }

            records = prepStmt.executeBatch();

        } catch (SQLException e ) { e.printStackTrace();}
        return records;
    }

    @Override
    public int[] delete(List<Book> books) {
        int[] records = {};

        String sql = " delete from book where id = ?";

        try (var con = getConnection();
            PreparedStatement prepStmt = con.prepareStatement(sql);
        ){
            for(var book : books) {
                prepStmt.setInt(1, book.getId());
                prepStmt.addBatch();
            }
            records = prepStmt.executeBatch();
        }catch (SQLException e ) {e.printStackTrace();}
        return records;
    }
}

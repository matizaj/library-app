package com.ps.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class JdbcQueryTemplate<T> extends BaseRepository {
    public JdbcQueryTemplate() {

    }

    public List<T> queryForList(String sql) {
        List<T> items = new ArrayList<>();

        try (var con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
        ) {
            while(rs.next()) {
                items.add(mapItem(rs));
            }
        } catch (SQLException e){e.printStackTrace();}

        return items;
    }

    public  abstract T mapItem(ResultSet rs) throws SQLException ;
}

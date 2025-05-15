package dao;

import java.sql.SQLException;
import java.util.List;

public interface BaseDAO<T> {

    void save(T t) throws SQLException;

    T findById(long id);

    List<T> findAll();

    void update(T t);

    void delete(long id);

}

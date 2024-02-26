package x.nutri.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Iservice<T> {

        void add(T t);
        ArrayList<T> getAll();
        void Update(T t) throws SQLException;
        boolean delete(T t);
    }



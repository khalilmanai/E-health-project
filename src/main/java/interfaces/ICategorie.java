package interfaces;

import java.util.ArrayList;

public interface ICategorie<T,P> {
    void addCat (T t);
    ArrayList<T> getAllCat();
    void updateCat(T t);
    boolean deleteCat(T t);
}

package pe.sanpedro.systemcv.dao;


import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Mysk
 * @param <T>
 */
public interface GenericDao<T> {
    
    default Boolean insert(T t){
        throw new UnsupportedOperationException("No implementado");
    }
    default Boolean insert2(List<T> l, boolean b){
        throw new UnsupportedOperationException("No implementado");
    }
    default void update(T t) {
        throw new UnsupportedOperationException("No implementado");
    }
    default void delete(int id) {
        throw new UnsupportedOperationException("No implementado");
    }
    default void deleteall(int id) {
        throw new UnsupportedOperationException("No implementado");
    }
    default List<T> sel() {
        throw new UnsupportedOperationException("No implementado");
    }
    default T searchById(int id) {
        throw new UnsupportedOperationException("No implementado");
    }
    default List<T> searchById2(int id) {
        throw new UnsupportedOperationException("No implementado");
    }
    default List<T> searchById3(int id, int id2) {
        throw new UnsupportedOperationException("No implementado");
    }
    default Integer  searchById4(int id, int id2) {
        throw new UnsupportedOperationException("No implementado");
    }
    default List<T> searchByQuery(String query) {
        throw new UnsupportedOperationException("No implementado");
    }
    default T searchByQuery2(String query) {
        throw new UnsupportedOperationException("No implementado");
    }
    default T login(String u, String p){
        throw new UnsupportedOperationException("No implementado");
    }
    
    default List<T> searchBetween(LocalDate d1, LocalDate d2){
        throw new UnsupportedOperationException("No implementado");
    }
    default List<T> searchByDate(LocalDate d){
        throw new UnsupportedOperationException("No implementado");
    }
    default String getMessage(){
        throw new UnsupportedOperationException("No implementado");
    }
}

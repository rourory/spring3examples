package app.database.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;
public interface CrudRepository<K, E> {

    Optional<E> findById(K id);

    boolean delete (K id);

}

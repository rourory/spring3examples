package app.database.repository;

import app.database.entity.Role;
import app.database.entity.User;
import app.dto.PersonalInfoInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends
        JpaRepository<User, Long>,
        FilterUserRepository,
        RevisionRepository<User, Long, Integer>,
        QuerydslPredicateExecutor<User> {

    Optional<User> findById(Long id);


    @Query("select u from User u where u.firstName like %:firstname% and u.lastName like %:lastname%")
    List<User> findAllBy(String firstname, String lastname);

    @Modifying
    @Query("update User u set u.role = :role where u.id in (:ids)")
    int updateRole(Role role, Long... ids);


    @Query("select u from User u where u.id in (:ids)")
    List<User> findAllByIds(Long... ids);

    //    @EntityGraph(value = "User.company")
    Page<User> findAllBy(Pageable pageable);

    //    <T> List<T> findAllByCompanyId(Integer companyId, Class<T> projectionClazz);

    @Query(nativeQuery = true, value = "SELECT firstname, lastname, birth_date as birthDate FROM users WHERE company_id = :companyId")
    List<PersonalInfoInterface> findAllByCompanyId(Integer companyId);

    Optional<User> findByUsername(String username);
}

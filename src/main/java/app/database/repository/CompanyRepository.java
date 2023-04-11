package app.database.repository;

import app.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface CompanyRepository extends JpaRepository<Company, Integer> {

//    @Query(name = "Company.findByName")
    @Query("select c from Company c join fetch c.locales cl where c.name = :companyName")
    Optional<Company> findByName(@Param("companyName") String name);

    List<Company> findAllByNameContainingIgnoreCase(String fragment);

}

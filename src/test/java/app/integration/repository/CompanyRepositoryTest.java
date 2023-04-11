package app.integration.repository;

import app.database.entity.Company;
import app.database.repository.CompanyRepository;
import app.integration.IntegrationTestBase;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class CompanyRepositoryTest extends IntegrationTestBase {

    private final EntityManager entityManager;
    private final CompanyRepository companyRepository;

    @Test
    void findById() {
        Company company = entityManager.find(Company.class, 1);
        Assertions.assertThat(company).isNotNull();
        Assertions.assertThat(company.getLocales()).hasSize(2);
    }

    @Test
    void delete() {
        Company company = Company.builder()
                .name("Apple")
                .locales(Map.of(
                        "ru", "Apple описание",
                        "en", "Apple description"
                ))
                .build();
        entityManager.persist(company);
        entityManager.flush();

        var foundCompany = companyRepository.findById(company.getId());
        Assertions.assertThat(foundCompany.isPresent()).isTrue();

        foundCompany.ifPresent(c -> companyRepository.deleteById(c.getId()));

        entityManager.flush();

        Assertions.assertThat(companyRepository.findById(company.getId()).isPresent()).isFalse();

    }


    @Test
    void checkFindByQueries (){
        Optional<Company> google = companyRepository.findByName("Google");
        google.ifPresent(company -> {
            Assertions.assertThat(company).isNotNull();
            Assertions.assertThat(company.getName()).isEqualTo("Google");
        });


    }
}

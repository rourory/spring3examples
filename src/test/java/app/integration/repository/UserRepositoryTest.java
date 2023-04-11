package app.integration.repository;

import app.database.entity.Role;
import app.database.entity.User;
import app.database.repository.UserRepository;
import app.dto.PersonalInfoInterface;
import app.dto.UserFilter;
import app.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class UserRepositoryTest extends IntegrationTestBase {

    private final UserRepository userRepository;

    @Test
    void checkSort() {
        var sortBy = Sort.sort(User.class);
        Sort sort = sortBy.by(User::getFirstName).and(sortBy.by(User::getLastName));
    }

    @Test
    void checkPageable() {
        PageRequest request = PageRequest.of(1, 2, Sort.by("id").descending());
        Page<User> users = userRepository.findAllBy(request);
        System.out.println(users.get().findFirst().get().getCompany().getName());
        Assertions.assertThat(users).hasSize(2);
    }

    @Test
    void checkUpdate() {
        int i = userRepository.updateRole(Role.USER, 1L, 5L);
        List<User> allByIds = userRepository.findAllByIds(1L, 5L);

        Assertions.assertThat(i).isEqualTo(2);

        allByIds.forEach(user -> org.junit.jupiter.api.Assertions.assertAll(() -> {
            Assertions.assertThat(user.getRole()).isEqualTo(Role.USER);
            Assertions.assertThat(user.getId()).isIn(1L, 5L);
        }));


    }

    @Test
    void checkQueries() {
        List<User> users = userRepository.findAllBy("a", "ov");
        System.out.println(users);
    }

    @Test
    void checkProjections() {
//        List<PersonalInfo> users = userRepository.findAllByCompanyId(1, PersonalInfo.class);
        List<PersonalInfoInterface> users = userRepository.findAllByCompanyId(1);
        Assertions.assertThat(users).hasSize(2);
//        System.out.println(users);
    }

    @Test
    void checkCustomImplementation() {
        var filter = new UserFilter("a", "ov", LocalDate.now());
        List<User> allByFilter = userRepository.findAllByFilter(filter);
        System.out.println(allByFilter);
    }

    @Test
    void checkAuditing(){
        User user = userRepository.findById(1L).get();
        user.setBirthDate(user.getBirthDate().plusYears(1L));
        userRepository.flush();
        System.out.println();
    }

    @Test
    void checkBatch(){
        List<User> all = userRepository.findAll();
        System.out.println("/////////////////");
        System.out.println(all);
        System.out.println("/////////////////");
        userRepository.updateCompanyAndRole(all);
    }
}

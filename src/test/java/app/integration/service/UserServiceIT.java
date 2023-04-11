package app.integration.service;

import app.database.entity.Role;
import app.dto.UserCreateEditDto;
import app.integration.IntegrationTestBase;
import app.integration.annotation.IT;
import app.config.DatabaseProperties;
import app.database.ConnectionPool;
import app.dto.UserReadDto;
import app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {

    private final UserService userService;
    private final Long IVAN_ID = 1L;
    private final Integer COMPANY_ID = 1;

    @Test
    void findAll() {
        List<UserReadDto> result = userService.findAll();
        Assertions.assertThat(result).hasSize(5);
    }

    @Test
    void findById() {
        UserReadDto maybeIvan = Optional.of(userService.findById(IVAN_ID))
                .get().orElse(null);
        org.junit.jupiter.api.Assertions.assertAll(() -> {
            Assertions.assertThat(maybeIvan).isNotNull();
            Assertions.assertThat(maybeIvan.getFirstName()).isEqualTo("Ivan");
        });
    }


    @Test
    void create() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "testUsername",
                LocalDate.of(1999, 12, 12),
                "testFirstname",
                "testLastname",
                Role.ADMIN,
                COMPANY_ID
        );

        UserReadDto actualResult = userService.create(userDto);

        System.out.println(actualResult);

        org.junit.jupiter.api.Assertions.assertAll(() -> {
            Assertions.assertThat(actualResult).isNotNull();
            Assertions.assertThat(actualResult.getUsername()).isEqualTo(userDto.getUsername());
            Assertions.assertThat(actualResult.getRole().name()).isEqualTo(userDto.getRole().name());
        });
    }

    @Test
    void update() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "testUsername",
                LocalDate.of(1999, 12, 12),
                "testFirstname",
                "testLastname",
                Role.ADMIN,
                COMPANY_ID
        );

        UserReadDto actualResult = Optional.of(userService.update(IVAN_ID, userDto))
                .get().orElse(null);

        org.junit.jupiter.api.Assertions.assertAll(() -> {
            Assertions.assertThat(actualResult).isNotNull();
            Assertions.assertThat(actualResult.getUsername()).isEqualTo(userDto.getUsername());
            Assertions.assertThat(actualResult.getCompany().id()).isEqualTo(COMPANY_ID);
            Assertions.assertThat(actualResult.getRole().name()).isEqualTo(userDto.getRole().name());
        });
    }

    @Test
    void delete() {
        assertTrue(userService.delete(IVAN_ID));
        assertFalse(userService.delete(-666L));
    }
}

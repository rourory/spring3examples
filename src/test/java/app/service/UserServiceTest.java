package app.service;

import app.database.entity.Role;
import app.database.entity.User;
import app.database.repository.CrudRepository;
import app.dto.UserReadDto;
import app.listener.entity.EntityEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({
        MockitoExtension.class,
})
class UserServiceTest {

    private static final Long USER_ID = 1L;

    @Mock
    private CrudRepository<Long, User> userRepository;
    @Mock
    private ApplicationEventPublisher eventPublisher;
    @InjectMocks
    private UserService userService;


    @Test
    void findById() {
//        Mockito.doReturn(Optional.of(new User(USER_ID, "username", LocalDate.of(1997, 3, 21), "firstName", "lastName", Role.ADMIN, null, null)))
//                .when(userRepository).findById(USER_ID);
//
//        Optional<UserReadDto> actualResult = userService.findById(USER_ID);
//
//        assertTrue(actualResult.isPresent());
//
//        var expectedResult = new UserReadDto(USER_ID);
//
//        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
//
//        Mockito.verify(eventPublisher).publishEvent(Mockito.any(EntityEvent.class));
//        Mockito.verifyNoMoreInteractions(eventPublisher);
    }
}
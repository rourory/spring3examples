package app.dto;

import app.database.entity.Role;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UserReadDto {
    Long id;
    String username;
    LocalDate birthDate;
    String firstName;
    String lastName;
    Role role;
    CompanyReadDto company;
}

package app.mapper;

import app.database.entity.Company;
import app.database.entity.User;
import app.dto.CompanyReadDto;
import app.dto.UserReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {

    private final CompanyReadMapper companyReadMapper;

    @Override
    public UserReadDto map(User object) {
        CompanyReadDto company = Optional.ofNullable(object.getCompany())
                .map(companyReadMapper::map)
                .orElse(null);
        return new UserReadDto(object.getId(),
                object.getUsername(),
                object.getBirthDate(),
                object.getFirstName(),
                object.getLastName(),
                object.getImage(),
                object.getRole(),
                company);
    }
}

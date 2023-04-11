package app.mapper;

import app.database.entity.Company;
import app.database.entity.User;
import app.database.repository.CompanyRepository;
import app.dto.UserCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    private final CompanyRepository companyRepository;

    @Override
    public User map(UserCreateEditDto object) {
        User user = new User();
        copy(object, user);
        return user;
    }


    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }


    public Company getCompany(Integer id) {
        return Optional.ofNullable(id)
                .flatMap(companyRepository::findById)
                .orElse(null);
    }

    private void copy(UserCreateEditDto object, User user) {
        user.setUsername(object.getUsername());
        user.setFirstName(object.getFirstName());
        user.setLastName(object.getLastName());
        user.setBirthDate(object.getBirthDate());
        user.setRole(object.getRole());
        user.setCompany(getCompany(object.getCompanyId()));
    }

}

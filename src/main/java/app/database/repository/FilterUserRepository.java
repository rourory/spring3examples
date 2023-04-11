package app.database.repository;

import app.database.entity.Role;
import app.database.entity.User;
import app.dto.PersonalInfo;
import app.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {
    List<User> findAllByFilter(UserFilter filter);

    List<PersonalInfo> findAllByCompanyAndRole(Integer companyId, Role role);

    void updateCompanyAndRole(List<User> users);

    void updateCompanyAndRoleNamed(List<User> users);
}

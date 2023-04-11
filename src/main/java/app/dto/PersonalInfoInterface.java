package app.dto;

import org.springframework.beans.factory.annotation.Value;

public interface PersonalInfoInterface {
    String getFirstName();

    String getLastName();

    String getBirthDate();

    @Value("#{target.lastName + ' ' + target.firstName}")
    String getFullName();
}

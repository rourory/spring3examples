package app.dto;

import app.database.entity.Role;
import app.validation.UserInfo;
import app.validation.groups.CreateAction;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Value
@FieldNameConstants
@UserInfo(groups = CreateAction.class)
public class UserCreateEditDto {

    @Email
    String username;

    @NotBlank(groups = CreateAction.class)
    String rawPassword;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;

    String firstName;

    String lastName;

    Role role;

    Integer companyId;

    MultipartFile image;
}

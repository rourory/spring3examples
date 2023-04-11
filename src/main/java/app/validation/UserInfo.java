package app.validation;

import app.validation.Impl.UserInfoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserInfoValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface UserInfo {

    String message() default "Firstname of Lastname should be filled in";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

package app.mapper;

import app.database.entity.Company;
import app.dto.CompanyReadDto;
import org.springframework.stereotype.Component;

@Component
public class CompanyReadMapper implements Mapper<Company, CompanyReadDto> {
    @Override
    public CompanyReadDto map(Company object) {
        return new CompanyReadDto(object.getId(),object.getName());
    }
}

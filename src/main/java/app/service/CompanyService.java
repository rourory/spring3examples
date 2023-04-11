package app.service;

import app.database.repository.CompanyRepository;
import app.dto.CompanyReadDto;
import app.listener.entity.AccessType;
import app.listener.entity.EntityEvent;
import app.mapper.CompanyReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CompanyService {

    private final CompanyRepository companyRepository;

    private final ApplicationEventPublisher eventPublisher;
    private final CompanyReadMapper companyReadMapper;

    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                    return companyReadMapper.map(entity);
                });
    }

    public boolean delete(Long id) {
        System.out.println("Delete company with id " + id);
        return true;
    }

    public List<CompanyReadDto> findAll() {
        return companyRepository.findAll()
                .stream().map(companyReadMapper::map).toList();
    }
}

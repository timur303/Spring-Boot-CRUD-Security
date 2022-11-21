package kg.kadyrbekov.service.impl;

import kg.kadyrbekov.entity.Company;
import kg.kadyrbekov.entity.Student;
import kg.kadyrbekov.exception.NotFoundException;
import kg.kadyrbekov.repository.CompanyRepository;
import kg.kadyrbekov.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company updateCompany(Long id, Company newCompany) {
        Company company = findById(id).get();
        company.setCompanyName(newCompany.getCompanyName());
        company.setLocatedCountry(newCompany.getLocatedCountry());
        return companyRepository.save(company);
    }



    @Override
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }
}

package kg.kadyrbekov.controller;

import kg.kadyrbekov.entity.Company;
import kg.kadyrbekov.service.CompanyService;
import kg.kadyrbekov.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;
    private final StudentService studentService;

    @GetMapping
    public List<Company> findAllCompany() {
        return companyService.findAllCompany();
    }

    @GetMapping("/{id}")
    public Optional<Company> findById(@PathVariable("id") Long id) {
        return companyService.findById(id);
    }
    //localhost:9090/api/company/count канча студенти бар
    @GetMapping("/count")
    public Long countById(Long id){
        return studentService.countById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public Company saveCompany(@RequestBody Company company) {
        return companyService.saveCompany(company);
    }

    @PatchMapping("update/{id}")
    public Company updateCompany(@PathVariable("id") Long id, @RequestBody Company company) {
        return companyService.updateCompany(id, company);
    }

    @DeleteMapping("delete/{id}")
    public void deleteCompanyById(@PathVariable("id") Long id) {
        companyService.deleteById(id);
    }
}


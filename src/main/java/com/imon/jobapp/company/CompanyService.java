package com.imon.jobapp.company;

import java.util.List;

public interface CompanyService {
    List<Company> getCompanies();

    void createCompany(Company company);

    Company getCompanyById(Long id);

    boolean deleteJobById(Long id);

    boolean updateCompanyById(Long id, Company updatedCompany);
}

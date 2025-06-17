package com.example.universityadmissionscommittee.service;

import com.example.universityadmissionscommittee.data.Benefit;
import com.example.universityadmissionscommittee.repository.BenefitRepository;
import org.springframework.stereotype.Service;

@Service
public class BenefitService extends AbstractCrudService<Benefit, Long, BenefitRepository> {
    protected BenefitService(BenefitRepository repository) {
        super(repository);
    }
}

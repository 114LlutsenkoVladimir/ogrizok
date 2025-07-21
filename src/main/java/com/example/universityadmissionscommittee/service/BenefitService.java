package com.example.universityadmissionscommittee.service;

import com.example.universityadmissionscommittee.data.Benefit;
import com.example.universityadmissionscommittee.dto.BenefitIdAndName;
import com.example.universityadmissionscommittee.repository.BenefitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BenefitService extends AbstractCrudService<Benefit, Long, BenefitRepository> {
    protected BenefitService(BenefitRepository repository) {
        super(repository);
    }

    public List<BenefitIdAndName> toIdAndNameDto(List<Benefit> benefits) {
        return benefits.stream()
                .map(b -> new BenefitIdAndName(b.getName(), b.getId())).toList();
    }

    public List<BenefitIdAndName> allIdAndName() {
        return toIdAndNameDto(findAll());
    }
}

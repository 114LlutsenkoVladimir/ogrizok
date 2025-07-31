package com.example.universityadmissionscommittee.data;

import com.example.universityadmissionscommittee.data.enums.BenefitType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "benefit")
public class Benefit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "additional_points")
    private Integer additionalPoints;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private BenefitType benefitType;

    public Benefit(BenefitType benefitType) {
        this.name = benefitType.toString();
        this.additionalPoints = benefitType.getAdditionalPoints();
        this.benefitType = benefitType;
    }

    protected Benefit() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAdditionalPoints() {
        return additionalPoints;
    }


    public BenefitType getBenefitType() {
        return benefitType;
    }

}

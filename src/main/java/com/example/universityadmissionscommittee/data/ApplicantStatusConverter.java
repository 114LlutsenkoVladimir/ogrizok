package com.example.universityadmissionscommittee.data;

import com.example.universityadmissionscommittee.data.enums.ApplicantStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter(autoApply = true)
public class ApplicantStatusConverter implements AttributeConverter<ApplicantStatus, String> {

    @Override
    public String convertToDatabaseColumn(ApplicantStatus status) {
        return status == null ? null : status.name();
    }

    @Override
    public ApplicantStatus convertToEntityAttribute(String dbValue) {
        if (dbValue == null) return null;
        try {
             return ApplicantStatus.valueOf(dbValue);
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Unknown value for ApplicantStatus: " + dbValue, ex);
        }
    }
}

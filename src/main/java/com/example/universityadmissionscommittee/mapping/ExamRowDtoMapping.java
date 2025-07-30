package com.example.universityadmissionscommittee.mapping;
import com.example.universityadmissionscommittee.dto.ExamRowDto;
import jakarta.persistence.*;

@Entity
@SqlResultSetMapping(
        name = "ExamRowDtoMapping",
        classes = @ConstructorResult(
                targetClass = ExamRowDto.class,
                columns = {
                        @ColumnResult(name = "applicant_id", type = Long.class),
                        @ColumnResult(name = "first_name", type = String.class),
                        @ColumnResult(name = "last_name", type = String.class),
                        @ColumnResult(name = "phone_number", type = String.class),
                        @ColumnResult(name = "email", type = String.class),
                        @ColumnResult(name = "specialty_id", type = Long.class),
                        @ColumnResult(name = "specialty_name", type = String.class),
                        @ColumnResult(name = "subject_id", type = Long.class),
                        @ColumnResult(name = "subject_name", type = String.class),
                        @ColumnResult(name = "result", type = Integer.class),
                        @ColumnResult(name = "applicant_status", type = String.class),
                        @ColumnResult(name = "priority", type = Integer.class)
                }
        )
)
public class ExamRowDtoMapping {

    @Id
    private Long id;

    public ExamRowDtoMapping() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

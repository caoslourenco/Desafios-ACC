package acc.br.student_registration.dto;

import java.time.LocalDate;
import lombok.Data;

/**
 * DTO (Data Transfer Object) para representar os dados de registro de um estudante.
 */
@Data
public class StudentRegistrationDTO {

    private String name;
    private LocalDate dateOfBirth;
    private String cep;
    private String course;
}

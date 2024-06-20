import com.ukma.entity.Student;
import com.ukma.validator.CustomValidator;
import com.ukma.validator.StudentCustomValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentValidationTest {

    CustomValidator studentValidator = new StudentCustomValidator();

    @Test
    public void testThatCorrectStudentDoesNotCauseValidationMessages() {
        Student student = new Student(
                "Volodymyr",
                "+380636363636",
                "vova.havryliuk@mail.com",
                "FI"
        );

        List<String> validationViolations = studentValidator.validate(student);

        assertThat(validationViolations).isNotNull();
        assertThat(validationViolations).isEmpty();
    }

    @Test
    public void testThatStudentWithAllIncorrectFieldsCausesViolationMessages() {
        Student student = new Student(
                "",
                "+38063242342342346",
                "vova@",
                "fi"
        );

        List<String> validationViolations = studentValidator.validate(student);

        assertThat(validationViolations).isNotNull();
        assertThat(validationViolations).hasSize(4); // because all fields are not valid
    }

    @Test
    public void testThatNameLengthConstraintExist() {
        Student student = new Student(
                "Volodymyr Havryliuk",
                "+380636363636",
                "vova.havryliuk@mail.com",
                "FI"
        );

        List<String> validationViolations = studentValidator.validate(student);

        assertThat(validationViolations).isNotNull();
        assertThat(validationViolations).hasSize(1);
        assertThat(validationViolations.get(0)).startsWith("name length is bigger than max value");
    }

}

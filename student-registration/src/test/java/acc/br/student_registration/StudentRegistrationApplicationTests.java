package acc.br.student_registration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import acc.br.student_registration.service.StudentService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StudentRegistrationApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldContainStudentServiceBean() {
        assertThat(applicationContext.getBean(StudentService.class)).isNotNull();
    }

    @Test
    void verifyStudentServiceIsLoaded() {
        StudentService studentService = applicationContext.getBean(StudentService.class);
        assertThat(studentService).isNotNull();
    }

    @Test
    void checkStudentServiceFunctionality() {
        StudentService studentService = applicationContext.getBean(StudentService.class);
        
        int initialCount = studentService.getStudentCount();
        studentService.registerStudent("John Doe");
        int newCount = studentService.getStudentCount();

        assertThat(newCount).isEqualTo(initialCount + 1);
    }
}

package acc.br.student_registration.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import acc.br.student_registration.dto.AddressDTO;
import acc.br.student_registration.dto.StudentRegistrationDTO;
import acc.br.student_registration.entity.Student;
import acc.br.student_registration.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final AddressService addressService;

    public StudentService(StudentRepository studentRepository, AddressService addressService) {
        this.studentRepository = studentRepository;
        this.addressService = addressService;
    }

    @Transactional
    public Student createStudent(StudentRegistrationDTO dto) {
        AddressDTO addressDTO = addressService.getAddress(dto.getCep());

        Student student = new Student();
        student.setName(dto.getName());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setCourse(dto.getCourse());
        student.setCep(addressDTO.getCep());
        student.setStreet(addressDTO.getStreet());
        student.setNeighborhood(addressDTO.getNeighborhood());
        student.setCity(addressDTO.getCity());
        student.setState(addressDTO.getState());

        return studentRepository.save(student);
    }
}

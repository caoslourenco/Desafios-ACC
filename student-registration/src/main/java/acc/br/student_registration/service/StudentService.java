package acc.br.student_registration.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import acc.br.student_registration.entity.Student;
import acc.br.student_registration.repository.StudentRepository;
import acc.br.student_registration.dto.StudentRegistrationDTO;
import acc.br.student_registration.dto.AddressDTO;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final AddressService addressService;

    public StudentService(StudentRepository studentRepository, AddressService addressService) {
        this.studentRepository = studentRepository;
        this.addressService = addressService;
    }

    /**
     * Cria um novo estudante a partir dos dados fornecidos no DTO de registro.
     *
     * @param dto DTO contendo os dados do estudante
     * @return entidade Student salva
     */
    @Transactional
    public Student createStudent(StudentRegistrationDTO dto) {
        // Busca o endereço a partir do CEP
        AddressDTO addressDTO = addressService.getAddress(dto.getCep());

        // Cria uma nova entidade Student
        Student student = new Student();
        student.setName(dto.getName());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setCourse(dto.getCourse());
        student.setCep(addressDTO.getCep());
        student.setStreet(addressDTO.getStreet());
        student.setNeighborhood(addressDTO.getNeighborhood());
        student.setCity(addressDTO.getCity());
        student.setState(addressDTO.getState());

        // Salva no repositório
        return studentRepository.save(student);
    }

    /**
     * Busca um estudante pelo seu ID.
     *
     * @param id ID do estudante
     * @return entidade Student encontrada ou null se não encontrado
     */
    @Transactional(readOnly = true)
    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    /**
     * Atualiza um estudante existente pelo ID, com base nos dados fornecidos no DTO.
     *
     * @param id  ID do estudante
     * @param dto DTO contendo os novos dados do estudante
     * @return entidade Student atualizada ou null se não encontrado
     */
    @Transactional
    public Student updateStudent(Long id, StudentRegistrationDTO dto) {
        // Busca o estudante pelo ID
        Student student = findById(id);
        if (student == null) {
            return null;
        }

        // Busca o endereço atualizado a partir do CEP
        AddressDTO addressDTO = addressService.getAddress(dto.getCep());

        // Atualiza os dados do estudante
        student.setName(dto.getName());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setCourse(dto.getCourse());
        student.setCep(addressDTO.getCep());
        student.setStreet(addressDTO.getStreet());
        student.setNeighborhood(addressDTO.getNeighborhood());
        student.setCity(addressDTO.getCity());
        student.setState(addressDTO.getState());

        // Salva as alterações no repositório
        return studentRepository.save(student);
    }
}

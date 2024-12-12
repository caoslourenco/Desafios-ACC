package acc.br.student_registration.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import acc.br.student_registration.dto.StudentRegistrationDTO;
import acc.br.student_registration.entity.Student;
import acc.br.student_registration.service.StudentService;

@Controller
@SessionAttributes("registrationData")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @ModelAttribute("registrationData")
    public StudentRegistrationDTO registrationData() {
        return new StudentRegistrationDTO();
    }

    @GetMapping("/student/form")
    public String showForm(@ModelAttribute("registrationData") StudentRegistrationDTO registrationDto) {
        return "form";
    }

    @PostMapping("/student/submit")
    public String submitForm(@ModelAttribute("registrationData") StudentRegistrationDTO registrationDto, 
                             @RequestParam String name,
                             @RequestParam String course,
                             @RequestParam String cep,
                             @RequestParam("dateOfBirth") String dob, 
                             Model model) {
        registrationDto.setName(name);
        registrationDto.setCourse(course);
        registrationDto.setCep(cep);
        try {
            registrationDto.setDateOfBirth(LocalDate.parse(dob));
        } catch (DateTimeParseException e) {
            model.addAttribute("error", "Data de nascimento inválida. Use o formato yyyy-MM-dd.");
            return "form";
        }
        return "redirect:/student/review";
    }

    @GetMapping("/student/review")
    public String reviewRegistration(@ModelAttribute("registrationData") StudentRegistrationDTO registrationDto, Model model) {
        model.addAttribute("registrationData", registrationDto);
        return "review";
    }

    @PostMapping("/student/confirm")
    public String confirmRegistration(@ModelAttribute("registrationData") StudentRegistrationDTO registrationDto, Model model) {
        Student saved = studentService.createStudent(registrationDto);
        model.addAttribute("registrationData", new StudentRegistrationDTO());
        return "success";
    }

    @PostMapping("/student/edit")
    public String editRegistration(@ModelAttribute("registrationData") StudentRegistrationDTO registrationDto) {
        return "form";
    }

    @PostMapping("/student/cancel")
    public String cancelRegistration(Model model) {
        model.addAttribute("registrationData", new StudentRegistrationDTO());
        return "form";
    }
}

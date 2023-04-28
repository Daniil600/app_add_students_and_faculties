package skypro.liberyofhogwarts;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import skypro.liberyofhogwarts.controller.StudentController;
import skypro.liberyofhogwarts.object.Student;
import skypro.liberyofhogwarts.repositories.FacultyRepository;
import skypro.liberyofhogwarts.repositories.StudentAvatarRepository;
import skypro.liberyofhogwarts.repositories.StudentRepository;
import skypro.liberyofhogwarts.service.FacultyServiceImpl;
import skypro.liberyofhogwarts.service.StudentAvatarService;
import skypro.liberyofhogwarts.service.StudentServiceImpl;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private FacultyRepository facultyRepository;

    @MockBean
    private StudentAvatarRepository studentAvatarRepository;
    @SpyBean
    private StudentServiceImpl studentService;

    @SpyBean
    private StudentAvatarService studentAvatarService;

    @SpyBean
    private FacultyServiceImpl facultyService;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void saveStudentTest() throws Exception {

        final String NAME = "Иван";
        final long ID = 1;
        final int AGE = 21;


        JSONObject studentObject = new JSONObject();

        studentObject.put("name", NAME);
        studentObject.put("id", ID);
        studentObject.put("age", AGE);

        Student student = new Student();
        student.setId(ID);
        student.setName(NAME);
        student.setAge(AGE);


        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/students")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.age").value(AGE));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/students/getby/id?id=" + ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.age").value(AGE));
    }

}

package skypro.liberyofhogwarts.controller;

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
import skypro.liberyofhogwarts.object.Faculty;
import skypro.liberyofhogwarts.object.Student;
import skypro.liberyofhogwarts.repositories.FacultyRepository;
import skypro.liberyofhogwarts.repositories.StudentAvatarRepository;
import skypro.liberyofhogwarts.repositories.StudentRepository;
import skypro.liberyofhogwarts.service.FacultyServiceImpl;
import skypro.liberyofhogwarts.service.StudentAvatarService;
import skypro.liberyofhogwarts.service.StudentServiceImpl;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class FacultyContollerTests {

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

        final String NAME = "Экономический";
        final long ID = 1l;
        final String COLOR = "Синий";


        JSONObject studentObject = new JSONObject();

        studentObject.put("name", NAME);
        studentObject.put("id", ID);
        studentObject.put("color", COLOR);

        Faculty faculty = new Faculty();
        faculty.setId(ID);
        faculty.setName(NAME);
        faculty.setColor(COLOR);


        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);



        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.color").value(COLOR));


    }

    @Test
    public void putStudentTest() throws Exception {

        final String NAME = "Экономический";
        final long ID = 1l;
        final String COLOR = "Синий";


        JSONObject studentObject = new JSONObject();

        studentObject.put("name", NAME);
        studentObject.put("id", ID);
        studentObject.put("color", COLOR);

        Faculty faculty = new Faculty();
        faculty.setId(ID);
        faculty.setName(NAME);
        faculty.setColor(COLOR);


        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);



        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.color").value(COLOR));


    }

    @Test
    public void delStudentTest() throws Exception {

        final String NAME = "Экономический";
        final long ID = 1l;
        final String COLOR = "Синий";


        JSONObject studentObject = new JSONObject();

        studentObject.put("name", NAME);
        studentObject.put("id", ID);
        studentObject.put("color", COLOR);

        Faculty faculty = new Faculty();
        faculty.setId(ID);
        faculty.setName(NAME);
        faculty.setColor(COLOR);



        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + ID)
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(facultyRepository).deleteById(ID);


    }

    @Test
    public void getStudentTest() throws Exception {

        final String NAME = "Экономический";
        final long ID = 1l;
        final String COLOR = "Синий";


        JSONObject studentObject = new JSONObject();

        studentObject.put("name", NAME);
        studentObject.put("id", ID);
        studentObject.put("color", COLOR);

        Faculty faculty = new Faculty();
        faculty.setId(ID);
        faculty.setName(NAME);
        faculty.setColor(COLOR);


        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));



        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/"+ID)
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.color").value(COLOR));


    }


}

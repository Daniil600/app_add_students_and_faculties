package skypro.liberyofhogwarts.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import skypro.liberyofhogwarts.object.Avatar;
import skypro.liberyofhogwarts.repositories.FacultyRepository;
import skypro.liberyofhogwarts.repositories.StudentAvatarRepository;
import skypro.liberyofhogwarts.repositories.StudentRepository;
import skypro.liberyofhogwarts.service.FacultyServiceImpl;
import skypro.liberyofhogwarts.service.StudentAvatarService;
import skypro.liberyofhogwarts.service.StudentServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest
public class AvatarControllerTest {
    @Autowired
    MockMvc mockMvc;

    @SpyBean
    private FacultyServiceImpl facultyService;

    @SpyBean
    private StudentServiceImpl studentService;

    @MockBean
    private FacultyRepository facultyRepository;
    @SpyBean
    private StudentAvatarService studentAvatarService;
    @MockBean
    private StudentAvatarRepository studentAvatarRepository;
    @MockBean
    private StudentRepository studentRepository;
    @InjectMocks
    private AvatarControllerTest avatarController;


    @Test
    void getAllByPage_success() {
        //Подготовка входных данных
        int page = 1;
        int size = 1;
        PageRequest pageRequest = PageRequest.of(page-1, size);

        Avatar firstAvatar = new Avatar();
        firstAvatar.setId(1l);

        Avatar secondAvatar = new Avatar();
        secondAvatar.setId(2l);

        List<Avatar> avatars = List.of(firstAvatar, secondAvatar);

        PageImpl<Avatar> pageable = new PageImpl<>(avatars);

        //Подготовка ожидаемого результата
        when(studentAvatarRepository.findAll(pageRequest)).thenReturn(pageable);

        //Начало теста
        List<Avatar> actualAvatars = studentAvatarService.getAllAvatars(page, size);
        assertEquals(avatars, actualAvatars);
        verify(studentAvatarRepository).findAll(pageRequest);
        verifyNoMoreInteractions(studentAvatarRepository);
    }


}

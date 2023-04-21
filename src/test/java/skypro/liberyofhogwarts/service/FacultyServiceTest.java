package skypro.liberyofhogwarts.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import skypro.liberyofhogwarts.object.Faculty;
import skypro.liberyofhogwarts.object.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@ContextConfiguration(classes = FacultyServiceImpl.class)
@ExtendWith(SpringExtension.class)
public class FacultyServiceTest {


}

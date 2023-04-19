package skypro.liberyofhogwarts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skypro.liberyofhogwarts.object.Faculty;
import skypro.liberyofhogwarts.object.Faculty;
import skypro.liberyofhogwarts.service.FacultyService;
import skypro.liberyofhogwarts.service.FacultyServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    @Autowired
    FacultyService facultyService;
    public FacultyController(FacultyServiceImpl facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAllFaculty(){
        return ResponseEntity.ok(facultyService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.getFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public ResponseEntity<Faculty> postFaculty(@RequestBody Faculty faculty) {
        Faculty createdFaculty = facultyService.addFaculty(faculty);
        return ResponseEntity.ok(createdFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> delFaculty(@PathVariable Long id) {
        Faculty delFaculty = facultyService.delFaculty(id);
        return ResponseEntity.ok(delFaculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> putFaculty(@RequestBody Faculty faculty) {
        Faculty putFaculty = facultyService.putFaculty(faculty);
        if (putFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(putFaculty);

    }



}

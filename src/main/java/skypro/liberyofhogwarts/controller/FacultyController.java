package skypro.liberyofhogwarts.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skypro.liberyofhogwarts.object.Avatar;
import skypro.liberyofhogwarts.object.Faculty;
import skypro.liberyofhogwarts.service.FacultyServiceImpl;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyServiceImpl facultyService;
    public FacultyController(FacultyServiceImpl facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAllFaculty(){
        return ResponseEntity.ok(facultyService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable long id) {
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
    public ResponseEntity delFaculty(@PathVariable long id) {
        facultyService.delFaculty(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Faculty> putFaculty(@RequestBody Faculty faculty) {
        Faculty putFaculty = facultyService.putFaculty(faculty);
        if (putFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(putFaculty);

    }

    @GetMapping("/most-length")
    public ResponseEntity getMostLenghthFaculty() {
        return ResponseEntity.ok(facultyService.getMostLenghthFaculty());

    }


    @GetMapping("/checkparallel")
    public ResponseEntity checkParaller() {
        return ResponseEntity.ok(facultyService.checkParaller());

    }
}

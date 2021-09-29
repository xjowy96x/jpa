package com.example.jpa.infrastructure.controller.student;

import com.example.jpa.infrastructure.dto.student.output.StudentOutputDto;
import com.example.jpa.infrastructure.application.student.StudentServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class FindStudentController {

    @Autowired
    StudentServicePort studentServicePort;

    @GetMapping
    public List<StudentOutputDto> getAll() {
        return studentServicePort.getAll();
    }

    @GetMapping("{id}")
    public StudentOutputDto getById(@PathVariable String id, @RequestParam(value="output", defaultValue = "simple") String output) {
        return studentServicePort.getById(id, output);
    }


}

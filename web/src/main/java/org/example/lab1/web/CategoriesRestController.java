package org.example.lab1.web;

import org.example.lab1.model.enumerations.Category;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/cat")
public class CategoriesRestController {

    @GetMapping
    public List<Category> getAll()
    {
        return Arrays.stream(Category.values()).toList();
    }

}

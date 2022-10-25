package ru.javabegin.backend.todo.caontrollers;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javabegin.backend.todo.entity.Category;
import ru.javabegin.backend.todo.entity.Priority;
import ru.javabegin.backend.todo.search.CategorySearchValues;
import ru.javabegin.backend.todo.search.PrioritySearchValues;
import ru.javabegin.backend.todo.service.PriorityService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/priority")
@AllArgsConstructor
public class PriorityController {

    private final PriorityService priorityService;

  /*  @PostMapping("/all")
    public List<Priority> findByUserEmailOrderByTitleAsc(@RequestBody String email) {
        return priorityService.findByUserEmailOrderByTitleAsc(email);
    }*/

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody Priority priority) {
        if (priority.getId() != null && priority.getId() != 0) {
            return new ResponseEntity("redutant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title must be not null", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityService.add(priority));
    }

    @PutMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestBody Category category) {
        // проверка на обязательные параметры
        if (category.getId() == null || category.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение title
        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        priorityService.update(category);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try{
            priorityService.delete(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("id" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Priority>> search(@RequestBody PrioritySearchValues prioritySearchValues) {
        // проверка на обязательные параметры
        if (prioritySearchValues.getEmail() == null || prioritySearchValues.getEmail().trim().length() == 0) {
            return new ResponseEntity("missed param: email", HttpStatus.NOT_ACCEPTABLE);
        }

        // поиск категорий пользователя по названию
        List<Priority> list = priorityService.findByTitle(prioritySearchValues.getTitle(), prioritySearchValues.getEmail());

        return ResponseEntity.ok(list);
    }

    @PostMapping("/id")
    public ResponseEntity findById(@RequestBody Long id) {

        Priority priority = null;

        try {
            priority = priorityService.findById(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priority);
    }
}
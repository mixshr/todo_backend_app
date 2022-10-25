package ru.javabegin.backend.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javabegin.backend.todo.entity.Category;
import ru.javabegin.backend.todo.entity.Priority;
import ru.javabegin.backend.todo.repo.PriorityRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class PriorityService {

    private final PriorityRepository priorityRepository;

    public List<Priority> findByTitle(String title, String email) {
        return priorityRepository.findByTitle(title, email);
    }

    /*public List<Priority> findByUserEmailOrderByAsc(String email) {
        return priorityRepository.findByUserEmailOrderByAsc(email);
    }*/

    public Priority findById(Long id) {
        return priorityRepository.findById(id).get();
    }

    public void delete(Long id) {
        priorityRepository.deleteById(id);
    }

    public void update(Category category) {

    }

    public Object add(Priority priority) {
        return priorityRepository.save(priority);
    }

  /*  public List<Priority> findByUserEmailOrderByTitleAsc(String email) {
        return priorityRepository.findByUserEmailOrderByAsc(email);
    }*/
}

package ru.javabegin.backend.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javabegin.backend.todo.entity.Category;
import ru.javabegin.backend.todo.repo.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    public List<Category> findByUserEmailOrderByTitleAsc(String email) {
        return categoryRepository.findByUserEmailOrderByTitleAsc(email);
    }

    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    public void update(Category category) {
        categoryRepository.save(category);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    public List<Category> findByTitle(String title, String email) {
        return categoryRepository.findByTitle(title, email);
    }
}
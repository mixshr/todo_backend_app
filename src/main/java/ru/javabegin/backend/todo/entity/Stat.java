package ru.javabegin.backend.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "stat", schema = "todolist", catalog = "postgres")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Cacheable
public class Stat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long uncompleted_total;

    private Long completed_total;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stat stat = (Stat) o;
        return Objects.equals(id, stat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

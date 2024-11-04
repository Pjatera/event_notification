package ru.javacourse.eventmanagement.db.entity.field;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "changes_integer")
public final class FieldChangeIntegerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "old_field")
    private Integer oldField;
    @Column(name = "new_field")
    private Integer newField;


}

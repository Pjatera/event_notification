package ru.javacourse.eventmanagement.db.entity.field;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "changes_date")
public final class FieldChangeDateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "old_field")
    private LocalDateTime oldField;
    @Column(name = "new_field")
    private LocalDateTime newField;


}

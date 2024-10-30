package ru.javacourse.event_notificator.db.entity.field;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "changes_long")
public final class FieldChangeLongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "old_field")
    private Long oldField;
    @Column(name = "new_field")
    private Long newField;


}

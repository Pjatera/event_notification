package ru.javacourse.event_notificator.db.entity.field;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "changes_string")
public final class FieldChangeStringEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "old_field")
    private String oldField;
    @Column(name = "new_field")
    private String newField;


}

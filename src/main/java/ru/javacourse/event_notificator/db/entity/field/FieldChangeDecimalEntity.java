package ru.javacourse.event_notificator.db.entity.field;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "changes_decimal")
public final class FieldChangeDecimalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "old_field")
    private BigDecimal oldField;
    @Column(name = "new_field")
    private BigDecimal newField;


}

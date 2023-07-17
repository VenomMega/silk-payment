package kz.alibek.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The BaseEntity class is a base class for all entity classes in the application.
 * It provides common properties like id, createdAt, and updatedAt which are common
 * to all the entities. This class is annotated with @MappedSuperclass, meaning
 * that properties of this class will be mapped to all the entity classes extending this base class.
 */
@Getter
@Setter
@MappedSuperclass
@ToString
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

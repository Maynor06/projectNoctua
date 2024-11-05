package com.nt.noctua.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reactions", uniqueConstraints = {@UniqueConstraint(columnNames = {"post_id", "user_id"})})
public class Reactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private ModelUser user;

    @Column( name = "date_create", nullable = false, updatable = false)
    private LocalDateTime dateCreated = LocalDateTime.now();

    public Reactions(Posts posts, ModelUser user) {
        this.posts = posts;
        this.user = user;
        this.dateCreated = LocalDateTime.now();
    }

}

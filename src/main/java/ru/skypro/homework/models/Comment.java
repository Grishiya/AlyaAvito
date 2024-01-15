package ru.skypro.homework.models;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
public class Comment implements OwnedEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "author_id",nullable = false)
    private UserEntity author;
    private Instant createdAt;
    @Column(nullable = false)
    private String text;
    @ManyToOne
    @JoinColumn(name = "ad_id",nullable = false)
    private Ad ad;

    public Integer getOwnerId() {
        return author.getId();
    }
}


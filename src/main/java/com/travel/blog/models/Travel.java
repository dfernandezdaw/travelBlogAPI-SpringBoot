package com.travel.blog.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "travels", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "title"
        })
})
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false, length = 1500)
    private String description;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "location", nullable = false)
    private String location;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Categories category;

    public Travel(String title, String description, String image, Date date, String location, Categories category) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.date = date;
        this.location = location;
        this.category = category;
    }
}

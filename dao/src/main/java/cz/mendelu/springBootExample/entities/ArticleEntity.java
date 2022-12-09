package cz.mendelu.springBootExample.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="article")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_article")
    private int id;

    @NotBlank
    @Column(name="name")
    private String name;
    @NotBlank
    @Column(name="text")
    private String text;

   @Column(name="date")
   private LocalDate date;

}

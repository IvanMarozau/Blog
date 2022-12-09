package cz.mendelu.springBootExample.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="person")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_person")
    private int id;
    @NotBlank
    @Column(name="name")
    private String name;
    @NotBlank
    @Column(name="surname")
    private String surname;




}

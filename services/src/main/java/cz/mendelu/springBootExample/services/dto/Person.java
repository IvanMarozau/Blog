package cz.mendelu.springBootExample.services.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public final class Person {

    private int id;

    private String name;

    private String surname;

}

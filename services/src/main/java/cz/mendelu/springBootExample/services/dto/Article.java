package cz.mendelu.springBootExample.services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public final class Article {

     private  int id;


     private String name;

     private String text;


     private String date;


}

package com.backend.demox.models;


import lombok.*;

import jakarta.persistence.*;
import java.util.Date;

/*
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
*/

@NoArgsConstructor
//@AllArgsConstructor
@Data

@Entity
public class Salesman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String surname;

    @Column(name="employment_date")
    private Date employmentDate;

}

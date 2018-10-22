package com.aluksik.fileupload.domains;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "linia_csv")
@Entity
public class LiniaCSV {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Lob
    private String linia;
}

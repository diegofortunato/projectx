package com.projectx.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    private Long id;
    private String name;
    private String document;
    private ArrayList<String> arrayList;
}

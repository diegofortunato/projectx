package com.projectx.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Document
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    private BigInteger id;

    @NonNull
    private String email;

    private String emailSHA512;

    @NonNull
    private String name;

    private Boolean verifiedEmail;

    @NonNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private ArrayList<Date> hintDateList;
}

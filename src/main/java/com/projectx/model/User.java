package com.projectx.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
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
    private String name;

    @NonNull
    private String document;

    @NonNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private ArrayList<Date> hintDateList;
}

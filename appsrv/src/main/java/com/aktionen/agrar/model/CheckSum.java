package com.aktionen.agrar.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class CheckSum {

    @Id
    @GeneratedValue
    private int id;
    private String checkSum;
    private Timestamp timestamp;

    @Lob
    private byte[] csvFile;

    private boolean changed;
}

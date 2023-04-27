package com.example.shortletBackend.entities;

import com.example.shortletBackend.enums.HouseType;
import com.example.shortletBackend.enums.PropertyType;
import com.example.shortletBackend.enums.Status;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

import com.example.shortletBackend.enums.HomeState;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.*;

@Entity@Getter @Setter@ToString
@AllArgsConstructor 
@Document(indexName = "shortlet") @Setting(settingPath = "static/es-settings.json")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Apartments {
    @Id @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field(type = FieldType.Keyword)
    @EqualsAndHashCode.Include
    private Long id;

    @Field(type = FieldType.Text)
    private String name;

    private String address;
    @Field(type = FieldType.Text)
    private String state;
    @NonNull @Field(type = FieldType.Text)
    private String country;
    private String continent;
    private String houseRefCode;
    @Enumerated(EnumType.STRING)
    @Field(type = FieldType.Text)
    private HomeState homeState;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Field(type = FieldType.Integer)
    private int price;
    private int cleaningFee;
    private int serviceFee = 50;
    private double rating;
    @Field(type = FieldType.Integer)
    private int maxNoOfGuests;
    @Field(type = FieldType.Integer)
    private int noOfBedrooms;
    @Field(type = FieldType.Integer)
    private int noOfBeds;
    @Field(type = FieldType.Integer)
    private int noOfBathrooms;

    @Enumerated(EnumType.STRING)
    @Field(type = FieldType.Text)
    private PropertyType propertyType;
    @Enumerated(EnumType.STRING)
    @Field(type = FieldType.Text)
    private HouseType houseType;

    @OneToOne
    private Amenities amenities;


    @ManyToOne
    private Users users;
    @Lob
    private String description;

    @OneToMany(mappedBy = "apartment")//,cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();
    @OneToMany//(cascade = CascadeType.ALL)
    private List<Pictures> pictures = new ArrayList<>();

    @OneToMany(mappedBy = "apartments")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "apartments")
    private List<Comments> comments = new ArrayList<>();


    public void setHouseRefCode(String name, int id) {
        this.houseRefCode = name + id;
    }

    public Apartments() {
        this.status = Status.UNOCCUPIED;
    }



}

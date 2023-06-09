package com.example.shortletBackend.entities;

import com.example.shortletBackend.enums.Role;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//@Getter @Setter @ToString
@Entity @ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;
    private String phoneNo;
    private String email;
    @Lob
    private String picture;
    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean activeUser = true;

    public Users(String name, String email, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;

    }
    public Users(String email, Role role) {
        this.email = email;
        this.role = role;
    }


    @OneToMany(mappedBy = "users")//,cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Apartments> apartmentsSet = new HashSet<>();

    @OneToMany(mappedBy = "users")//,cascade = CascadeType.ALL)
    private Set<Reservation> reservationSet=new HashSet<>();

    @OneToMany(mappedBy = "users")
    private Set<Review> reviewSet= new HashSet<>();
    @OneToMany(mappedBy = "users")
    private Set<Comments> comments = new HashSet<>();
    @OneToOne
    private Wallet wallet;

}

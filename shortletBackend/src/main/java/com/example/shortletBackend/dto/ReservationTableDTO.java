package com.example.shortletBackend.dto;

import com.example.shortletBackend.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor
@NoArgsConstructor
public class ReservationTableDTO {

    private long id;
    private String apartmentName;
    private long apartmentId;
    private String apartmentPicture;
    private String apartmentState;
    private String apartmentCountry;
    private Date checkInDate;
    private Date checkOutDate;
    private double apartmentRating;
    private PropertyType apartmentPropertyType;

    private int price;

}

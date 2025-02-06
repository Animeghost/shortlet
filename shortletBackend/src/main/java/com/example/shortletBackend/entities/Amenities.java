package com.example.shortletBackend.entities;

import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Field;

@Entity @Getter @Setter
@ToString @AllArgsConstructor
@NoArgsConstructor
public class  Amenities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean wifi;
    private boolean tv;
    private boolean kitchen;
    private boolean washer;
    private boolean free_parking;
    private boolean paid_parking;
    private boolean air_condition;
    private boolean work_space;
    private boolean pool;
    private boolean hot_tub;
    private boolean patio;
    private boolean bbq_grill;
    private boolean outdoor_dining;
    private boolean pool_table;
    private boolean fireplace;
    private boolean lake_access;
    private boolean beach_access;
    private boolean outdoor_shower;
    private boolean smoke_alarm;
    private boolean first_aid_kit;
    private boolean fire_extinguisher;
}

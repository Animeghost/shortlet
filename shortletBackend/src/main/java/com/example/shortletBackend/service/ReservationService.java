package com.example.shortletBackend.service;

import com.example.shortletBackend.dto.ReservationDTO;
import com.example.shortletBackend.dto.TextResponse;
import com.example.shortletBackend.entities.Apartments;
import com.example.shortletBackend.entities.Reservation;
import com.example.shortletBackend.entities.Users;
import com.example.shortletBackend.enums.ReservationState;
import com.example.shortletBackend.repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service @AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final ApartmentService apartmentService;
    private final ModelMapper mapper;


    public void save(Reservation reservation){
        reservationRepository.save(reservation);
    }
    public void changeState(Reservation reservation, ReservationState state){
        reservation.setReservationState(state);
        save(reservation);
    }

    public Optional<Reservation> findByReservationId(long id){
        return reservationRepository.findById(id);
    }
    public List<Reservation> findAllReservations(){
        return reservationRepository.findAll();
    }

    public ArrayList getReservationByApartmentId(long id){
        return reservationRepository.findAllByApartment_Id(id);
    }

    public TextResponse getReservationByHomes(String email, long id ){
        Optional<Apartments> apartments = apartmentService.findById(id);
        if (userService.findUserByEmail(email).get() == apartments.get().getUsers()){
            // checks if the user accessing the house is the owner
            ArrayList<ReservationDTO> reservationDTOS = new ArrayList<>();

            for (Object reserve : getReservationByApartmentId(id)) {
                updateReservation((Reservation) reserve);
                reservationDTOS.add(mapper.map(reserve, ReservationDTO.class));
            }
            return new TextResponse(reservationDTOS,200);
        }
        return new TextResponse("You are not the owner of the house",403);
    }

    public Reservation updateReservation(@NonNull Reservation reservation){
        if (reservation.getCheckInDate().before(new Date())){
            reservation.setReservationState(ReservationState.STARTED);
        }
        if (reservation.getCheckOutDate().before(new Date())){
            reservation.setReservationState(ReservationState.COMPLETED);
        }
        save(reservation);
        return reservation;
    }

    public TextResponse addReservation(Reservation reservation, String email, long home_id){
        Optional<Users> user = userService.findUserByEmail(email);
        Optional<Apartments> apartments= apartmentService.findById(home_id);

        if(user.isPresent()){
            if(apartments.isPresent()){

                reservation.setUsers(user.get());
                reservation.setApartment(apartments.get());
                reservation.setReservationState(ReservationState.PENDING);
                reservation.setReservationCode(apartments.get().getName().substring(0,2)+reservationRepository.findAll().size()+
                        user.get().getEmail().substring(0,1));
                user.get().getReservationSet().add(reservation);
                apartments.get().getReservations().add(reservation);

                apartmentService.save(apartments.get());
                reservationRepository.save(reservation);
                userService.save(user.get());
                return new TextResponse(reservation,200);
            }else {

                return new TextResponse("The house can not be found",HttpStatus.NOT_FOUND.value());
            }
        }else {
            return new TextResponse("The user can not be found",HttpStatus.NOT_FOUND.value());
        }
    }
}

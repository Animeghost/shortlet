package com.example.shortletBackend.service;

import com.example.shortletBackend.dto.ApartmentsDTO;
import com.example.shortletBackend.dto.TextResponse;
import com.example.shortletBackend.entities.Apartments;
import com.example.shortletBackend.entities.Users;
import com.example.shortletBackend.enums.HomeState;
import com.example.shortletBackend.enums.PropertyType;
import com.example.shortletBackend.enums.Status;
import com.example.shortletBackend.repositories.AmenitiesRepository;
import com.example.shortletBackend.repositories.ApartmentRepository;
import com.example.shortletBackend.repositories.PicturesRepository;
import com.example.shortletBackend.service.Impl.ApartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final AmenitiesRepository amenitiesRepo;
    private final PicturesRepository picRepo;
    private final TextResponse customResponse;
    private final UserServiceImpl userServiceImpl;
    private final MailService mailService;
    private final ModelMapper mapper;

    public void save(Apartments apartments){
        apartmentRepository.save(apartments);
    }
    public Optional<Apartments> findById(Long id){
        return apartmentRepository.findById(id);
    }
    public List<Apartments> findByUser(Users users){
        return apartmentRepository.findAllByUsers(users);
    }

    public List<Apartments> findAllApartments(){
        return apartmentRepository.findAll();
    }
    public List<Apartments> findAllApartmentByHomeState(HomeState state, Class dto){
        ArrayList hotelList = new ArrayList<>();
        for (Apartments hotel:apartmentRepository.findAllByHomeStateIs(state)
        ) {
            hotelList.add(mapper.map(hotel, dto));
        }
        return hotelList;
    }

    public TextResponse updateApartment(Apartments updatedApartments, String email , long id){
        Optional<Users> owner = userServiceImpl.findUserByEmail(email);
        Optional<Apartments> oldHouse = findById(id);
        if (owner.isPresent()) {
            if (oldHouse.get().getUsers() == owner.get()) {
                updatedApartments.setId(id);
                if (updatedApartments.getPictures() != null) {

                    picRepo.saveAll(updatedApartments.getPictures());
                }
                if (updatedApartments.getAmenities() != null) {
                    amenitiesRepo.save(updatedApartments.getAmenities());
                }

                updatedApartments.setHomeState(HomeState.PENDING);

                save(updatedApartments);
                return new TextResponse(updatedApartments,200);
            } else {

                return new TextResponse("This isn't your apartment ", HttpStatus.FORBIDDEN.value());
            }
        }else {
            customResponse.setMessage("You should really signup or login else you won't" +
                    " be able to do this ");
            return new TextResponse(customResponse, HttpStatus.FORBIDDEN.value());

        }
    }

    public List<ApartmentsDTO> getAllVerifiedHomesWithNumberOfGuest(int number){
        ArrayList<ApartmentsDTO> hotelList = new ArrayList<>();
        for (Apartments hotel:apartmentRepository.findAllByMaxNoOfGuestsGreaterThanEqualAndHomeState(number,HomeState.VERIFIED)
        ) {
            hotelList.add(mapper.map(hotel, ApartmentsDTO.class));
        }
        return hotelList;
    }

    public List<ApartmentsDTO> getAllVerifiedHomesWithPropertyType(PropertyType propertyType){
        ArrayList<ApartmentsDTO> hotelList = new ArrayList<>();
        for (Apartments hotel:apartmentRepository.findAllByPropertyTypeIsAndHomeState(propertyType,HomeState.VERIFIED)
        ) {
            hotelList.add(mapper.map(hotel, ApartmentsDTO.class));
        }
        return hotelList;
    }

    public Apartments addHome(Apartments apartments, Users users) {
        apartments.setHomeState(HomeState.PENDING);
        apartments.setHouseRefCode(apartments.getCountry().substring(0,2),apartmentRepository.findAll().size());
        if (apartments.getUsers() != null) {
           users.setPhoneNo(apartments.getUsers().getPhoneNo());

            userServiceImpl.save(users);
        }
            apartments.setUsers(users);

        changeStatus(apartments,Status.UNOCCUPIED);
//        mailService.sendHtmlMessage(users.getEmail(), "Pending Apartment Request","Your listing with the title "+apartments.getName()
//                        +" has been listed unverified please contact support for additional aid.",apartments.getUsers().getName()
//                ,"/index.html");
        return apartments;
    }

    public void changeStatus(Apartments apartments,Status status){
        apartments.setStatus(status);
        save(apartments);
    }

    public String deleteApartment(long id,String email) {
        if (apartmentRepository.findById(id).get().getUsers() == userServiceImpl.findUserByEmail(email).get()) {
            // check if the user deleting the apartment is the owner
            apartmentRepository.deleteById(id);
            return "The apartment has been deleted";
        } else {
            return "You don't have access to do it";
        }
    }
}

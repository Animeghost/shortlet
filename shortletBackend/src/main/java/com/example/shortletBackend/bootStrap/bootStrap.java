//package com.example.shortletBackend.bootStrap;
//
//import com.example.shortletBackend.entities.*;
//import com.example.shortletBackend.enums.*;
//import com.example.shortletBackend.repositories.*;
//import lombok.AllArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.Date;
//
//@AllArgsConstructor
//@Component
//public class bootStrap implements CommandLineRunner {
//    private final ApartmentRepository apartmentRepo;
//    private final PicturesRepository picturesRepository;
//    private final UserRepository userRepository;
//    private final ReservationRepository reservationRepo;
//    private final AmenitiesRepository amenitiesRepository;
//    private final CommentRepository commentRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        Users Admin = new Users("Peter","peter@gmail.com",Role.ADMIN);
//        Users walter = new Users("Kristi J. Walters","walter@gmail.com",Role.USER);
//        Users kathy = new Users("Kathleen Kammer","kathy@gmail.com",Role.USER);
//        Users samy= new Users("Samuel Altieri","sami@gmail.com",Role.USER);
//        Users melisa = new Users("Melissa Marino","mari@gmail.com",Role.USER);
//        Users emily = new Users("Emily Hannah","emily@gmail.com",Role.USER);
//        Users olu = new Users("Oluchukwu Chiwetelu","olu@gmail.com",Role.USER);
//        Users chi = new Users("Chiabuotu Chinomso","chi@gmail.com",Role.USER);
//        Users isak = new Users("Isak Lennert","isak@gmail.com",Role.USER);
//        Users peter = new Users("Ladenika Peter","peterladenika@gmail.com",Role.ADMIN);
//        Users mogena = new Users("Mogena", "abdulai1398@student.babcock.edu.ng",Role.ADMIN);
//
//
//        userRepository.saveAll(new ArrayList<>(Arrays.asList(walter,Admin,kathy,samy,melisa,emily,olu,chi,isak,peter,mogena)));
//
//        Apartments firstHouse = new Apartments();
//        firstHouse.setAddress("Leece");
//        firstHouse.setState("Puglia");
//        firstHouse.setCountry("Italy");
//        firstHouse.setName("Saponea 13 Flat Design");
//        firstHouse.setHouseRefCode(firstHouse.getCountry().substring(0, 3), 1);
//        firstHouse.setPrice(533);
//        firstHouse.setRating(4.7);
//        firstHouse.setHomeState(HomeState.VERIFIED);
//        firstHouse.setNoOfBeds(3);
//        firstHouse.setNoOfBathrooms(2);
//        firstHouse.setNoOfBedrooms(3);
//        firstHouse.setDescription("My place is close to Apuan Park. We're 12 km from Castelnuovo di Garfagnana. You'll love my place because of its location and atmosphere. My place is good for couples, lonely adventurers, families (with kids), furry friends (pets), and groups of friends");
//        firstHouse.setHouseType(HouseType.WHOLE_HOUSE);
//        firstHouse.setPropertyType(PropertyType.APARTMENT);
//        firstHouse.setCleaningFee(15);
//
//
//
//
//        firstHouse.setMaxNoOfGuests(5);
//
//        Pictures picture1 = new Pictures();
//        picture1.setUrl("https://a0.muscache.com/im/pictures/83738e60-4654-4faa-af1a-f53d02acbe6c.jpg?im_w=1200 ");
//
//        Pictures picture2 = new Pictures();
//        picture2.setUrl("https://a0.muscache.com/im/pictures/69faa681-fb85-4817-86f6-9d727c3fe9d2.jpg?im_w=720");
//
//        Pictures picture3 = new Pictures();
//        picture3.setUrl("https://a0.muscache.com/im/pictures/miso/Hosting-43244736/original/a7d388b9-c419-4d1f-8566-26590a847300.jpeg?im_w=1200 ");
//
//        Pictures picture4 = new Pictures();
//        picture4.setUrl("https://a0.muscache.com/im/pictures/f4df0d13-ab67-4ca7-bfb8-946b2dc3aa00.jpg?im_w=1200 ");
//
//        Pictures picture5 = new Pictures();
//        picture5.setUrl("https://a0.muscache.com/im/pictures/b2fa90f6-1cac-4013-a40b-7653cf4872dd.jpg?im_w=720");
//
//
//        firstHouse.getPictures().addAll(new ArrayList<>(Arrays.asList(new Pictures[]{picture1, picture2, picture3, picture4, picture5})));
//        Amenities A = new Amenities();
//        A.setAir_condition(true);
//        A.setFireplace(false);
//        A.setBbq_grill(true);
//        firstHouse.setAmenities(A);
//        firstHouse.setUsers(walter);
//
//        Comments comment1= new Comments("We were fortunate to get a late check out on the last day which made a huge differnce to us. Good communication from the host.\n" +
//                "Would definitely recommend and is on my list for a return visit",new Date(2023 - 1900, Calendar.FEBRUARY, 25));
//        comment1.setUsers(kathy);
//        comment1.setApartments(firstHouse);
//        Comments comment2= new Comments("We had an amazing holiday . The villa was faultless . Location , size ,cleanliness . I will definitely be back again soon.",new Date(2023 - 1900, Calendar.MARCH, 05),emily,firstHouse);
//
//
//
//        //2nd
//        Apartments House2 = new Apartments();
//        House2.setAddress("Route de I'Ourika");
//        House2.setState("Marrakesh");
//        House2.setCountry("Morocco");
//        House2.setName("Room with a sunset view in the Ourika Valley");
//        House2.setHouseRefCode(House2.getCountry().substring(0, 3), 2);
//        House2.setPrice(1371);
//        House2.setRating(3.9);
//        House2.setHomeState(HomeState.VERIFIED);
//        House2.setMaxNoOfGuests(8);
//        House2.setDescription("The space\n" +
//                "The sunset room is furnished with a double bed and a separate sitting area with a sofa bed which can be made into a single bed. There is a storage space for luggage and clothing. The bathroom is equipped with a shower, a toilet and a washbasin. The room has a private terrace facing west with a table and chairs overlooking the mountains and river.\n" +
//                "Guest access\n" +
//                "The guesthouse offers a dining room with airco and heating as well as a large lounge area where drinks and meals can be enjoyed. There are also numerous terraces offering seating areas and great views of the valley.");
//        House2.setNoOfBeds(4);
//        House2.setNoOfBathrooms(3);
//        House2.setNoOfBedrooms(4);
//        House2.setHouseType(HouseType.PRIVATE_ROOM);
//        House2.setPropertyType(PropertyType.GUESTHOUSE);
//        House2.setCleaningFee(23);
//
//
//
//
//        Pictures picture6 = new Pictures();
//
//        picture6.setUrl("https://a0.muscache.com/im/pictures/6a9cae57-dfe1-4ea6-a570-84856e20d410.jpg?im_w=720");
//
//
//        Pictures picture7 = new Pictures();
//        picture7.setUrl("https://a0.muscache.com/im/pictures/52737d90-1113-42c9-8787-0911ed7444dc.jpg?im_w=1200");
//
//        Pictures picture8 = new Pictures();
//        picture8.setUrl("https://a0.muscache.com/im/pictures/babc53a6-6934-463c-b8f7-85d45692fa76.jpg?im_w=720");
//
//        Pictures picture9 = new Pictures();
//        picture9.setUrl("https://a0.muscache.com/im/pictures/f7016064-1a7b-4be7-b9bd-a938d3f59b07.jpg?im_w=1200 ");
//
//        Pictures picture10 = new Pictures();
//        picture10.setUrl("https://a0.muscache.com/im/pictures/2a3f591a-a8b3-4ef9-b1bf-d988b2c3bff5.jpg?im_w=1200 ");
//
//        House2.getPictures().addAll(new ArrayList<>(Arrays.asList(new Pictures[]{picture6, picture7, picture8,
//                picture9, picture10})));
//
//        Comments comment3= new Comments(" Very spacious, very clean fantastic location host was always prompt in a response if you had any questions. ",new Date(2023 - 1900, Calendar.MARCH, 25),olu,House2);
//        Comments comment4= new Comments("Great apartment - good size, great location, close to all the bars and restaurants, but set back enough so it isn’t noisy at all",new Date(2023 - 1900, Calendar.MARCH, 05),samy,House2);
//        Comments comment5= new Comments("Great villa brilliant location and fabulous pool,will definitely be back again ",new Date(2023 - 1900, Calendar.MARCH, 15),melisa,House2);
//
//        House2.setUsers(Admin);
//        Amenities h2 = new Amenities();
//        h2.setWifi(true);
//        h2.setTv(true);
//        h2.setKitchen(true);
//        h2.setAir_condition(true);
//
//        House2.setAmenities(h2);
//
//
//        //3rd
//        Apartments House3 = new Apartments();
//        House3.setAddress("Sant Ferriol");
//        House3.setState("La Garrotxa");
//        House3.setCountry("Spain");
//        House3.setName("Village house with charm in Garrotxa (Girona)");
//        House3.setHouseRefCode(House3.getCountry().substring(0, 5), 3);
//        House3.setPrice(838);
//        House3.setRating(5.0);
//        House3.setHomeState(HomeState.VERIFIED);
//        House3.setMaxNoOfGuests(3);
//        House3.setDescription("A stone village house. We live upstairs.\n" +
//                "Guest rooms are on the ground floor and are independent (except for the entrance, which we also use). The living room has a small kitchen (induction, two fires), with microwave, boiler, toaster, fridge, coffee maker, spade, etc.\n" +
//                "The room has seats, books, maps. It has a single bed (with a nest below).\n" +
//                "We have a small garden for the guests. The sink is independent and new. Guest spaces are hygienically safe.\n" +
//                "The space\n" +
//                "The double bed measures 1'50 cm. You don't have to go upstairs. There is only one (new) sink for guests. There is a separate entrance (for the room, with a single small grain), but if not, there is also a general entrance to share with us. We have a wired internet connection and also wifi. Our kitchen is small (a stove), also has microwaves and a small refrigerator. There are dishes, silverware, drops, roaster, kettle, coffee maker and everything you need.\n" +
//                "Guest access\n" +
//                "The ground floor for guests is separate. It's just share the main entrance to the house. Guests can enter through a second separate entrance if they wish.\n" +
//                "Other things to note\n" +
//                "We're a quiet family. We also thank our guests for their peace of mind. Avoid noisy holidays and respect the neighborhood.\n" +
//                "\n" +
//                "No smoking in our house. Guest pets are also not allowed.\n" +
//                "License number\n" +
//                "HUTG-046620");
//        House3.setNoOfBeds(1);
//        House3.setNoOfBathrooms(2);
//        House3.setNoOfBedrooms(1);
//        House3.setHouseType(HouseType.PRIVATE_ROOM);
//        House3.setPropertyType(PropertyType.BED_AND_BREAKFAST);
//        House3.setCleaningFee(65);
//
//
//
//        Pictures picture11 = new Pictures();
//        picture11.setUrl("https://a0.muscache.com/im/pictures/6e75f583-5c1f-41e8-b705-511dbffe92b5.jpg?im_w=1200 ");
//
//        Pictures picture12 = new Pictures();
//        picture12.setUrl("https://a0.muscache.com/im/pictures/miso/Hosting-48307356/original/8dd3d5c3-968b-414a-af09-9fdbf33212f7.jpeg?im_w=720 ");
//
//        Pictures picture13 = new Pictures();
//        picture13.setUrl("https://a0.muscache.com/im/pictures/2fc8c4b9-74ff-4a40-8fe7-acad214834e7.jpg?im_w=720 ");
//
//        Pictures picture14 = new Pictures();
//        picture14.setUrl("https://a0.muscache.com/im/pictures/4626d93b-e838-47c6-8b9b-01eee3e199e4.jpg?im_w=720 ");
//
//        Pictures picture15 = new Pictures();
//        picture15.setUrl("https://a0.muscache.com/im/pictures/miso/Hosting-48307356/original/7fd97148-3723-48d8-b2ca-b37dfe275ffd.jpeg?im_w=720 ");
//
//        House3.getPictures().addAll(new ArrayList<>(Arrays.asList(new Pictures[]{picture11, picture12, picture13, picture14, picture15})));
//        House3.setUsers(samy);
//        Amenities h3=new Amenities();
//        h3.setPaid_parking(true);
//        h3.setWasher(true);
//        h3.setWifi(true);
//        h3.setTv(true);
//        h3.setKitchen(true);
//        House3.setAmenities(h3);
//
//        Comments comment6= new Comments(" All facilities in the villa are excellent, full kitchen, WiFi, TV with Netflix etc. and washing machine too.",new Date(2022 - 1900, Calendar.DECEMBER, 25),chi,House3);
//        Comments comment7= new Comments("Thank you so much for letting us stay in this beautiful location",new Date(2023 - 1900, Calendar.JANUARY, 25),Admin,House3);
//        Comments comment8= new Comments("We found a home far away from home. Cosy apartment with a breathtaking view!",new Date(2023 - 1900, Calendar.MARCH, 25),walter,House3);
//
//
//
//        //4
//        Apartments House4 = new Apartments();
//        House4.setAddress("Al Haouz");
//        House4.setState("Marrakesh-Safi");
//        House4.setCountry("Morocco");
//        House4.setName("Ideal for a romantic getaway !");
//        House4.setHouseRefCode(House4.getCountry().substring(0, 3), 4);
//        House4.setPrice(734);
//        House4.setRating(4.5);
//        House4.setHomeState(HomeState.VERIFIED);
//        House4.setMaxNoOfGuests(2);
//        House4.setUsers(melisa);
//        House4.setDescription("Our moroccan Manager, Moutaa, will make you live an unforgettable experience : feel like at home in Marrakech ! Airport transfer, table d'hote, musical evening, guided tour, massages and treatments on-site, as many services at your disposal. Our delicious breakfasts are included ! Welcome to your home !\n" +
//                "The space\n" +
//                "Riad Chamali is a guest house with pool, in the medina of Marrakech, ideal for romantic or family holiday.\n" +
//                "\n" +
//                "We are located 15 minutes walk from the famous Jemaa El Fna square, in the quiet and traditional neighborhood of Berrima (Badii Palace, the old Jewish quarter, tinsmiths' square, the Bahia Palace ...) near the door Bab Hmar, which gives quick access to the Golf Courses.\n" +
//                "\n" +
//                "Our Riad offers a perfect combination of traditional lifestyle and modern high standard facilities. The riad decoration is sleek, warm and feels natural with paintings and rugs made from local craftmen. The architecture is from the original house called \"Zelliges\" which gives the place a friendly, peaceful and out of time atmosphere.\n"
//                );
//        House4.setNoOfBeds(2);
//        House4.setNoOfBathrooms(1);
//        House4.setNoOfBedrooms(1);
//        House4.setHouseType(HouseType.PRIVATE_ROOM);
//        House4.setPropertyType(PropertyType.BED_AND_BREAKFAST);
//        House4.setCleaningFee(45);
//
//
//
//
//        Pictures picture16 = new Pictures();
//        picture16.setUrl("https://a0.muscache.com/im/pictures/11755737/cbefb5d6_original.jpg?im_w=1200 ");
//
//        Pictures picture17 = new Pictures();
//        picture17.setUrl("https://a0.muscache.com/im/pictures/11755725/f3d5fd5d_original.jpg?im_w=720 ");
//
//        Pictures picture18 = new Pictures();
//        picture18.setUrl("https://a0.muscache.com/im/pictures/11755759/3bfdf199_original.jpg?im_w=720 ");
//
//        Pictures picture19 = new Pictures();
//        picture19.setUrl("https://a0.muscache.com/im/pictures/11755763/53c0aaa9_original.jpg?im_w=720 ");
//
//        Pictures picture20 = new Pictures();
//        picture20.setUrl("https://a0.muscache.com/im/pictures/890deadc-040e-43b7-ad5a-2391ab90e314.jpg?im_w=720 ");
//
//        House4.getPictures().addAll(new ArrayList<>(Arrays.asList(new Pictures[]{picture16, picture17, picture18, picture19, picture20})));
//        Amenities h4=new Amenities();
//        h4.setBbq_grill(true);
//        h4.setOutdoor_dining(true);
//        h4.setLake_access(true);
//        h4.setSmoke_alarm(true);
//        h4.setFirst_aid_kit(true);
//        h4.setFire_extinguisher(true);
//        House4.setAmenities(h4);
//
//        Comments comment9= new Comments("Very nice stay! Very cozy and amazing view of the ocean!\n" +
//                "Too bad we couldn't stay longer",new Date(2023 - 1900, Calendar.FEBRUARY, 25),kathy,House4);
//        Comments comment10= new Comments("We loved it",new Date(2023 - 1900, Calendar.MARCH, 5),isak,House4);
//
//
//        Apartments House5 = new Apartments();
//        House5.setAddress("Osaka");
//        House5.setState("Honshu");
//        House5.setCountry("Japan");
//        House5.setName("Calm Home");
//        House5.setHouseRefCode(House5.getCountry().substring(0, 3), 5);
//        House5.setPrice(1265);
//        House5.setRating(4.7);
//        House5.setMaxNoOfGuests(3);
//        House5.setHomeState(HomeState.VERIFIED);
//        House5.setUsers(emily);
//        House5.setNoOfBeds(2);
//        House5.setDescription("A beautiful and centrally-located place with superior view of the sea, the Portara and the magical sunset colours. It has two bedrooms (one with double bed and one with single bed), fully equipped kitchen and it can accommodate up to 4 guests. Relax and enjoy your stay within walking distance from Naxos Town port and all the resort amenities. Parking available.\n" +
//                "License number\n" +
//                "00001306417");
//        House5.setNoOfBathrooms(2);
//        House5.setNoOfBedrooms(2);
//        House5.setHouseType(HouseType.PRIVATE_ROOM);
//        House5.setPropertyType(PropertyType.TREEHOUSE);
//        House5.setCleaningFee(10);
//
//
//
//
//        Pictures picture21 = new Pictures();
//        picture21.setUrl("https://a0.muscache.com/im/pictures/32233013/785da15f_original.jpg?im_w=1200 ");
//
//        Pictures pictures22 = new Pictures();
//        pictures22.setUrl("https://a0.muscache.com/im/pictures/32233110/70706ded_original.jpg?im_w=720 ");
//
//        Pictures pictures23 = new Pictures();
//        pictures23.setUrl("https://a0.muscache.com/im/pictures/32233356/fe6bbadd_original.jpg?im_w=720 ");
//
//        Pictures pictures24 = new Pictures();
//        pictures24.setUrl("https://a0.muscache.com/im/pictures/32233280/b60c45d8_original.jpg?im_w=720");
//
//        Pictures pictures25 = new Pictures();
//        pictures25.setUrl("https://a0.muscache.com/im/pictures/658f0839-9f2a-4bd5-a878-b25b5ef284fc.jpg?im_w=720 ");
//
//        House5.getPictures().addAll(new ArrayList<>(Arrays.asList(new Pictures[]{picture21, pictures22, pictures23, pictures24, pictures25})));
//
//        Amenities h5= new Amenities();
//        h5.setPool(true);
//        h5.setTv(true);
//        h5.setPool(true);
//        h5.setAir_condition(true);
//        h5.setWasher(true);
//        House5.setAmenities(h5);
//
//        Comments comment11= new Comments("100% TOP experience",new Date(2023 - 1900, Calendar.MARCH, 2),melisa,House5);
//        Comments comment12= new Comments("We had a wonderful time and would love to come back. Thanks!",new Date(2023 - 1900, Calendar.MARCH, 4),chi,House5);
//
//
//
//        Apartments House6 = new Apartments();
//        House6.setAddress("Chania");
//        House6.setState("Crete");
//        House6.setCountry("Greece");
//        House6.setName("Maik Beyer");
//        House6.setHouseRefCode(House6.getCountry().substring(0, 3), 6);
//        House6.setPrice(923);
//        House6.setRating(5.0);
//        House6.setHomeState(HomeState.VERIFIED);
//        House6.setMaxNoOfGuests(4);
//        House6.setUsers(melisa);
//        House6.setDescription("The house has very bright spaces, lots of natural light, the decoration is very fresh and fun with some antique elements that give it a special touch.\n" +
//                "The space\n" +
//                "The sound of the waves of the sea floods every corner of the house. It's a unique place to disconnect from the outside world and connect with nature. Ideal for surfers due to location.");
//        House6.setNoOfBeds(4);
//        House6.setNoOfBathrooms(2);
//        House6.setNoOfBedrooms(3);
//        House6.setHouseType(HouseType.WHOLE_HOUSE);
//        House6.setPropertyType(PropertyType.APARTMENT);
//        House6.setCleaningFee(90);
//
//
////
//
//
//        Pictures pictures26 = new Pictures();
//        pictures26.setUrl("https://a0.muscache.com/im/pictures/prohost-api/Hosting-2631155/original/e5f83d80-6e85-4cdc-99b4-ae9cc89e7020.jpeg?im_w=1200 ");
//
//        Pictures pictures27 = new Pictures();
//        pictures27.setUrl("https://a0.muscache.com/im/pictures/prohost-api/Hosting-2631155/original/cf757cd5-7375-4c33-9b58-55a91b512567.jpeg?im_w=720 ");
//
//        Pictures pictures28 = new Pictures();
//        pictures28.setUrl("https://a0.muscache.com/im/pictures/prohost-api/Hosting-2631155/original/70cbf62d-e9cf-4c0e-ae2f-c3029f4cb94d.jpeg?im_w=720 ");
//
//        Pictures pictures29 = new Pictures();
//        pictures29.setUrl("https://a0.muscache.com/im/pictures/prohost-api/Hosting-2631155/original/a164f978-39f4-484d-b544-e1da91b9d470.jpeg?im_w=720 ");
//
//        Pictures pictures30 = new Pictures();
//        pictures30.setUrl("https://a0.muscache.com/im/pictures/prohost-api/Hosting-2631155/original/c19722b5-e048-4778-b4d3-4576c62e0b70.jpeg?im_w=720 ");
//
//        House6.getPictures().addAll(new ArrayList<>(Arrays.asList(new Pictures[]{pictures26, pictures27, pictures28, pictures29, pictures30})));
//
//        Amenities h6=new Amenities();
//        h6.setFree_parking(true);
//        h6.setWifi(true);
//        h6.setFirst_aid_kit(true);
//        h6.setFire_extinguisher(true);
//        House6.setAmenities(h6);
//
//        Comments comment13= new Comments("A house with extraordinary views! We had a great time at this very well decorated and comfortably furnished house",new Date(2023 - 1900, Calendar.MARCH, 20),isak,House6);
//        Comments comment14= new Comments("The apartment is spacious and equipped with every comfort. The view over the ocean is amazing!",new Date(2023 - 1900, Calendar.MARCH, 15),Admin,House6);
//
//
//
//        Apartments House7 = new Apartments();
//        House7.setAddress("La Lajita");
//        House7.setState("Canary Islands");
//        House7.setCountry("Spain");
//        House7.setName("La Lajita Barca Beach Sunset");
//        House7.setHouseRefCode(House7.getCountry().substring(0, 3), 7);
//        House7.setPrice(182);
//        House7.setRating(4.9);
//        House7.setHomeState(HomeState.VERIFIED);
//        House7.setMaxNoOfGuests(2);
//        House7.setUsers(isak);
//        House7.setDescription("Enjoy the best views of Fuerteventura with an incredible location, with the sea and a serene climate where you can enjoy an ideal stay.\n" +
//                "\n" +
//                "It is an outdoor vacation apartment located a few meters from the beach in a typical fishing village, where you can taste the fish of the day.\n" +
//                "It consists of two bedrooms, a living room with sofa bed and kitchenette, a bathroom and a balcony with spectacular ocean views.");
//        House7.setNoOfBeds(1);
//        House7.setNoOfBathrooms(1);
//        House7.setNoOfBedrooms(1);
//        House7.setHouseType(HouseType.WHOLE_HOUSE);
//        House7.setPropertyType(PropertyType.TINY_HOME);
//        House7.setCleaningFee(32);
//
//
//
//        Pictures pictures33 = new Pictures();
//        pictures33.setUrl("https://a0.muscache.com/im/pictures/miso/Hosting-49937547/original/0109bbe0-90ca-4645-8112-99b4544a372b.jpeg?im_w=1200 ");
//
//        Pictures pictures31 = new Pictures();
//        pictures31.setUrl("https://a0.muscache.com/im/pictures/miso/Hosting-50058763/original/2f6f2b10-447e-4486-adeb-9420341cb8ab.jpeg?im_w=720 ");
//
//        Pictures pictures32 = new Pictures();
//        pictures32.setUrl("https://a0.muscache.com/im/pictures/miso/Hosting-50058763/original/9ffcb84a-f773-4a03-8e78-7b42bda45107.jpeg?im_w=720 ");
//
//        Pictures pictures34 = new Pictures();
//        pictures34.setUrl("https://a0.muscache.com/im/pictures/miso/Hosting-50058763/original/8174450f-3d6b-4090-95f2-bc6582063c49.jpeg?im_w=720 ");
//
//        Pictures pictures35 = new Pictures();
//        pictures35.setUrl("https://a0.muscache.com/im/pictures/miso/Hosting-50058763/original/105f5096-1885-498e-88e3-5c7805700227.jpeg?im_w=720 ");
//
//        House7.getPictures().addAll(new ArrayList<>(Arrays.asList(new Pictures[]{pictures31, pictures32, pictures33, pictures35, pictures34})));
//
//        Amenities h7 =new Amenities();
//        h7.setFire_extinguisher(true);
//        h7.setSmoke_alarm(true);
//        h7.setWifi(true);
//        h7.setLake_access(true);
//        h7.setFree_parking(true);
//        h7.setWork_space(true);
//        House7.setAmenities(h7);
//
//        Comments comment15= new Comments(" The view is unforgettable.",new Date(2023 - 1900, Calendar.JANUARY, 15),kathy,House7);
//        Comments comment16= new Comments("Just unique and amazing place to stay. ",new Date(2023 - 1900, Calendar.JANUARY, 20),walter,House7);
//
//        House7.getComments().addAll(new ArrayList<>(Arrays.asList(comment15,comment16)));
//
//        Apartments House8 = new Apartments();
//        House8.setAddress("Mogan");
//        House8.setState("Gran Canaria");
//        House8.setCountry("Spain");
//        House8.setName("10th century medieval Castle");
//        House8.setHouseRefCode(House8.getCountry().substring(0, 3), 8);
//        House8.setPrice(374);
//        House8.setRating(5.0);
//        House8.setHomeState(HomeState.VERIFIED);
//        House8.setMaxNoOfGuests(8);
//        House8.setUsers(Admin);
//        House8.setDescription("In the Ripollès region, between rivers, valleys and mountains, the ancient Castle of Llaés (10th century) stands splendidly. A unique place, of exceptional beauty, where absolute tranquility reigns in the middle of an exuberant nature.\n" +
//                "The Castle has been fully renovated for the comfort required by the facilities for rural tourism, with 8 rooms, 5 with a double bed, and 3 with two single beds. It has a living room, dining room, kitchen, 4 bathrooms, garden and terrace.\n" +
//                "The space\n" +
//                "The Llaés Castle is a project of reform and maintenance of this national cultural asset that, by force of spirit and the guests from all over the world who come to visit us, can continue its journey.\n" +
//                "Guest access\n" +
//                "Currently the fortification retains the physiognomy of a castle, the homage tower, the parade ground, and the religious enclosure with a garden area, the small cemetery, and the church of San Bartolomé, in a marked Romanesque style.\n" +
//                "Other things to note\n" +
//                "Access to the castle is not prepared for people with reduced mobility since the last 50m are on an ascending ramp and only accessible on foot.\n" +
//                "\n" +
//                "Events and parties are not allowed without an application, budget request and express authorization are required.\n" +
//                "License number\n" +
//                "PG000900");
//        House8.setNoOfBeds(7);
//        House8.setNoOfBathrooms(6);
//        House8.setNoOfBedrooms(6);
//        House8.setHouseType(HouseType.WHOLE_HOUSE);
//        House8.setPropertyType(PropertyType.CASTLE);
//        House8.setCleaningFee(100);
//
//
//
//        Pictures pictures36 = new Pictures();
//        pictures36.setUrl("https://a0.muscache.com/im/pictures/miso/Hosting-842511498702667444/original/ca793819-9385-412e-87ec-a0562b674f42.jpeg?im_w=720");
//
//        Pictures pictures37 = new Pictures();
//        pictures37.setUrl("https://a0.muscache.com/im/pictures/b0e551a4-fd58-44ec-9be7-10f53da910ea.jpg?im_w=720 ");
//
//        Pictures pictures38 = new Pictures();
//        pictures38.setUrl("https://a0.muscache.com/im/pictures/2543a9bc-0f49-40c6-acf9-427367f1f5fe.jpg?im_w=720 ");
//
//        Pictures pictures39 = new Pictures();
//        pictures39.setUrl("https://a0.muscache.com/im/pictures/e995e3b0-c9cd-4ffb-aad4-0f76f4e00a0a.jpg?im_w=720 ");
//
//        Pictures pictures40 = new Pictures();
//        pictures40.setUrl("https://a0.muscache.com/im/pictures/05f93fe3-da99-485d-aada-f8224d714d97.jpg?im_w=720 ");
//
//        House8.getPictures().addAll(new ArrayList<>(Arrays.asList(new Pictures[]{pictures36, pictures37, pictures38, pictures39, pictures40})));
//
//        Amenities h8=new Amenities();
//        h8.setFire_extinguisher(true);
//        h8.setWork_space(true);
//        h8.setBeach_access(true);
//        h8.setOutdoor_dining(true);
//        h8.setWork_space(true);
//        House8.setAmenities(h8);
//
//        Comments comment17= new Comments("This is perfect for you if you wish to get away from the tourist crowds",new Date(2023 - 1900, Calendar.MARCH, 25),samy,House8);
//        Comments comment18= new Comments("We had a spectacular time, 100% recommended, excellent service and attention, I'm still in love",new Date(2022 - 1900, Calendar.NOVEMBER, 25),melisa,House8);
//
//
//
//        Apartments House9 = new Apartments();
//        House9.setAddress("Lekki ");
//        House9.setState("Lagos");
//        House9.setCountry("Nigeria");
//        House9.setName("Jacuzzi and heated floor in orchid road");
//        House9.setHouseRefCode(House9.getCountry().substring(0, 3), 9);
//        House9.setPrice(380);
//        House9.setRating(5.0);
//        House9.setHomeState(HomeState.VERIFIED);
//        House9.setMaxNoOfGuests(6);
//        House9.setUsers(olu);
//        House9.setDescription("Take it easy at this unique and tranquil getaway.");
//        House9.setNoOfBeds(6);
//        House9.setNoOfBathrooms(4);
//        House9.setNoOfBedrooms(6);
//        House9.setHouseType(HouseType.PRIVATE_ROOM);
//        House9.setPropertyType(PropertyType.HOTEL);
//        House9.setCleaningFee(150);
//
//
//
//        Pictures pictures41 = new Pictures();
//        pictures41.setUrl("https://a0.muscache.com/im/pictures/miso/Hosting-822280454813797636/original/28910836-2cbf-4793-ad56-9068d8414ebb.jpeg?im_w=720");
//
//        Pictures pictures42 = new Pictures();
//        pictures42.setUrl("https://a0.muscache.com/im/pictures/miso/Hosting-51327545/original/286568cd-c8aa-4665-a73d-ec6cd6884da6.jpeg?im_w=720 ");
//
//        Pictures pictures43 = new Pictures("https://a0.muscache.com/im/pictures/miso/Hosting-51327545/original/c0cae2de-aaf1-4180-920d-cde5c0d9bb92.jpeg?im_w=720 ");
//
//
//        Pictures pictures44 = new Pictures("https://a0.muscache.com/im/pictures/miso/Hosting-51327545/original/1975c982-9712-4fcd-8380-ff1ca1207f8c.jpeg?im_w=720 ");
//
//        Pictures pictures45 = new Pictures("https://a0.muscache.com/im/pictures/miso/Hosting-51327545/original/430eaa3d-9a8a-45f9-b652-bb110ec0dd0b.jpeg?im_w=720 ");
//
//        House9.getPictures().addAll(new ArrayList<>(Arrays.asList(new Pictures[]{pictures41, pictures42, pictures43, pictures44, pictures45})));
//
//        Amenities h9 = new Amenities();
//        h9.setFire_extinguisher(true);
//        h9.setPool(true);
//        h9.setWifi(true);
//        h9.setPaid_parking(true);
//        h9.setSmoke_alarm(true);
//        h9.setWasher(true);
//        h9.setPool_table(true);
//        h9.setWasher(true);
//        House9.setAmenities(h9);
//
//        Comments comment19= new Comments("Incredible accommodation.",new Date(2022 - 1900, Calendar.DECEMBER, 25),olu,House9);
//        Comments comment20= new Comments("This house is absolutely amazing! An unforgettable view:)",new Date(2022 - 1900, Calendar.DECEMBER, 20),kathy,House9);
//
//
//
//        Apartments House10 = new Apartments();
//        House10.setAddress("Ikoyi");
//        House10.setState("Lagos State");
//        House10.setCountry("Nigeria");
//        House10.setName("Mediterranean Island Beach-House Oasis 2br in Vi");
//        House10.setHouseRefCode(House10.getCountry().substring(0, 5), 10);
//        House10.setPrice(120);
//        House10.setRating(3.9);
//        House10.setHomeState(HomeState.VERIFIED);
//        House10.setMaxNoOfGuests(5);
//        House10.setUsers(chi);
//        House10.setDescription("Relax, unwind and enjoy this stunning Mediterranean-inspired Duplex getaway on Victoria Island, spectacular lagoon view from all the large azure windows. It is fully equipped for short or long-term stays. The beach house is situated on the 12th Floor in an unbeatable touristic and business location, near beaches, the coolest clubs, shops, bars, and restaurants.\n" +
//                "\n" +
//                "There is space for walking, jogging and playing tennis. Suitable for families, couples, individuals & business travelers.\n" +
//                "The space\n" +
//                "A beach house in the city? That’s exactly the unique combination you get from this beautifully presented 12th floor Mediterranean-inspired duplex apartment on Victoria Island, Lagos.\n" +
//                "\n" +
//                "As soon as you step inside, you’re sure to appreciate how much time and effort your host has spent in making this relaxing and inspiring getaway just right. Every piece of furniture in the property has been meticulously designed and crafted by your host, demonstrating their passion for interior design, and desire to make everyone who stays here feel special and welcome.\n" +
//                "\n" +
//                "This home-from-home is ideal for friends or family looking for a short to long-term stay in Lagos for pleasure, or corporate travelers looking for easy access to the business district.\n" +
//                "\n" +
//                "The large azure windows give every guest majestic views of the island lagoon on which the property is located. There are incredible views to be enjoyed from sunrise to sunset. And why not try to find the seashells and other beach treasures the owner’s young daughter has hidden around the property?\n" +
//                "\n" +
//                "1ST FLOOR:\n" +
//                "Lounge & Dining Area\n" +
//                "This bright and open area continues the white and blue theme of the interior, all designed and made by your host, remember! Take time to relax on the comfortable seating in the lounge area, maybe to watch the smart TV with DSTV cable channels available, plus access to the Netflix account.\n" +
//                "\n" +
//                "If you’re visiting on business, the work desk and chair are sure to come in handy, so to the 30MBPS unlimited data fiber-optic WiFi. The dining table can also be used as a workspace, or to host any special occasion meals you might have planned to celebrate an important milestone for a friend or family member. Last but not least, there’s a wooden bar area, great for relaxing at, perhaps with a drink at the end of a busy day.\n" +
//                "\n" +
//                "Kitchen\n" +
//                "You’ll discover quality European-style appliances in here, including a kettle, toaster, sandwich toaster, blender, and Nespresso coffee machine, plus everything you need to prepare delicious meals and snacks. For your convenience, a serving hatch connects the kitchen to the dining area in the main lounge area.\n" +
//                "\n" +
//                "Restroom / Toilet\n" +
//                "The first floor has a convenient WC with a washbasin\n" +
//                "\n" +
//                "2ND FLOOR\n" +
//                "2 Bedrooms\n" +
//                "Both of the bedrooms are located on the upper floor. The first bedroom features a King size bed with luxury Egyptian cotton linen, as well as a brand-new gloriously comfortable mattress that guarantees perfect rest throughout your visit, day or night.\n" +
//                "\n" +
//                "The second bedroom has a king-size bed that can be split into two single beds based on your needs. Please let your host know how you’d like these beds arranged before your stay.\n" +
//                "\n" +
//                "Main Bathroom\n" +
//                "You can’t fail to feel reinvigorated and ready for whatever the new day has in store after freshening up under the rainfall shower. Your host will supply towels and a range of bathroom amenities, including soap, shampoo, conditioner, and body wash.\n" +
//                "\n" +
//                "Small sitting room\n" +
//                "There’s another break-out area up here with two host-made pallet couches, great for relaxing on. Maybe you’ll want to use this space to read one of the many books you’ll find in the apartment. As a special gift, your host invites you to take home any book you like as a souvenir of your stay.\n" +
//                "\n" +
//                "Laundry\n" +
//                "Handy if you’re planning a long-term stay, the washer and portable clothesline are sure to come in useful, and many nearby laundries are ready to pick up ur cloth.\n" +
//                "\n" +
//                "OUTSIDE SPACE\n" +
//                "Stay here, and you will have your own balcony with space to sit outside and enjoy the view, perhaps with a drink. There is also a dedicated parking space you can use, and lots of free parking in the local area.\n" +
//                "Guest access\n" +
//                "Your host is delighted to make this apartment exclusively and entirely available to you for however long you make your reservation. The apartment is treasured by your host who has handmade the furniture inside. Ensuring you enjoy a relaxing time here is the top priority, so expect your host to react quickly and professionally to any questions you might have, ensuring your stay is hassle-free.\n" +
//                "Other things to note\n" +
//                "-Airport transfers directly to apartments are available for a small fee. Please contact us to arrange it.\n" +
//                "\n" +
//                "-Your host is nearby, so if you have questions or require assistance during your stay, you can expect quick and professional service from the team to help ensure your stay is hassle-free from start to finish.\n" +
//                "\n" +
//                "Please be aware that not all pets are allowed, so please contact your host to discuss how to accommodate your pet.\n" +
//                "\n" +
//                "Cleaning service during your time in this Beach house is available for an extra charge. Please request this and your host will be happy to make the arrangements.\n" +
//                "\n" +
//                "PS: the apartment is not toddler-proof.\n" +
//                "\n" +
//                "If a baby cot is needed, please request it before the check-in.\n" +
//                "\n" +
//                "To ensure this property operates sustainably, and because of the recent rises in the price of diesel, power consumption in the property is limited to 40 kWh per day. This should be more than enough to supply all the power you need, the extra consumption will be paid by the guest at the same rate the host is paying to the estate.\n" );
//        House10.setNoOfBeds(5);
//        House10.setNoOfBathrooms(5);
//        House10.setNoOfBedrooms(5);
//        House10.setHouseType(HouseType.WHOLE_HOUSE);
//        House10.setPropertyType(PropertyType.BED_AND_BREAKFAST);
//        House10.setCleaningFee(80);
//
//
//
//        Pictures pictures46 = new Pictures("https://a0.muscache.com/im/pictures/3e327003-1cb2-4113-89eb-dfc83de633a9.jpg?im_w=1200 ");
//
//
//        Pictures pictures47 = new Pictures("https://a0.muscache.com/im/pictures/fd0aaf7a-2b3f-463a-9148-027724af9c8b.jpg?im_w=720 ");
//
//
//        Pictures pictures48 = new Pictures("https://a0.muscache.com/im/pictures/6acb596f-8106-48a7-994f-7ba7e70eaf89.jpg?im_w=720 ");
//
//        Pictures pictures49 = new Pictures("https://a0.muscache.com/im/pictures/23379e50-b3c9-42dc-9e78-6aba9c9a1449.jpg?im_w=720 ");
//
//        Pictures pictures50 = new Pictures("https://a0.muscache.com/im/pictures/f0e93743-59be-4a53-b4de-118103b44ff5.jpg?im_w=720 ");
//
//        House10.getPictures().addAll(new ArrayList<>(Arrays.asList(new Pictures[]{pictures46, pictures47, pictures48, pictures49, pictures50})));
//
//        Amenities h10 = new Amenities();
//        h10.setFire_extinguisher(true);
//        h10.setFireplace(true);
//        h10.setFirst_aid_kit(true);
//        h10.setPool_table(true);
//        h10.setBbq_grill(true);
//        h10.setFree_parking(true);
//        h10.setWasher(true);
//        h10.setWork_space(true);
//        h10.setPatio(true);
//        h10.setAir_condition(true);
//        h10.setTv(true);
//        House10.setAmenities(h10);
//
//        Comments comment21= new Comments("A magical location, incredible ocean views, friendly welcome",new Date(2023 - 1900, Calendar.MARCH, 25),walter,House10);
//        Comments comment22= new Comments("We had an amazing holiday . The villa was faultless . Location , size ,cleanliness . ",new Date(2023 - 1900, Calendar.MARCH, 25),olu,House10);
//
//
//
//        Apartments House11 = new Apartments();
//        House11.setAddress("Granadilla de Abona");
//        House11.setState("Canarias");
//        House11.setCountry("Spain");
//        House11.setName("Wood & Forest ");
//        House11.setHouseRefCode(House11.getCountry().substring(0, 3), 11);
//        House11.setPrice(445);
//        House11.setRating(3.9);
//        House11.setHomeState(HomeState.VERIFIED);
//        House11.setMaxNoOfGuests(4);
//        House11.setUsers(samy);
//        House11.setNoOfBeds(4);
//        House11.setDescription("Wake up in nature with the animals of the farm, you will be on the 1000m. high and just 15 minutes from Adeje and the coast. Enjoy nature (visiting ancestral Guanches routes, Trails, Galleries). A different sunset every day with views of La Gomera, La Palma, El Hierro and the mountains. Fresh eggs every morning from the chickens on our farm. An authentic experience of relaxation and tranquility away from the masses, the mobile coverage and the hustle and bustle of the city.\n" +
//                "The space\n" +
//                "A charming little wooden house. Equipped for everything you might need for a few days of relaxation.\n" +
//                "Climbing, hiking or paragliding are just some of the things that are waiting for you.\n" +
//                "Guest access\n" +
//                "Due to the Covid-19 situation we have taken a number of exceptional measures for everyone's safety. Upon arrival, all our guests will be offered a small gift set with disinfectant products as well as instructions during their stay.\n" +
//                "Guests have a cabin for their entire enjoyment as well as the entire delimited land.\n" +
//                "A living room with TV/DVD for media use as there is no TV signal. Board games, books, and movies. Fully equipped bathroom and kitchen.\n" +
//                "Wake up every morning with the rooster, collect fresh eggs for breakfast in the gardens surrounded by royal paths and trails that will be delightful of those who like tranquility and nature.\n" +
//                "Enough WIFI to be connected although we remind our guests that it is a slow connection.\n" +
//                "We wish you an unbeatable stay with our best wishes.\n" +
//                "Other things to note\n" +
//                "The cabin is located a few meters from two points of paragliding jumping, several trails and you can do many outdoor activities. The house offers everything you need to disconnect for a few days from the hustle and bustle of the city in complete relaxation.");
//        House11.setNoOfBathrooms(2);
//        House11.setNoOfBedrooms(2);
//        House11.setHouseType(HouseType.SHARED_ROOM);
//        House11.setPropertyType(PropertyType.TENT);
//        House11.setCleaningFee(70);
//
//
//        Pictures pictures51 = new Pictures("https://a0.muscache.com/im/pictures/ba8d2e6f-968a-4fde-a21b-c56bdd60556c.jpg?im_w=1200 ");
//
//        Pictures pictures52 = new Pictures("https://a0.muscache.com/im/pictures/2864374b-2833-43da-96b6-5b47c8dcd6fa.jpg?im_w=720 ");
//
//
//        Pictures pictures53 = new Pictures("https://a0.muscache.com/im/pictures/miso/Hosting-594589887674215403/original/b604bcfc-7251-4f5e-a40e-e4d97c6b840f.jpeg?im_w=720 ");
//
//
//        Pictures pictures54 = new Pictures("https://a0.muscache.com/im/pictures/177fdf77-8e87-422b-b1a1-dab439393c84.jpg?im_w=720 ");
//
//
//        Pictures pictures55 = new Pictures("https://a0.muscache.com/im/pictures/80da7330-2992-48c5-b6c6-dd3cf6797a8c.jpg?im_w=720 ");
//
//
//        House11.getPictures().addAll(new ArrayList<>(Arrays.asList(new Pictures[]{pictures51, pictures52, pictures53, pictures54, pictures55})));
//
//        Amenities h11 = new Amenities();
//        h11.setOutdoor_shower(true);
//        h11.setFireplace(true);
//        h11.setBeach_access(true);
//        h11.setFirst_aid_kit(true);
//        h11.setFire_extinguisher(true);
//        House11.setAmenities(h11);
//
//        Comments comment23= new Comments("Amazing villa, really clean and spacious",new Date(2023 - 1900, Calendar.MARCH, 25),isak,House11);
//        Comments comment24= new Comments("Top class holiday in excellent accommodation.",new Date(2023 - 1900, Calendar.MARCH, 25),Admin,House11);
//
//
//
//        Apartments House12 = new Apartments();
//        House12.setAddress("Sombrun");
//        House12.setState("Occitanie");
//        House12.setCountry("France");
//        House12.setName("Independent accommodation in the park of the castle");
//        House12.setHouseRefCode(House12.getCountry().substring(0, 3), 12);
//        House12.setPrice(962);
//        House12.setRating(5.0);
//        House12.setHomeState(HomeState.VERIFIED);
//        House12.setMaxNoOfGuests(10);
//        House12.setUsers(emily);
//        House12.setDescription("The castle of Sombrun ( XVII th) near Marciac is surrounded by a park of 6ha\n" +
//                "The fully independent rented apartment,with a surface of 140 m2, is located in the main courtyard of the castle and is divided into living/dining room Kitchenette equipped , 2 bedrooms, 1 children's room, 2 bathrooms, 2 separate toilets.The horse riders can possibly come with their horses.Possibility to accommodate 4 horses in boxes or paddocks ,for walks in the forest starting from the castle\n" +
//                "The space\n" +
//                "The castle is located 12 kms from Marciac where the famous International Jazz Festival takes place every year.\n" +
//                "Guests can enjoy a 15mx5m swimming pool and for riders leave the castle for magnificent walks accompanied or not by the owner .\n" +
//                "Guest access\n" +
//                "The home in the main courtyard is completely independent and fully reserved for our guests");
//        House12.setNoOfBeds(10);
//        House12.setNoOfBathrooms(11);
//        House12.setNoOfBedrooms(10);
//        House12.setHouseType(HouseType.WHOLE_HOUSE);
//        House12.setPropertyType(PropertyType.CASTLE);
//        House12.setCleaningFee(50);
//
//
//
//        Pictures pictures56 = new Pictures("https://a0.muscache.com/im/pictures/74192cff-0e84-43fc-ac8c-1c5a98039ca8.jpg?im_w=1200");
//
//
//        Pictures pictures57 = new Pictures("https://a0.muscache.com/im/pictures/8981847a-d41b-446f-8c71-99bc4eb77fba.jpg?im_w=720 ");
//
//
//        Pictures pictures58 = new Pictures("https://a0.muscache.com/im/pictures/2ecb5e87-d1bf-418f-a89e-5a090d92dfc6.jpg?im_w=720 ");
//
//
//        Pictures pictures59 = new Pictures("https://a0.muscache.com/im/pictures/a500265f-2414-47d1-b521-d99d126a53b3.jpg?im_w=720 ");
//
//
//        Pictures pictures60 = new Pictures("https://a0.muscache.com/im/pictures/db09b862-4a15-4f3b-94a9-814eadb1492f.jpg?im_w=720 ");
//
//        House12.getPictures().addAll(new ArrayList<>(Arrays.asList(new Pictures[]{pictures56, pictures57, pictures58, pictures59, pictures60})));
//
//        Amenities h12 = new Amenities();
//        h12.setFireplace(true);
//        h12.setPool(true);
//        h12.setFireplace(true);
//        h12.setKitchen(true);
//        h12.setFirst_aid_kit(true);
//        h12.setPool_table(true);
//        h12.setBbq_grill(true);
//        h12.setFire_extinguisher(true);
//        h12.setWasher(true);
//        h12.setWork_space(true);
//        h12.setPatio(true);
//        House12.setAmenities(h12);
//
//        Comments comment25= new Comments("We had a problem with wi fi and it was sorted very promptly. Would highly recommend this property",new Date(2022 - 1900, Calendar.DECEMBER, 15),melisa,House12);
//        Comments comment26= new Comments("The pool and patio area with BBQ are great for spending time outdoors in the lovely weather.",new Date(2022 - 1900, Calendar.DECEMBER, 25),emily,House12);
//
//
//        ArrayList<Apartments> houseCollection = new ArrayList<>(Arrays.asList(firstHouse, House2,
//                House3, House4, House5, House6, House7, House8, House9, House10, House11, House12));
//
//        ArrayList<Pictures> picturesCollection = new ArrayList<>(Arrays.asList(picture1, picture2, picture3, picture4,
//                picture5, picture6, picture7, picture8, picture9, picture10, picture11, picture12, picture13, picture14, picture15,
//                picture16, picture17, picture18, picture19, picture20, picture21, pictures22, pictures23, pictures24, pictures25, pictures26,
//                pictures27, pictures28, pictures29, pictures30, pictures31, pictures32, pictures33, pictures34, pictures35, pictures36, pictures37,
//                pictures38, pictures39, pictures40, pictures41, pictures42, pictures43, pictures44, pictures45, pictures46, pictures47, pictures48,
//                pictures49, pictures50, pictures51, pictures52, pictures53, pictures54, pictures55, pictures56, pictures57, pictures58, pictures59,
//                pictures60));
//
//
////        Admin.getApartmentsSet().addAll(new ArrayList<>(Arrays.asList(new Apartments[]{firstHouse, House2, House3, House4, House5
////                , House6, House7, House8, House9, House10, House11, House12})));
//
//        Reservation reservation = new Reservation();
//        reservation.setCheckInDate(new Date());//today's date(checkIn date)
//        reservation.setCheckOutDate(new Date(2023 - 1900, Calendar.MARCH, 25));//check out date
//        reservation.setReservationState(ReservationState.COMPLETED);
//
//        reservation.setUsers(Admin);
//        reservation.setApartment(firstHouse);
//
//
//        amenitiesRepository.saveAll(new ArrayList<>(Arrays.asList(A,h2,h3,h4,h5,h6,h7,h8,h9,h10
//        ,h11,h12)));
//        picturesRepository.saveAll(picturesCollection);
//        userRepository.saveAll(new ArrayList<>(Arrays.asList(walter,Admin,kathy,samy,melisa,emily,olu,chi,isak)));
//        apartmentRepo.saveAll(houseCollection);
//        commentRepository.saveAll(new ArrayList<>(Arrays.asList(comment1,comment2,comment3,comment4
//                ,comment5,comment6,comment7,comment8,comment9,comment10,comment11,comment12,comment13,comment14
//                ,comment15,comment16,comment17,comment18,comment19,comment20,comment21,comment22,comment23,comment24
//                ,comment25,comment26)));
//        reservationRepo.save(reservation);
//
//
//    }
//}

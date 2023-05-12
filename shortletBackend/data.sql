CREATE DATABASE  IF NOT EXISTS `shortlet` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `shortlet`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: shortlet
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `amenities`
--

DROP TABLE IF EXISTS `amenities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `amenities` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `air_condition` bit(1) NOT NULL,
  `bbq_grill` bit(1) NOT NULL,
  `beach_access` bit(1) NOT NULL,
  `fire_extinguisher` bit(1) NOT NULL,
  `fireplace` bit(1) NOT NULL,
  `first_aid_kit` bit(1) NOT NULL,
  `free_parking` bit(1) NOT NULL,
  `hot_tub` bit(1) NOT NULL,
  `kitchen` bit(1) NOT NULL,
  `lake_access` bit(1) NOT NULL,
  `outdoor_dining` bit(1) NOT NULL,
  `outdoor_shower` bit(1) NOT NULL,
  `paid_parking` bit(1) NOT NULL,
  `patio` bit(1) NOT NULL,
  `pool` bit(1) NOT NULL,
  `pool_table` bit(1) NOT NULL,
  `smoke_alarm` bit(1) NOT NULL,
  `tv` bit(1) NOT NULL,
  `washer` bit(1) NOT NULL,
  `wifi` bit(1) NOT NULL,
  `work_space` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amenities`
--

LOCK TABLES `amenities` WRITE;
/*!40000 ALTER TABLE `amenities` DISABLE KEYS */;
INSERT INTO `amenities` VALUES (1,_binary '',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(2,_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '',_binary '\0'),(3,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '',_binary '',_binary '\0'),(4,_binary '\0',_binary '',_binary '\0',_binary '',_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(5,_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '',_binary '',_binary '\0',_binary '\0'),(6,_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0'),(7,_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '',_binary ''),(8,_binary '\0',_binary '\0',_binary '',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary ''),(9,_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '',_binary '',_binary '',_binary '\0',_binary '',_binary '',_binary '\0'),(10,_binary '',_binary '',_binary '\0',_binary '',_binary '',_binary '',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '',_binary '\0',_binary '',_binary '',_binary '\0',_binary ''),(11,_binary '\0',_binary '\0',_binary '',_binary '',_binary '',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(12,_binary '\0',_binary '',_binary '\0',_binary '',_binary '',_binary '',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '',_binary '',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '');
/*!40000 ALTER TABLE `amenities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartments`
--

DROP TABLE IF EXISTS `apartments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `cleaning_fee` int NOT NULL,
  `continent` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `country` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `description` longtext COLLATE utf8mb3_unicode_ci,
  `home_state` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `house_ref_code` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `house_type` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `max_no_of_guests` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `no_of_bathrooms` int NOT NULL,
  `no_of_bedrooms` int NOT NULL,
  `no_of_beds` int NOT NULL,
  `price` int NOT NULL,
  `property_type` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `rating` double NOT NULL,
  `service_fee` int NOT NULL,
  `state` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `amenities_id` bigint DEFAULT NULL,
  `users_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr795hgq4yc8wev8nr1dm9ytbr` (`amenities_id`),
  KEY `FKscql5m2pfi58av6dbee52tqcy` (`users_id`),
  CONSTRAINT `FKr795hgq4yc8wev8nr1dm9ytbr` FOREIGN KEY (`amenities_id`) REFERENCES `amenities` (`id`),
  CONSTRAINT `FKscql5m2pfi58av6dbee52tqcy` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartments`
--

LOCK TABLES `apartments` WRITE;
/*!40000 ALTER TABLE `apartments` DISABLE KEYS */;
INSERT INTO `apartments` VALUES (1,'Leece',15,NULL,'Italy','My place is close to Apuan Park. We\'re 12 km from Castelnuovo di Garfagnana. You\'ll love my place because of its location and atmosphere. My place is good for couples, lonely adventurers, families (with kids), furry friends (pets), and groups of friends','VERIFIED','Ita1','WHOLE_HOUSE',5,'Saponea 13 Flat Design',2,3,3,533,'APARTMENT',4.7,50,'Puglia','UNOCCUPIED',1,1),(2,'Route de I\'Ourika',23,NULL,'Morocco','The space\nThe sunset room is furnished with a double bed and a separate sitting area with a sofa bed which can be made into a single bed. There is a storage space for luggage and clothing. The bathroom is equipped with a shower, a toilet and a washbasin. The room has a private terrace facing west with a table and chairs overlooking the mountains and river.\nGuest access\nThe guesthouse offers a dining room with airco and heating as well as a large lounge area where drinks and meals can be enjoyed. There are also numerous terraces offering seating areas and great views of the valley.','VERIFIED','Mor2','PRIVATE_ROOM',8,'Room with a sunset view in the Ourika Valley',3,4,4,1371,'GUESTHOUSE',3.9,50,'Marrakesh','UNOCCUPIED',2,2),(3,'Sant Ferriol',65,NULL,'Spain','A stone village house. We live upstairs.\nGuest rooms are on the ground floor and are independent (except for the entrance, which we also use). The living room has a small kitchen (induction, two fires), with microwave, boiler, toaster, fridge, coffee maker, spade, etc.\nThe room has seats, books, maps. It has a single bed (with a nest below).\nWe have a small garden for the guests. The sink is independent and new. Guest spaces are hygienically safe.\nThe space\nThe double bed measures 1\'50 cm. You don\'t have to go upstairs. There is only one (new) sink for guests. There is a separate entrance (for the room, with a single small grain), but if not, there is also a general entrance to share with us. We have a wired internet connection and also wifi. Our kitchen is small (a stove), also has microwaves and a small refrigerator. There are dishes, silverware, drops, roaster, kettle, coffee maker and everything you need.\nGuest access\nThe ground floor for guests is separate. It\'s just share the main entrance to the house. Guests can enter through a second separate entrance if they wish.\nOther things to note\nWe\'re a quiet family. We also thank our guests for their peace of mind. Avoid noisy holidays and respect the neighborhood.\n\nNo smoking in our house. Guest pets are also not allowed.\nLicense number\nHUTG-046620','VERIFIED','Spain3','PRIVATE_ROOM',3,'Village house with charm in Garrotxa (Girona)',2,1,1,838,'BED_AND_BREAKFAST',5,50,'La Garrotxa','UNOCCUPIED',3,4),(4,'Al Haouz',45,NULL,'Morocco','Our moroccan Manager, Moutaa, will make you live an unforgettable experience : feel like at home in Marrakech ! Airport transfer, table d\'hote, musical evening, guided tour, massages and treatments on-site, as many services at your disposal. Our delicious breakfasts are included ! Welcome to your home !\nThe space\nRiad Chamali is a guest house with pool, in the medina of Marrakech, ideal for romantic or family holiday.\n\nWe are located 15 minutes walk from the famous Jemaa El Fna square, in the quiet and traditional neighborhood of Berrima (Badii Palace, the old Jewish quarter, tinsmiths\' square, the Bahia Palace ...) near the door Bab Hmar, which gives quick access to the Golf Courses.\n\nOur Riad offers a perfect combination of traditional lifestyle and modern high standard facilities. The riad decoration is sleek, warm and feels natural with paintings and rugs made from local craftmen. The architecture is from the original house called \"Zelliges\" which gives the place a friendly, peaceful and out of time atmosphere.\n','VERIFIED','Mor4','PRIVATE_ROOM',2,'Ideal for a romantic getaway !',1,1,2,734,'BED_AND_BREAKFAST',4.5,50,'Marrakesh-Safi','UNOCCUPIED',4,5),(5,'Osaka',10,NULL,'Japan','A beautiful and centrally-located place with superior view of the sea, the Portara and the magical sunset colours. It has two bedrooms (one with double bed and one with single bed), fully equipped kitchen and it can accommodate up to 4 guests. Relax and enjoy your stay within walking distance from Naxos Town port and all the resort amenities. Parking available.\nLicense number\n00001306417','VERIFIED','Jap5','PRIVATE_ROOM',3,'Calm Home',2,2,2,1265,'TREEHOUSE',4.7,50,'Honshu','UNOCCUPIED',5,6),(6,'Chania',90,NULL,'Greece','The house has very bright spaces, lots of natural light, the decoration is very fresh and fun with some antique elements that give it a special touch.\nThe space\nThe sound of the waves of the sea floods every corner of the house. It\'s a unique place to disconnect from the outside world and connect with nature. Ideal for surfers due to location.','VERIFIED','Gre6','WHOLE_HOUSE',4,'Maik Beyer',2,3,4,923,'APARTMENT',5,50,'Crete','UNOCCUPIED',6,5),(7,'La Lajita',32,NULL,'Spain','Enjoy the best views of Fuerteventura with an incredible location, with the sea and a serene climate where you can enjoy an ideal stay.\n\nIt is an outdoor vacation apartment located a few meters from the beach in a typical fishing village, where you can taste the fish of the day.\nIt consists of two bedrooms, a living room with sofa bed and kitchenette, a bathroom and a balcony with spectacular ocean views.','VERIFIED','Spa7','WHOLE_HOUSE',2,'La Lajita Barca Beach Sunset',1,1,1,182,'TINY_HOME',4.9,50,'Canary Islands','UNOCCUPIED',7,9),(8,'Mogan',100,NULL,'Spain','In the Ripollès region, between rivers, valleys and mountains, the ancient Castle of Llaés (10th century) stands splendidly. A unique place, of exceptional beauty, where absolute tranquility reigns in the middle of an exuberant nature.\nThe Castle has been fully renovated for the comfort required by the facilities for rural tourism, with 8 rooms, 5 with a double bed, and 3 with two single beds. It has a living room, dining room, kitchen, 4 bathrooms, garden and terrace.\nThe space\nThe Llaés Castle is a project of reform and maintenance of this national cultural asset that, by force of spirit and the guests from all over the world who come to visit us, can continue its journey.\nGuest access\nCurrently the fortification retains the physiognomy of a castle, the homage tower, the parade ground, and the religious enclosure with a garden area, the small cemetery, and the church of San Bartolomé, in a marked Romanesque style.\nOther things to note\nAccess to the castle is not prepared for people with reduced mobility since the last 50m are on an ascending ramp and only accessible on foot.\n\nEvents and parties are not allowed without an application, budget request and express authorization are required.\nLicense number\nPG000900','VERIFIED','Spa8','WHOLE_HOUSE',8,'10th century medieval Castle',6,6,7,374,'CASTLE',5,50,'Gran Canaria','UNOCCUPIED',8,2),(9,'Lekki ',150,NULL,'Nigeria','Take it easy at this unique and tranquil getaway.','VERIFIED','Nig9','PRIVATE_ROOM',6,'Jacuzzi and heated floor in orchid road',4,6,6,380,'HOTEL',5,50,'Lagos','UNOCCUPIED',9,7),(10,'Ikoyi',80,NULL,'Nigeria','Relax, unwind and enjoy this stunning Mediterranean-inspired Duplex getaway on Victoria Island, spectacular lagoon view from all the large azure windows. It is fully equipped for short or long-term stays. The beach house is situated on the 12th Floor in an unbeatable touristic and business location, near beaches, the coolest clubs, shops, bars, and restaurants.\n\nThere is space for walking, jogging and playing tennis. Suitable for families, couples, individuals & business travelers.\nThe space\nA beach house in the city? That’s exactly the unique combination you get from this beautifully presented 12th floor Mediterranean-inspired duplex apartment on Victoria Island, Lagos.\n\nAs soon as you step inside, you’re sure to appreciate how much time and effort your host has spent in making this relaxing and inspiring getaway just right. Every piece of furniture in the property has been meticulously designed and crafted by your host, demonstrating their passion for interior design, and desire to make everyone who stays here feel special and welcome.\n\nThis home-from-home is ideal for friends or family looking for a short to long-term stay in Lagos for pleasure, or corporate travelers looking for easy access to the business district.\n\nThe large azure windows give every guest majestic views of the island lagoon on which the property is located. There are incredible views to be enjoyed from sunrise to sunset. And why not try to find the seashells and other beach treasures the owner’s young daughter has hidden around the property?\n\n1ST FLOOR:\nLounge & Dining Area\nThis bright and open area continues the white and blue theme of the interior, all designed and made by your host, remember! Take time to relax on the comfortable seating in the lounge area, maybe to watch the smart TV with DSTV cable channels available, plus access to the Netflix account.\n\nIf you’re visiting on business, the work desk and chair are sure to come in handy, so to the 30MBPS unlimited data fiber-optic WiFi. The dining table can also be used as a workspace, or to host any special occasion meals you might have planned to celebrate an important milestone for a friend or family member. Last but not least, there’s a wooden bar area, great for relaxing at, perhaps with a drink at the end of a busy day.\n\nKitchen\nYou’ll discover quality European-style appliances in here, including a kettle, toaster, sandwich toaster, blender, and Nespresso coffee machine, plus everything you need to prepare delicious meals and snacks. For your convenience, a serving hatch connects the kitchen to the dining area in the main lounge area.\n\nRestroom / Toilet\nThe first floor has a convenient WC with a washbasin\n\n2ND FLOOR\n2 Bedrooms\nBoth of the bedrooms are located on the upper floor. The first bedroom features a King size bed with luxury Egyptian cotton linen, as well as a brand-new gloriously comfortable mattress that guarantees perfect rest throughout your visit, day or night.\n\nThe second bedroom has a king-size bed that can be split into two single beds based on your needs. Please let your host know how you’d like these beds arranged before your stay.\n\nMain Bathroom\nYou can’t fail to feel reinvigorated and ready for whatever the new day has in store after freshening up under the rainfall shower. Your host will supply towels and a range of bathroom amenities, including soap, shampoo, conditioner, and body wash.\n\nSmall sitting room\nThere’s another break-out area up here with two host-made pallet couches, great for relaxing on. Maybe you’ll want to use this space to read one of the many books you’ll find in the apartment. As a special gift, your host invites you to take home any book you like as a souvenir of your stay.\n\nLaundry\nHandy if you’re planning a long-term stay, the washer and portable clothesline are sure to come in useful, and many nearby laundries are ready to pick up ur cloth.\n\nOUTSIDE SPACE\nStay here, and you will have your own balcony with space to sit outside and enjoy the view, perhaps with a drink. There is also a dedicated parking space you can use, and lots of free parking in the local area.\nGuest access\nYour host is delighted to make this apartment exclusively and entirely available to you for however long you make your reservation. The apartment is treasured by your host who has handmade the furniture inside. Ensuring you enjoy a relaxing time here is the top priority, so expect your host to react quickly and professionally to any questions you might have, ensuring your stay is hassle-free.\nOther things to note\n-Airport transfers directly to apartments are available for a small fee. Please contact us to arrange it.\n\n-Your host is nearby, so if you have questions or require assistance during your stay, you can expect quick and professional service from the team to help ensure your stay is hassle-free from start to finish.\n\nPlease be aware that not all pets are allowed, so please contact your host to discuss how to accommodate your pet.\n\nCleaning service during your time in this Beach house is available for an extra charge. Please request this and your host will be happy to make the arrangements.\n\nPS: the apartment is not toddler-proof.\n\nIf a baby cot is needed, please request it before the check-in.\n\nTo ensure this property operates sustainably, and because of the recent rises in the price of diesel, power consumption in the property is limited to 40 kWh per day. This should be more than enough to supply all the power you need, the extra consumption will be paid by the guest at the same rate the host is paying to the estate.\n','VERIFIED','Niger10','WHOLE_HOUSE',5,'Mediterranean Island Beach-House Oasis 2br in Vi',5,5,5,120,'BED_AND_BREAKFAST',3.9,50,'Lagos State','UNOCCUPIED',10,8),(11,'Granadilla de Abona',70,NULL,'Spain','Wake up in nature with the animals of the farm, you will be on the 1000m. high and just 15 minutes from Adeje and the coast. Enjoy nature (visiting ancestral Guanches routes, Trails, Galleries). A different sunset every day with views of La Gomera, La Palma, El Hierro and the mountains. Fresh eggs every morning from the chickens on our farm. An authentic experience of relaxation and tranquility away from the masses, the mobile coverage and the hustle and bustle of the city.\nThe space\nA charming little wooden house. Equipped for everything you might need for a few days of relaxation.\nClimbing, hiking or paragliding are just some of the things that are waiting for you.\nGuest access\nDue to the Covid-19 situation we have taken a number of exceptional measures for everyone\'s safety. Upon arrival, all our guests will be offered a small gift set with disinfectant products as well as instructions during their stay.\nGuests have a cabin for their entire enjoyment as well as the entire delimited land.\nA living room with TV/DVD for media use as there is no TV signal. Board games, books, and movies. Fully equipped bathroom and kitchen.\nWake up every morning with the rooster, collect fresh eggs for breakfast in the gardens surrounded by royal paths and trails that will be delightful of those who like tranquility and nature.\nEnough WIFI to be connected although we remind our guests that it is a slow connection.\nWe wish you an unbeatable stay with our best wishes.\nOther things to note\nThe cabin is located a few meters from two points of paragliding jumping, several trails and you can do many outdoor activities. The house offers everything you need to disconnect for a few days from the hustle and bustle of the city in complete relaxation.','VERIFIED','Spa11','SHARED_ROOM',4,'Wood & Forest ',2,2,4,445,'TENT',3.9,50,'Canarias','UNOCCUPIED',11,4),(12,'Sombrun',50,NULL,'France','The castle of Sombrun ( XVII th) near Marciac is surrounded by a park of 6ha\nThe fully independent rented apartment,with a surface of 140 m2, is located in the main courtyard of the castle and is divided into living/dining room Kitchenette equipped , 2 bedrooms, 1 children\'s room, 2 bathrooms, 2 separate toilets.The horse riders can possibly come with their horses.Possibility to accommodate 4 horses in boxes or paddocks ,for walks in the forest starting from the castle\nThe space\nThe castle is located 12 kms from Marciac where the famous International Jazz Festival takes place every year.\nGuests can enjoy a 15mx5m swimming pool and for riders leave the castle for magnificent walks accompanied or not by the owner .\nGuest access\nThe home in the main courtyard is completely independent and fully reserved for our guests','VERIFIED','Fra12','WHOLE_HOUSE',10,'Independent accommodation in the park of the castle',11,10,10,962,'CASTLE',5,50,'Occitanie','UNOCCUPIED',12,6);
/*!40000 ALTER TABLE `apartments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartments_pictures`
--

DROP TABLE IF EXISTS `apartments_pictures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartments_pictures` (
  `apartments_id` bigint NOT NULL,
  `pictures_id` bigint NOT NULL,
  PRIMARY KEY (`apartments_id`,`pictures_id`),
  UNIQUE KEY `UK_41uw60oibih9f1t49douy50yv` (`pictures_id`),
  CONSTRAINT `FKjvkis8s4cwb7rsjj9dyjgp5go` FOREIGN KEY (`pictures_id`) REFERENCES `pictures` (`id`),
  CONSTRAINT `FKofafpj4sbnrc27mh4ytwxdnu0` FOREIGN KEY (`apartments_id`) REFERENCES `apartments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartments_pictures`
--

LOCK TABLES `apartments_pictures` WRITE;
/*!40000 ALTER TABLE `apartments_pictures` DISABLE KEYS */;
INSERT INTO `apartments_pictures` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(2,6),(2,7),(2,8),(2,9),(2,10),(3,11),(3,12),(3,13),(3,14),(3,15),(4,16),(4,17),(4,18),(4,19),(4,20),(5,21),(5,22),(5,23),(5,24),(5,25),(6,26),(6,27),(6,28),(6,29),(6,30),(7,31),(7,32),(7,33),(7,34),(7,35),(8,36),(8,37),(8,38),(8,39),(8,40),(9,41),(9,42),(9,43),(9,44),(9,45),(10,46),(10,47),(10,48),(10,49),(10,50),(11,51),(11,52),(11,53),(11,54),(11,55),(12,56),(12,57),(12,58),(12,59),(12,60);
/*!40000 ALTER TABLE `apartments_pictures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comment` longtext COLLATE utf8mb3_unicode_ci,
  `comment_date` date DEFAULT NULL,
  `apartments_id` bigint DEFAULT NULL,
  `users_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3glbruu7kxoit58wjl8a6r8ru` (`apartments_id`),
  KEY `FKt55y2infwbbw3o942o2mhm0v4` (`users_id`),
  CONSTRAINT `FK3glbruu7kxoit58wjl8a6r8ru` FOREIGN KEY (`apartments_id`) REFERENCES `apartments` (`id`),
  CONSTRAINT `FKt55y2infwbbw3o942o2mhm0v4` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,'We were fortunate to get a late check out on the last day which made a huge differnce to us. Good communication from the host.\nWould definitely recommend and is on my list for a return visit','2023-02-25',1,3),(2,'We had an amazing holiday . The villa was faultless . Location , size ,cleanliness . I will definitely be back again soon.','2023-03-05',1,6),(3,' Very spacious, very clean fantastic location host was always prompt in a response if you had any questions. ','2023-03-25',2,7),(4,'Great apartment - good size, great location, close to all the bars and restaurants, but set back enough so it isn’t noisy at all','2023-03-05',2,4),(5,'Great villa brilliant location and fabulous pool,will definitely be back again ','2023-03-15',2,5),(6,' All facilities in the villa are excellent, full kitchen, WiFi, TV with Netflix etc. and washing machine too.','2022-12-25',3,8),(7,'Thank you so much for letting us stay in this beautiful location','2023-01-25',3,2),(8,'We found a home far away from home. Cosy apartment with a breathtaking view!','2023-03-25',3,1),(9,'Very nice stay! Very cozy and amazing view of the ocean!\nToo bad we couldn\'t stay longer','2023-02-25',4,3),(10,'We loved it','2023-03-05',4,9),(11,'100% TOP experience','2023-03-02',5,5),(12,'We had a wonderful time and would love to come back. Thanks!','2023-03-04',5,8),(13,'A house with extraordinary views! We had a great time at this very well decorated and comfortably furnished house','2023-03-20',6,9),(14,'The apartment is spacious and equipped with every comfort. The view over the ocean is amazing!','2023-03-15',6,2),(15,' The view is unforgettable.','2023-01-15',7,3),(16,'Just unique and amazing place to stay. ','2023-01-20',7,1),(17,'This is perfect for you if you wish to get away from the tourist crowds','2023-03-25',8,4),(18,'We had a spectacular time, 100% recommended, excellent service and attention, I\'m still in love','2022-11-25',8,5),(19,'Incredible accommodation.','2022-12-25',9,7),(20,'This house is absolutely amazing! An unforgettable view:)','2022-12-20',9,3),(21,'A magical location, incredible ocean views, friendly welcome','2023-03-25',10,1),(22,'We had an amazing holiday . The villa was faultless . Location , size ,cleanliness . ','2023-03-25',10,7),(23,'Amazing villa, really clean and spacious','2023-03-25',11,9),(24,'Top class holiday in excellent accommodation.','2023-03-25',11,2),(25,'We had a problem with wi fi and it was sorted very promptly. Would highly recommend this property','2022-12-15',12,5),(26,'The pool and patio area with BBQ are great for spending time outdoors in the lovely weather.','2022-12-25',12,6);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pictures`
--

DROP TABLE IF EXISTS `pictures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pictures` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `url` longtext COLLATE utf8mb3_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pictures`
--

LOCK TABLES `pictures` WRITE;
/*!40000 ALTER TABLE `pictures` DISABLE KEYS */;
INSERT INTO `pictures` VALUES (1,'https://a0.muscache.com/im/pictures/83738e60-4654-4faa-af1a-f53d02acbe6c.jpg?im_w=1200 '),(2,'https://a0.muscache.com/im/pictures/69faa681-fb85-4817-86f6-9d727c3fe9d2.jpg?im_w=720'),(3,'https://a0.muscache.com/im/pictures/miso/Hosting-43244736/original/a7d388b9-c419-4d1f-8566-26590a847300.jpeg?im_w=1200 '),(4,'https://a0.muscache.com/im/pictures/f4df0d13-ab67-4ca7-bfb8-946b2dc3aa00.jpg?im_w=1200 '),(5,'https://a0.muscache.com/im/pictures/b2fa90f6-1cac-4013-a40b-7653cf4872dd.jpg?im_w=720'),(6,'https://a0.muscache.com/im/pictures/6a9cae57-dfe1-4ea6-a570-84856e20d410.jpg?im_w=720'),(7,'https://a0.muscache.com/im/pictures/52737d90-1113-42c9-8787-0911ed7444dc.jpg?im_w=1200'),(8,'https://a0.muscache.com/im/pictures/babc53a6-6934-463c-b8f7-85d45692fa76.jpg?im_w=720'),(9,'https://a0.muscache.com/im/pictures/f7016064-1a7b-4be7-b9bd-a938d3f59b07.jpg?im_w=1200 '),(10,'https://a0.muscache.com/im/pictures/2a3f591a-a8b3-4ef9-b1bf-d988b2c3bff5.jpg?im_w=1200 '),(11,'https://a0.muscache.com/im/pictures/6e75f583-5c1f-41e8-b705-511dbffe92b5.jpg?im_w=1200 '),(12,'https://a0.muscache.com/im/pictures/miso/Hosting-48307356/original/8dd3d5c3-968b-414a-af09-9fdbf33212f7.jpeg?im_w=720 '),(13,'https://a0.muscache.com/im/pictures/2fc8c4b9-74ff-4a40-8fe7-acad214834e7.jpg?im_w=720 '),(14,'https://a0.muscache.com/im/pictures/4626d93b-e838-47c6-8b9b-01eee3e199e4.jpg?im_w=720 '),(15,'https://a0.muscache.com/im/pictures/miso/Hosting-48307356/original/7fd97148-3723-48d8-b2ca-b37dfe275ffd.jpeg?im_w=720 '),(16,'https://a0.muscache.com/im/pictures/11755737/cbefb5d6_original.jpg?im_w=1200 '),(17,'https://a0.muscache.com/im/pictures/11755725/f3d5fd5d_original.jpg?im_w=720 '),(18,'https://a0.muscache.com/im/pictures/11755759/3bfdf199_original.jpg?im_w=720 '),(19,'https://a0.muscache.com/im/pictures/11755763/53c0aaa9_original.jpg?im_w=720 '),(20,'https://a0.muscache.com/im/pictures/890deadc-040e-43b7-ad5a-2391ab90e314.jpg?im_w=720 '),(21,'https://a0.muscache.com/im/pictures/32233013/785da15f_original.jpg?im_w=1200 '),(22,'https://a0.muscache.com/im/pictures/32233110/70706ded_original.jpg?im_w=720 '),(23,'https://a0.muscache.com/im/pictures/32233356/fe6bbadd_original.jpg?im_w=720 '),(24,'https://a0.muscache.com/im/pictures/32233280/b60c45d8_original.jpg?im_w=720'),(25,'https://a0.muscache.com/im/pictures/658f0839-9f2a-4bd5-a878-b25b5ef284fc.jpg?im_w=720 '),(26,'https://a0.muscache.com/im/pictures/prohost-api/Hosting-2631155/original/e5f83d80-6e85-4cdc-99b4-ae9cc89e7020.jpeg?im_w=1200 '),(27,'https://a0.muscache.com/im/pictures/prohost-api/Hosting-2631155/original/cf757cd5-7375-4c33-9b58-55a91b512567.jpeg?im_w=720 '),(28,'https://a0.muscache.com/im/pictures/prohost-api/Hosting-2631155/original/70cbf62d-e9cf-4c0e-ae2f-c3029f4cb94d.jpeg?im_w=720 '),(29,'https://a0.muscache.com/im/pictures/prohost-api/Hosting-2631155/original/a164f978-39f4-484d-b544-e1da91b9d470.jpeg?im_w=720 '),(30,'https://a0.muscache.com/im/pictures/prohost-api/Hosting-2631155/original/c19722b5-e048-4778-b4d3-4576c62e0b70.jpeg?im_w=720 '),(31,'https://a0.muscache.com/im/pictures/miso/Hosting-50058763/original/2f6f2b10-447e-4486-adeb-9420341cb8ab.jpeg?im_w=720 '),(32,'https://a0.muscache.com/im/pictures/miso/Hosting-50058763/original/9ffcb84a-f773-4a03-8e78-7b42bda45107.jpeg?im_w=720 '),(33,'https://a0.muscache.com/im/pictures/miso/Hosting-49937547/original/0109bbe0-90ca-4645-8112-99b4544a372b.jpeg?im_w=1200 '),(34,'https://a0.muscache.com/im/pictures/miso/Hosting-50058763/original/8174450f-3d6b-4090-95f2-bc6582063c49.jpeg?im_w=720 '),(35,'https://a0.muscache.com/im/pictures/miso/Hosting-50058763/original/105f5096-1885-498e-88e3-5c7805700227.jpeg?im_w=720 '),(36,'https://a0.muscache.com/im/pictures/miso/Hosting-842511498702667444/original/ca793819-9385-412e-87ec-a0562b674f42.jpeg?im_w=720'),(37,'https://a0.muscache.com/im/pictures/b0e551a4-fd58-44ec-9be7-10f53da910ea.jpg?im_w=720 '),(38,'https://a0.muscache.com/im/pictures/2543a9bc-0f49-40c6-acf9-427367f1f5fe.jpg?im_w=720 '),(39,'https://a0.muscache.com/im/pictures/e995e3b0-c9cd-4ffb-aad4-0f76f4e00a0a.jpg?im_w=720 '),(40,'https://a0.muscache.com/im/pictures/05f93fe3-da99-485d-aada-f8224d714d97.jpg?im_w=720 '),(41,'https://a0.muscache.com/im/pictures/miso/Hosting-822280454813797636/original/28910836-2cbf-4793-ad56-9068d8414ebb.jpeg?im_w=720'),(42,'https://a0.muscache.com/im/pictures/miso/Hosting-51327545/original/286568cd-c8aa-4665-a73d-ec6cd6884da6.jpeg?im_w=720 '),(43,'https://a0.muscache.com/im/pictures/miso/Hosting-51327545/original/c0cae2de-aaf1-4180-920d-cde5c0d9bb92.jpeg?im_w=720 '),(44,'https://a0.muscache.com/im/pictures/miso/Hosting-51327545/original/1975c982-9712-4fcd-8380-ff1ca1207f8c.jpeg?im_w=720 '),(45,'https://a0.muscache.com/im/pictures/miso/Hosting-51327545/original/430eaa3d-9a8a-45f9-b652-bb110ec0dd0b.jpeg?im_w=720 '),(46,'https://a0.muscache.com/im/pictures/3e327003-1cb2-4113-89eb-dfc83de633a9.jpg?im_w=1200 '),(47,'https://a0.muscache.com/im/pictures/fd0aaf7a-2b3f-463a-9148-027724af9c8b.jpg?im_w=720 '),(48,'https://a0.muscache.com/im/pictures/6acb596f-8106-48a7-994f-7ba7e70eaf89.jpg?im_w=720 '),(49,'https://a0.muscache.com/im/pictures/23379e50-b3c9-42dc-9e78-6aba9c9a1449.jpg?im_w=720 '),(50,'https://a0.muscache.com/im/pictures/f0e93743-59be-4a53-b4de-118103b44ff5.jpg?im_w=720 '),(51,'https://a0.muscache.com/im/pictures/ba8d2e6f-968a-4fde-a21b-c56bdd60556c.jpg?im_w=1200 '),(52,'https://a0.muscache.com/im/pictures/2864374b-2833-43da-96b6-5b47c8dcd6fa.jpg?im_w=720 '),(53,'https://a0.muscache.com/im/pictures/miso/Hosting-594589887674215403/original/b604bcfc-7251-4f5e-a40e-e4d97c6b840f.jpeg?im_w=720 '),(54,'https://a0.muscache.com/im/pictures/177fdf77-8e87-422b-b1a1-dab439393c84.jpg?im_w=720 '),(55,'https://a0.muscache.com/im/pictures/80da7330-2992-48c5-b6c6-dd3cf6797a8c.jpg?im_w=720 '),(56,'https://a0.muscache.com/im/pictures/74192cff-0e84-43fc-ac8c-1c5a98039ca8.jpg?im_w=1200'),(57,'https://a0.muscache.com/im/pictures/8981847a-d41b-446f-8c71-99bc4eb77fba.jpg?im_w=720 '),(58,'https://a0.muscache.com/im/pictures/2ecb5e87-d1bf-418f-a89e-5a090d92dfc6.jpg?im_w=720 '),(59,'https://a0.muscache.com/im/pictures/a500265f-2414-47d1-b521-d99d126a53b3.jpg?im_w=720 '),(60,'https://a0.muscache.com/im/pictures/db09b862-4a15-4f3b-94a9-814eadb1492f.jpg?im_w=720 ');
/*!40000 ALTER TABLE `pictures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `check_in_date` date DEFAULT NULL,
  `check_out_date` date DEFAULT NULL,
  `price` int NOT NULL,
  `reservation_state` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `apartment_id` bigint DEFAULT NULL,
  `users_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKivm4eucnmrfspa4s2e4j5x0th` (`apartment_id`),
  KEY `FK15w8lrbqikmtjwas4jalyrawq` (`users_id`),
  CONSTRAINT `FK15w8lrbqikmtjwas4jalyrawq` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKivm4eucnmrfspa4s2e4j5x0th` FOREIGN KEY (`apartment_id`) REFERENCES `apartments` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (1,'2023-05-08','2023-03-25',0,'COMPLETED',1,2);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `review` double NOT NULL,
  `apartments_id` bigint DEFAULT NULL,
  `users_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo7hosrw55j37pft1llrsyxl2h` (`apartments_id`),
  KEY `FKg6eq2th3bvlqun9ri060j8hak` (`users_id`),
  CONSTRAINT `FKg6eq2th3bvlqun9ri060j8hak` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKo7hosrw55j37pft1llrsyxl2h` FOREIGN KEY (`apartments_id`) REFERENCES `apartments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `phone_no` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `picture` longtext COLLATE utf8mb3_unicode_ci,
  `role` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'walter@gmail.com','Kristi J. Walters',NULL,NULL,'USER'),(2,'peter@gmail.com','Peter',NULL,NULL,'ADMIN'),(3,'kathy@gmail.com','Kathleen Kammer',NULL,NULL,'USER'),(4,'sami@gmail.com','Samuel Altieri',NULL,NULL,'USER'),(5,'mari@gmail.com','Melissa Marino',NULL,NULL,'USER'),(6,'emily@gmail.com','Emily Hannah',NULL,NULL,'USER'),(7,'olu@gmail.com','Oluchukwu Chiwetelu',NULL,NULL,'USER'),(8,'chi@gmail.com','Chiabuotu Chinomso',NULL,NULL,'USER'),(9,'isak@gmail.com','Isak Lennert',NULL,NULL,'USER'),(10,'peterladenika@gmail.com','Ladenika Peter',NULL,NULL,'ADMIN'),(11,'abdulai1398@student.babcock.edu.ng','Mogena',NULL,NULL,'ADMIN');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-08 12:18:53

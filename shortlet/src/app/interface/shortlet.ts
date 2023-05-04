export interface Shortlet {
  address: string;
  houseRefCode: number;
  id: number;
  description: string;
  maxNoOfGuests: number;
  noOfBedrooms: number;
  noOfBathrooms: number;
  noOfBeds: number;
  name: string;
  pictures: Pictures[];
  price: number;
  rating: number;
  users: Users;
  reservations: [];
  comments: [];
  amenities: [];
  serviceFee: number;
  cleaningFee: number;
}

export interface SignedinUser {
  role: string;
}

export interface ApartmentObj {
  address: string;
  country: string;
  id: number;
  name: string;
  state: string;
  pictures: Array<Pictures>;
}

export interface ReservationObj {
  apartment: ApartmentObj;
  checkInDate: string;
  checkOutDate: string;
  id: number;
  price: number;
}

export interface Pictures {
  id: number;
  url: string;
}

export interface Users {
  id: number;
  name: string;
  phoneNo?: number;
  email: string;
  picture?: string;
}

export interface NewShortlet {
  name: string;
  phoneNo?: number;
  title: string;
  price: number;
  description: string;
  address: string;
  maxNoOfGuests: number;
  noOfBathrooms: number;
  noOfBedrooms: number;
  propertyType: string;
  houseType: string;
  amenities?: any;
  // photos: File
}

// export interface Listings {
//   name: string,
//   status: string,
//   noOfBedrooms: number,
//   noOfBathrooms: number,
//   country: string
// }

export interface Listings {
  id: number;
  name: string;
  noOfBedrooms: number;
  noOfBathrooms: number;
  noOfBeds: number;
  address: string;
  state: string;
  country: string;
  pictures: Pictures;
}

export interface amenities {
  air_condition: boolean;
  bbq_grill: boolean;
  fireplace: boolean;
  first_aid_kit: true;
  patio: boolean;
  pool: boolean;
  washer: boolean;
  wifi: boolean;
  work_space: boolean;
}

export interface UserData {
  apartmentPicture: string;
  apartmentId: number;
  apartmentName: string;
  apartmentCountry: string;
  apartmentState: string;
  checkInDate: Date;
  checkOutDate: Date;
  price: number;
  id: number;
}
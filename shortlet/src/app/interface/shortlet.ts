export interface Shortlet {
    address: string,
    houseRefCode: number,
    id: number,
    maxNoOfGuests: number,
    name: string,
    pictures: Pictures[], 
    price: number,
    rating:number,
    users:  Users 
}

export interface Pictures {
    id: number,
    url: string
}

export interface Users {
    id: number,
    name: string, 
    phoneNo?: number, 
    email: string,
    picture?: string
}

export interface NewShortlet {
    name: string
    phoneNo?: number, 
    title: string,
    price: number,
    description: string,
    address: string
    guest: number
    photos: File
}
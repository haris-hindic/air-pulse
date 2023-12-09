export class AirportResponse {
    id!: number;
    city!: string;
    country!: string;
    created!: Date;
    createdBy!: string;
    iataCode!: string;
    icaoCode!: string;
    modified!: Date;
    modifiedBy!: string;
    name!: string;
    status!: string;
}

export class AirportRequest {
    city!: string;
    country!: string;
    iataCode!: string;
    icaoCode!: string;
    name!: string;
    status!: string;
}
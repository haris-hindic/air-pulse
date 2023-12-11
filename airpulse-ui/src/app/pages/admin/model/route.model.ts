export class RouteResponse {
    id!: number;
    aircraftId!: number;
    aircraftDetails!: string;
    arrivalAirportId!: number;
    arrivalAirportDetails!: string;
    arrivalAirportCity!: string;
    created!: Date;
    createdBy!: string;
    departureAirportId!: number;
    departureAirportDetails!: string;
    departureAirportCity!: string;
    distance!: number;
    duration!: number;
    modified!: Date;
    modifiedBy!: string;
    note!: string;
    status!: string;
}

export class RouteRequest {
    aircraftId!: number;
    arrivalAirportId!: number;
    departureAirportId!: number;
    distance!: number;
    duration!: number;
    note!: string;
    status!: string;
}
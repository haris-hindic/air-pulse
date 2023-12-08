import { RouteResponse } from "./route.model";

export class FlightResponse {
    id!: number;
    arrival!: Date;
    departure!: Date;
    basePrice!: number;
    created!: Date;
    createdBy!: string;
    modified!: Date;
    modifiedBy!: string;
    status!: string;
    routeId!: number;
    route!: RouteResponse;
}

export class FlightRequest {
    arrival!: Date;
    basePrice!: number;
    departure!: Date;
    routeId!: number;
}
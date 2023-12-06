export class AircraftseatResponse {
    id!: number;
    aircraftId!: number;
    seatClass!: string;
    created!: Date;
    createdBy!: string;
    modified!: Date;
    modifiedBy!: string;
    priceModifier!: number;
    quantity!: number;
    status!: string;
}

export class AircraftseatRequest {
    aircraftId!: number;
    seatClass!: string;
    priceModifier!: number;
    quantity!: number;
}


export class AircraftResponse {
    id!: number;
    averageSpeed!: number;
    buildDate!: Date;
    created!: Date;
    createdBy!: string;
    manufacturer!: string;
    model!: string;
    modified!: Date;
    modifiedBy!: string;
    status!: string;
    image!: string;
}

export class AircraftRequest {
    manufacturer!: string;
    model!: string;
    buildDate!: Date;
    averageSpeed!: number;
    status!: string;
    imageData!: string;

    public static createFromResponse(aircraft: AircraftResponse) {
        const ac = new AircraftRequest();

        ac.averageSpeed = aircraft.averageSpeed;
        ac.model = aircraft.model;
        ac.manufacturer = aircraft.manufacturer;
        ac.buildDate = aircraft.buildDate;
        ac.status = aircraft.status;

        return ac;
    }
}
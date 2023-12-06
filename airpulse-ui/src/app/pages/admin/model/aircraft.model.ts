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
}

export class AircraftRequest {
    manufacturer!: string;
    model!: string;
    buildDate!: Date;
    averageSpeed!: number;
    status!: string;
}
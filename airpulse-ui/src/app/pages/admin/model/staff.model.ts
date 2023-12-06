export class StaffResponse {
    id!: number;
    aircraftId!: number;
    created!: Date;
    createdBy!: string;
    employeeId!: number;
    modified!: Date;
    modifiedBy!: string;
    status!: string;
    validFrom!: Date;
    validTo!: Date;
    summary!: string;
}

export class StaffRequest {
    aircraftId!: number;
    employeeId!: number;
    validFrom!: Date;
    validTo!: Date;
    summary!: string;
    status!: string;
}
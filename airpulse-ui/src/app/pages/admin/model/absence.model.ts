export class AbsenceResponse {
    id!: number;
    comments!: string;
    employeeId!: number;
    employeeFullName!: string;
    endDate!: Date;
    reason!: string;
    startDate!: Date;
    type!: string;
    modified!: Date;
    modifiedBy!: string;
    created!: Date;
    createdBy!: string;
    status!: string;
}

export class AbsenceRequest {
    comments!: string;
    employeeId!: number;
    endDate!: Date;
    reason!: string;
    startDate!: Date;
    type!: string;
    status!: string;
}
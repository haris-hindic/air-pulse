export class PositionResponse {
    id!: number;
    created!: Date;
    createdBy!: string;
    employeeId!: number;
    employeeFullName!: string;
    endDate!: Date;
    jobTypeId!: number;
    title!: string;
    modified!: Date;
    modifiedBy!: string;
    salary!: number;
    startDate!: Date;
    status!: string;
}

export class PositionRequest {
    employeeId!: number;
    endDate!: Date;
    jobTypeId!: number;
    salary!: number;
    startDate!: Date;
}
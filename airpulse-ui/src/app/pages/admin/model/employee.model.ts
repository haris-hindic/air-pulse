export class EmployeeResponse {
    id!: number;
    created!: Date;
    createdBy!: string;
    dateOfBirth!: Date;
    email!: string;
    firstName!: string;
    gender!: string;
    lastName!: string;
    modified!: Date;
    modifiedBy!: string;
    phoneNumber!: string;
    status!: string;
}

export class EmployeeRequest {
    dateOfBirth!: Date;
    email!: string;
    firstName!: string;
    gender!: string;
    lastName!: string;
    phoneNumber!: string;
}
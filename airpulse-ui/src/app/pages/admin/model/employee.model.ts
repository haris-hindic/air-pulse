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
    image!: string;
}

export class EmployeeRequest {
    dateOfBirth!: Date;
    email!: string;
    firstName!: string;
    gender!: string;
    lastName!: string;
    phoneNumber!: string;
    imageData!: string;

    public static createFromResponse(employeeResponse: EmployeeResponse) {
        const emp = new EmployeeRequest();

        emp.dateOfBirth = employeeResponse.dateOfBirth;
        emp.email = employeeResponse.email;
        emp.firstName = employeeResponse.firstName;
        emp.gender = employeeResponse.gender;
        emp.lastName = employeeResponse.lastName;
        emp.phoneNumber = employeeResponse.phoneNumber;

        return emp;
    }
}
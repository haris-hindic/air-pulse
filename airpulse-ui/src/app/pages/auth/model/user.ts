export class UserResponse {
  id!: number;
  created!: Date;
  createdBy!: string;
  dateOfBirth!: Date;
  email!: string;
  firstName!: string;
  lastName!: string;
  username!: string;
  modified!: Date;
  modifiedBy!: string;
  phoneNumber!: string;
  status!: string;
}

export class UserRequest {
  private dateOfBirth!: Date;
  private email!: string;
  private firstName!: string;
  private username!: string;
  private password!: string;
  private lastName!: string;
  private phoneNumber!: string;
}

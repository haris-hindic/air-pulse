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
  image!: string;
}

export class UserRequest {
  dateOfBirth!: Date;
  email!: string;
  firstName!: string;
  username!: string;
  password!: string;
  lastName!: string;
  phoneNumber!: string;
  imageData!: string;

  public static createFromResponse(response: UserResponse) {
    const request = new UserRequest();

    request.dateOfBirth = response.dateOfBirth;
    request.email = response.email;
    request.firstName = response.firstName;
    request.lastName = response.lastName;
    request.username = response.username;

    return request;
  }
}

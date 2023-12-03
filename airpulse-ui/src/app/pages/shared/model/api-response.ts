export class ApiResponse<T>{
  code!: number;
  status!: string;
  data!: T;
}

export class ApiListResponse<T>{
  code!: number;
  status!: string;
  data!: T[];
}
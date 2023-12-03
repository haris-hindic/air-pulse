export class JobTypeResponse {
  id!: number;
  created!: Date;
  createdBy!: string;
  modified!: Date;
  modifiedBy!: string;
  responsibilities!: string;
  salaryMax!: number;
  salaryMin!: number;
  status!: string;
  title!: string;
}

export class JobTypeRequest {
  responsibilities!: string;
  salaryMin!: number;
  salaryMax!: number;
  title!: string;
  status!: string;
}
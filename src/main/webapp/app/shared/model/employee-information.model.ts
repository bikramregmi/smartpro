export interface IEmployeeInformation {
  id?: number;
  dob?: string;
  gender?: string;
  isMarried?: boolean;
  bloodGroup?: string;
  parentName?: string;
  addressline1?: string;
  addressline2?: string;
  phoneNumber?: string;
  mobileNumber?: string;
  emergencyContactNumber?: string;
  joiningDate?: string;
  designation?: string;
  panNumber?: string;
  documentNumber?: string;
  extraField?: string;
}

export class EmployeeInformation implements IEmployeeInformation {
  constructor(
    public id?: number,
    public dob?: string,
    public gender?: string,
    public isMarried?: boolean,
    public bloodGroup?: string,
    public parentName?: string,
    public addressline1?: string,
    public addressline2?: string,
    public phoneNumber?: string,
    public mobileNumber?: string,
    public emergencyContactNumber?: string,
    public joiningDate?: string,
    public designation?: string,
    public panNumber?: string,
    public documentNumber?: string,
    public extraField?: string
  ) {
    this.isMarried = this.isMarried || false;
  }
}

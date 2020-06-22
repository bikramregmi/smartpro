export interface IEmployee {
  id?: number;
  fullName?: string;
  email?: string;
  eCode?: string;
  isActive?: boolean;
  employeeSalaryFullName?: string;
  employeeSalaryId?: number;
  employeeInformationId?: number;
}

export class Employee implements IEmployee {
  constructor(
    public id?: number,
    public fullName?: string,
    public email?: string,
    public eCode?: string,
    public isActive?: boolean,
    public employeeSalaryFullName?: string,
    public employeeSalaryId?: number,
    public employeeInformationId?: number
  ) {
    this.isActive = this.isActive || false;
  }
}

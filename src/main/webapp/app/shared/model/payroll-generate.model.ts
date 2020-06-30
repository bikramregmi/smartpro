export interface IPayrollGenerate {
  id?: number;
  salaryMonth?: string;
  description?: string;
  tax?: string;
  pf?: string;
  employeeId?: number;
  employeeSalaryId?: number;
}

export class PayrollGenerate implements IPayrollGenerate {
  constructor(
    public id?: number,
    public salaryMonth?: string,
    public description?: string,
    public tax?: string,
    public pf?: string,
    public employeeId?: number,
    public employeeSalaryId?: number
  ) {}
}

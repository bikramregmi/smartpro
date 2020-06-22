export interface IEmployeeSalary {
  id?: number;
  basicSalary?: string;
  allowance?: string;
  ot?: string;
  bonus?: string;
  description?: string;
  tax?: string;
  pf?: string;
  extra?: string;
}

export class EmployeeSalary implements IEmployeeSalary {
  constructor(
    public id?: number,
    public basicSalary?: string,
    public allowance?: string,
    public ot?: string,
    public bonus?: string,
    public description?: string,
    public tax?: string,
    public pf?: string,
    public extra?: string
  ) {}
}

export interface ICompany {
  id?: number;
  companyName?: string;
  address?: string;
  email?: string;
  fy?: Date;
  bookDate?: Date;
  currencyString?: string;
  currencySymbol?: string;
  currencySubString?: string;
  dealerType?: string;
  taxRate?: number;
  type?: string;
  extraField?: string;
  extrafield1?: string;
}

export class Company implements ICompany {
  constructor(
    public id?: number,
    public companyName?: string,
    public address?: string,
    public email?: string,
    public fy?: Date,
    public bookDate?: Date,
    public currencyString?: string,
    public currencySymbol?: string,
    public currencySubString?: string,
    public dealerType?: string,
    public taxRate?: number,
    public type?: string,
    public extraField?: string,
    public extrafield1?: string
  ) {}
}

export interface IVoucherType {
  id?: number;
  name?: string;
  type?: string;
  method?: string;
  description?: string;
  extraField?: string;
  companyId?: number;
}

export class VoucherType implements IVoucherType {
  constructor(
    public id?: number,
    public name?: string,
    public type?: string,
    public method?: string,
    public description?: string,
    public extraField?: string,
    public companyId?: number
  ) {}
}

export interface ISalesVoucherType {
  id?: number;
  item?: string;
  quantity?: number;
  rate?: number;
  amount?: number;
  extra?: string;
  accountingVoucherId?: number;
}

export class SalesVoucherType implements ISalesVoucherType {
  constructor(
    public id?: number,
    public item?: string,
    public quantity?: number,
    public rate?: number,
    public amount?: number,
    public extra?: string,
    public accountingVoucherId?: number
  ) {}
}

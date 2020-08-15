export interface IAccountingVoucher {
  id?: number;
  accountName?: string;
  currentBalance?: string;
  particulars?: string;
  amount?: number;
  narration?: string;
  total?: string;
  grandTotal?: number;
}

export class AccountingVoucher implements IAccountingVoucher {
  constructor(
    public id?: number,
    public accountName?: string,
    public currentBalance?: string,
    public particulars?: string,
    public amount?: number,
    public narration?: string,
    public total?: string,
    public grandTotal?: number
  ) {}
}

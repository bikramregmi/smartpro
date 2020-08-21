import { ISalesVoucherType } from '@/shared/model/sales-voucher-type.model';

export interface ISalesVoucherTypeTotal {
  id?: number;
  item?: string;
  quantityTotal?: string;
  rateTotal?: number;
  amountTotal?: number;
  referenceNumber?: string;
  extraField?: string;
  salesVoucherTypes?: ISalesVoucherType[];
}

export class SalesVoucherTypeTotal implements ISalesVoucherTypeTotal {
  constructor(
    public id?: number,
    public item?: string,
    public quantityTotal?: string,
    public rateTotal?: number,
    public amountTotal?: number,
    public referenceNumber?: string,
    public extraField?: string,
    public salesVoucherTypes?: ISalesVoucherType[]
  ) {}
}

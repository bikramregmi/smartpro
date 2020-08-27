export interface IInventoryVoucher {
  id?: number;
  srItem?: string;
  srQuantity?: number;
  srRate?: number;
  srAmount?: number;
  narration?: string;
  desItem?: string;
  desQuantity?: number;
  desRate?: number;
  desAmount?: number;
  extraField?: string;
}

export class InventoryVoucher implements IInventoryVoucher {
  constructor(
    public id?: number,
    public srItem?: string,
    public srQuantity?: number,
    public srRate?: number,
    public srAmount?: number,
    public narration?: string,
    public desItem?: string,
    public desQuantity?: number,
    public desRate?: number,
    public desAmount?: number,
    public extraField?: string
  ) {}
}

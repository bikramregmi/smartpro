export interface IInventoryVoucherEntry {
  id?: number;
  srItem?: string;
  srQuantity?: number;
  srRate?: number;
  srAmount?: number;
  desItem?: string;
  desQuantity?: number;
  desRate?: number;
  desAmount?: number;
  extraField?: string;
  inventoryVoucherId?: number;
}

export class InventoryVoucherEntry implements IInventoryVoucherEntry {
  constructor(
    public id?: number,
    public srItem?: string,
    public srQuantity?: number,
    public srRate?: number,
    public srAmount?: number,
    public desItem?: string,
    public desQuantity?: number,
    public desRate?: number,
    public desAmount?: number,
    public extraField?: string,
    public inventoryVoucherId?: number
  ) {}
}

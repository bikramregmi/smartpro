export interface IProductItem {
  id?: number;
  name?: string;
  group?: string;
  units?: string;
  rate?: string;
  quantityPerRate?: string;
  value?: string;
  extraField?: string;
  companyId?: number;
}

export class ProductItem implements IProductItem {
  constructor(
    public id?: number,
    public name?: string,
    public group?: string,
    public units?: string,
    public rate?: string,
    public quantityPerRate?: string,
    public value?: string,
    public extraField?: string,
    public companyId?: number
  ) {}
}

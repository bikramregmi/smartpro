export interface IProductGroups {
  id?: number;
  name?: string;
  group?: string;
  quantity?: string;
  extraField?: string;
  companyId?: number;
}

export class ProductGroups implements IProductGroups {
  constructor(
    public id?: number,
    public name?: string,
    public group?: string,
    public quantity?: string,
    public extraField?: string,
    public companyId?: number
  ) {}
}

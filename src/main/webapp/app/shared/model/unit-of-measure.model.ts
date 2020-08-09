export interface IUnitOfMeasure {
  id?: number;
  formalName?: string;
  type?: string;
  symbol?: string;
  decimalPlaces?: string;
  extraField?: string;
}

export class UnitOfMeasure implements IUnitOfMeasure {
  constructor(
    public id?: number,
    public formalName?: string,
    public type?: string,
    public symbol?: string,
    public decimalPlaces?: string,
    public extraField?: string
  ) {}
}

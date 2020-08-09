export interface IGroups {
  id?: number;
  name?: string;
  group?: string;
  extraField1?: boolean;
  extraField2?: boolean;
  extraField3?: boolean;
  extraField4?: boolean;
  extraField5?: string;
  extraField6?: string;
}

export class Groups implements IGroups {
  constructor(
    public id?: number,
    public name?: string,
    public group?: string,
    public extraField1?: boolean,
    public extraField2?: boolean,
    public extraField3?: boolean,
    public extraField4?: boolean,
    public extraField5?: string,
    public extraField6?: string
  ) {
    this.extraField1 = this.extraField1 || false;
    this.extraField2 = this.extraField2 || false;
    this.extraField3 = this.extraField3 || false;
    this.extraField4 = this.extraField4 || false;
  }
}

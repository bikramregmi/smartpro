import { IGroups } from '@/shared/model/groups.model';

export interface ILedger {
  id?: number;
  name?: string;
  mailingName?: string;
  mailingAddress?: string;
  panNo?: string;
  extraField1?: boolean;
  extraField2?: boolean;
  extraField3?: boolean;
  extraField4?: string;
  extraField5?: string;
  groups?: IGroups[];
  ledgerId?: number;
}

export class Ledger implements ILedger {
  constructor(
    public id?: number,
    public name?: string,
    public mailingName?: string,
    public mailingAddress?: string,
    public panNo?: string,
    public extraField1?: boolean,
    public extraField2?: boolean,
    public extraField3?: boolean,
    public extraField4?: string,
    public extraField5?: string,
    public groups?: IGroups[],
    public ledgerId?: number
  ) {
    this.extraField1 = this.extraField1 || false;
    this.extraField2 = this.extraField2 || false;
    this.extraField3 = this.extraField3 || false;
  }
}

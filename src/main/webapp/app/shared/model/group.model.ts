export interface IGroup {
  id?: number;
  name?: string;
  description?: string;
  option?: boolean;
}

export class Group implements IGroup {
  constructor(public id?: number, public name?: string, public description?: string, public option?: boolean) {
    this.option = this.option || false;
  }
}

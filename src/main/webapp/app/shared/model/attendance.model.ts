export interface IAttendance {
  id?: number;
  checkIn?: Date;
  checkOut?: Date;
  description?: string;
  employeeFullName?: string;
  employeeId?: number;
}

export class Attendance implements IAttendance {
  constructor(
    public id?: number,
    public checkIn?: Date,
    public checkOut?: Date,
    public description?: string,
    public employeeFullName?: string,
    public employeeId?: number
  ) {}
}

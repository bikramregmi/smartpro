import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import EmployeeService from '../employee/employee.service';
import { IEmployee } from '@/shared/model/employee.model';

import AlertService from '@/shared/alert/alert.service';
import { IAttendance, Attendance } from '@/shared/model/attendance.model';
import AttendanceService from './attendance.service';

const validations: any = {
  attendance: {
    checkIn: {
      required,
    },
    checkOut: {},
    description: {},
  },
};

@Component({
  validations,
})
export default class AttendanceUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('attendanceService') private attendanceService: () => AttendanceService;
  public attendance: IAttendance = new Attendance();

  @Inject('employeeService') private employeeService: () => EmployeeService;

  public employees: IEmployee[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.attendanceId) {
        vm.retrieveAttendance(to.params.attendanceId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.attendance.id) {
      this.attendanceService()
        .update(this.attendance)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.attendance.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.attendanceService()
        .create(this.attendance)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.attendance.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveAttendance(attendanceId): void {
    this.attendanceService()
      .find(attendanceId)
      .then(res => {
        this.attendance = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.employeeService()
      .retrieve()
      .then(res => {
        this.employees = res.data;
      });
  }
}

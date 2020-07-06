import { Component, Vue, Inject } from 'vue-property-decorator';

import { IAttendance } from '@/shared/model/attendance.model';
import AttendanceService from './attendance.service';

@Component
export default class AttendanceDetails extends Vue {
  @Inject('attendanceService') private attendanceService: () => AttendanceService;
  public attendance: IAttendance = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.attendanceId) {
        vm.retrieveAttendance(to.params.attendanceId);
      }
    });
  }

  public retrieveAttendance(attendanceId) {
    this.attendanceService()
      .find(attendanceId)
      .then(res => {
        this.attendance = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

import { Component, Vue, Inject } from 'vue-property-decorator';

import { IEmployeeSalary } from '@/shared/model/employee-salary.model';
import EmployeeSalaryService from './employee-salary.service';

@Component
export default class EmployeeSalaryDetails extends Vue {
  @Inject('employeeSalaryService') private employeeSalaryService: () => EmployeeSalaryService;
  public employeeSalary: IEmployeeSalary = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.employeeSalaryId) {
        vm.retrieveEmployeeSalary(to.params.employeeSalaryId);
      }
    });
  }

  public retrieveEmployeeSalary(employeeSalaryId) {
    this.employeeSalaryService()
      .find(employeeSalaryId)
      .then(res => {
        this.employeeSalary = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

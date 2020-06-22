import { Component, Vue, Inject } from 'vue-property-decorator';

import { IEmployeeInformation } from '@/shared/model/employee-information.model';
import EmployeeInformationService from './employee-information.service';

@Component
export default class EmployeeInformationDetails extends Vue {
  @Inject('employeeInformationService') private employeeInformationService: () => EmployeeInformationService;
  public employeeInformation: IEmployeeInformation = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.employeeInformationId) {
        vm.retrieveEmployeeInformation(to.params.employeeInformationId);
      }
    });
  }

  public retrieveEmployeeInformation(employeeInformationId) {
    this.employeeInformationService()
      .find(employeeInformationId)
      .then(res => {
        this.employeeInformation = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

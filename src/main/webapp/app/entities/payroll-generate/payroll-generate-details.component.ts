import { Component, Vue, Inject } from 'vue-property-decorator';

import { IPayrollGenerate } from '@/shared/model/payroll-generate.model';
import PayrollGenerateService from './payroll-generate.service';

@Component
export default class PayrollGenerateDetails extends Vue {
  @Inject('payrollGenerateService') private payrollGenerateService: () => PayrollGenerateService;
  public payrollGenerate: IPayrollGenerate = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.payrollGenerateId) {
        vm.retrievePayrollGenerate(to.params.payrollGenerateId);
      }
    });
  }

  public retrievePayrollGenerate(payrollGenerateId) {
    this.payrollGenerateService()
      .find(payrollGenerateId)
      .then(res => {
        this.payrollGenerate = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

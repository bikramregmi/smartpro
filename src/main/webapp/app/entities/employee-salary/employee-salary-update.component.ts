import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IEmployeeSalary, EmployeeSalary } from '@/shared/model/employee-salary.model';
import EmployeeSalaryService from './employee-salary.service';

const validations: any = {
  employeeSalary: {
    basicSalary: {},
    allowance: {},
    ot: {},
    bonus: {},
    description: {},
    tax: {},
    pf: {},
    extra: {},
  },
};

@Component({
  validations,
})
export default class EmployeeSalaryUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('employeeSalaryService') private employeeSalaryService: () => EmployeeSalaryService;
  public employeeSalary: IEmployeeSalary = new EmployeeSalary();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.employeeSalaryId) {
        vm.retrieveEmployeeSalary(to.params.employeeSalaryId);
      }
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
    if (this.employeeSalary.id) {
      this.employeeSalaryService()
        .update(this.employeeSalary)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.employeeSalary.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.employeeSalaryService()
        .create(this.employeeSalary)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.employeeSalary.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveEmployeeSalary(employeeSalaryId): void {
    this.employeeSalaryService()
      .find(employeeSalaryId)
      .then(res => {
        this.employeeSalary = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}

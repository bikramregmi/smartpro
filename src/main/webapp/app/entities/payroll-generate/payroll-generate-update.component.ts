import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import EmployeeService from '../employee/employee.service';
import { IEmployee } from '@/shared/model/employee.model';

import EmployeeSalaryService from '../employee-salary/employee-salary.service';
import { IEmployeeSalary } from '@/shared/model/employee-salary.model';

import AlertService from '@/shared/alert/alert.service';
import { IPayrollGenerate, PayrollGenerate } from '@/shared/model/payroll-generate.model';
import PayrollGenerateService from './payroll-generate.service';

const validations: any = {
  payrollGenerate: {
    salaryMonth: {
      required,
    },
    description: {},
    tax: {},
    pf: {},
  },
};

@Component({
  validations,
})
export default class PayrollGenerateUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('payrollGenerateService') private payrollGenerateService: () => PayrollGenerateService;
  public payrollGenerate: IPayrollGenerate = new PayrollGenerate();

  @Inject('employeeService') private employeeService: () => EmployeeService;

  public employees: IEmployee[] = [];

  @Inject('employeeSalaryService') private employeeSalaryService: () => EmployeeSalaryService;

  public employeeSalaries: IEmployeeSalary[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.payrollGenerateId) {
        vm.retrievePayrollGenerate(to.params.payrollGenerateId);
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
    if (this.payrollGenerate.id) {
      this.payrollGenerateService()
        .update(this.payrollGenerate)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.payrollGenerate.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.payrollGenerateService()
        .create(this.payrollGenerate)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.payrollGenerate.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrievePayrollGenerate(payrollGenerateId): void {
    this.payrollGenerateService()
      .find(payrollGenerateId)
      .then(res => {
        this.payrollGenerate = res;
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
    this.employeeSalaryService()
      .retrieve()
      .then(res => {
        this.employeeSalaries = res.data;
      });
  }
}

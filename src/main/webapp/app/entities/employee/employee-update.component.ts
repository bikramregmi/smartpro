import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import EmployeeSalaryService from '../employee-salary/employee-salary.service';
import { IEmployeeSalary } from '@/shared/model/employee-salary.model';

import EmployeeInformationService from '../employee-information/employee-information.service';
import { IEmployeeInformation } from '@/shared/model/employee-information.model';

import AlertService from '@/shared/alert/alert.service';
import { IEmployee, Employee } from '@/shared/model/employee.model';
import EmployeeService from './employee.service';

const validations: any = {
  employee: {
    fullName: {
      required,
    },
    email: {
      required,
    },
    eCode: {
      required,
    },
    isActive: {},
  },
};

@Component({
  validations,
})
export default class EmployeeUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('employeeService') private employeeService: () => EmployeeService;
  public employee: IEmployee = new Employee();

  @Inject('employeeSalaryService') private employeeSalaryService: () => EmployeeSalaryService;

  public employeeSalaries: IEmployeeSalary[] = [];

  @Inject('employeeInformationService') private employeeInformationService: () => EmployeeInformationService;

  public employeeInformations: IEmployeeInformation[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.employeeId) {
        vm.retrieveEmployee(to.params.employeeId);
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
    if (this.employee.id) {
      this.employeeService()
        .update(this.employee)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.employee.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.employeeService()
        .create(this.employee)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.employee.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveEmployee(employeeId): void {
    this.employeeService()
      .find(employeeId)
      .then(res => {
        this.employee = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.employeeSalaryService()
      .retrieve()
      .then(res => {
        this.employeeSalaries = res.data;
      });
    this.employeeInformationService()
      .retrieve()
      .then(res => {
        this.employeeInformations = res.data;
      });
  }
}

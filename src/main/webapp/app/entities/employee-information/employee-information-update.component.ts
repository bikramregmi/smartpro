import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IEmployeeInformation, EmployeeInformation } from '@/shared/model/employee-information.model';
import EmployeeInformationService from './employee-information.service';

const validations: any = {
  employeeInformation: {
    dob: {},
    gender: {},
    isMarried: {},
    bloodGroup: {},
    parentName: {},
    addressline1: {},
    addressline2: {},
    phoneNumber: {},
    mobileNumber: {},
    emergencyContactNumber: {},
    joiningDate: {},
    designation: {},
    panNumber: {},
    documentNumber: {},
    extraField: {},
  },
};

@Component({
  validations,
})
export default class EmployeeInformationUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('employeeInformationService') private employeeInformationService: () => EmployeeInformationService;
  public employeeInformation: IEmployeeInformation = new EmployeeInformation();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.employeeInformationId) {
        vm.retrieveEmployeeInformation(to.params.employeeInformationId);
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
    if (this.employeeInformation.id) {
      this.employeeInformationService()
        .update(this.employeeInformation)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.employeeInformation.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.employeeInformationService()
        .create(this.employeeInformation)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.employeeInformation.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveEmployeeInformation(employeeInformationId): void {
    this.employeeInformationService()
      .find(employeeInformationId)
      .then(res => {
        this.employeeInformation = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}

import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import CompanyService from '../company/company.service';
import { ICompany } from '@/shared/model/company.model';

import AlertService from '@/shared/alert/alert.service';
import { IVoucherType, VoucherType } from '@/shared/model/voucher-type.model';
import VoucherTypeService from './voucher-type.service';

const validations: any = {
  voucherType: {
    name: {
      required,
    },
    type: {},
    method: {},
    description: {},
    extraField: {},
  },
};

@Component({
  validations,
})
export default class VoucherTypeUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('voucherTypeService') private voucherTypeService: () => VoucherTypeService;
  public voucherType: IVoucherType = new VoucherType();

  @Inject('companyService') private companyService: () => CompanyService;

  public companies: ICompany[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.voucherTypeId) {
        vm.retrieveVoucherType(to.params.voucherTypeId);
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
    if (this.voucherType.id) {
      this.voucherTypeService()
        .update(this.voucherType)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.voucherType.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.voucherTypeService()
        .create(this.voucherType)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.voucherType.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveVoucherType(voucherTypeId): void {
    this.voucherTypeService()
      .find(voucherTypeId)
      .then(res => {
        this.voucherType = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.companyService()
      .retrieve()
      .then(res => {
        this.companies = res.data;
      });
  }
}

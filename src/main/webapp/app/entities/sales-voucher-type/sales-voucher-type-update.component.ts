import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AccountingVoucherService from '../accounting-voucher/accounting-voucher.service';
import { IAccountingVoucher } from '@/shared/model/accounting-voucher.model';

import AlertService from '@/shared/alert/alert.service';
import { ISalesVoucherType, SalesVoucherType } from '@/shared/model/sales-voucher-type.model';
import SalesVoucherTypeService from './sales-voucher-type.service';

const validations: any = {
  salesVoucherType: {
    item: {},
    quantity: {},
    rate: {},
    amount: {},
    extra: {},
  },
};

@Component({
  validations,
})
export default class SalesVoucherTypeUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('salesVoucherTypeService') private salesVoucherTypeService: () => SalesVoucherTypeService;
  public salesVoucherType: ISalesVoucherType = new SalesVoucherType();

  @Inject('accountingVoucherService') private accountingVoucherService: () => AccountingVoucherService;

  public accountingVouchers: IAccountingVoucher[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.salesVoucherTypeId) {
        vm.retrieveSalesVoucherType(to.params.salesVoucherTypeId);
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
    if (this.salesVoucherType.id) {
      this.salesVoucherTypeService()
        .update(this.salesVoucherType)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.salesVoucherType.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.salesVoucherTypeService()
        .create(this.salesVoucherType)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.salesVoucherType.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveSalesVoucherType(salesVoucherTypeId): void {
    this.salesVoucherTypeService()
      .find(salesVoucherTypeId)
      .then(res => {
        this.salesVoucherType = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.accountingVoucherService()
      .retrieve()
      .then(res => {
        this.accountingVouchers = res.data;
      });
  }
}

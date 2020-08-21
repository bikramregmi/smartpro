import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import SalesVoucherTypeService from '../sales-voucher-type/sales-voucher-type.service';
import { ISalesVoucherType } from '@/shared/model/sales-voucher-type.model';

import AlertService from '@/shared/alert/alert.service';
import { ISalesVoucherTypeTotal, SalesVoucherTypeTotal } from '@/shared/model/sales-voucher-type-total.model';
import SalesVoucherTypeTotalService from './sales-voucher-type-total.service';

const validations: any = {
  salesVoucherTypeTotal: {
    item: {},
    quantityTotal: {},
    rateTotal: {},
    amountTotal: {},
    referenceNumber: {},
    extraField: {},
  },
};

@Component({
  validations,
})
export default class SalesVoucherTypeTotalUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('salesVoucherTypeTotalService') private salesVoucherTypeTotalService: () => SalesVoucherTypeTotalService;
  public salesVoucherTypeTotal: ISalesVoucherTypeTotal = new SalesVoucherTypeTotal();

  @Inject('salesVoucherTypeService') private salesVoucherTypeService: () => SalesVoucherTypeService;

  public salesVoucherTypes: ISalesVoucherType[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.salesVoucherTypeTotalId) {
        vm.retrieveSalesVoucherTypeTotal(to.params.salesVoucherTypeTotalId);
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
    if (this.salesVoucherTypeTotal.id) {
      this.salesVoucherTypeTotalService()
        .update(this.salesVoucherTypeTotal)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.salesVoucherTypeTotal.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.salesVoucherTypeTotalService()
        .create(this.salesVoucherTypeTotal)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.salesVoucherTypeTotal.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveSalesVoucherTypeTotal(salesVoucherTypeTotalId): void {
    this.salesVoucherTypeTotalService()
      .find(salesVoucherTypeTotalId)
      .then(res => {
        this.salesVoucherTypeTotal = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.salesVoucherTypeService()
      .retrieve()
      .then(res => {
        this.salesVoucherTypes = res.data;
      });
  }
}

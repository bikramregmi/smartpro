import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IInventoryVoucher, InventoryVoucher } from '@/shared/model/inventory-voucher.model';
import InventoryVoucherService from './inventory-voucher.service';

const validations: any = {
  inventoryVoucher: {
    srItem: {},
    srQuantity: {},
    srRate: {},
    srAmount: {},
    narration: {},
    desItem: {},
    desQuantity: {},
    desRate: {},
    desAmount: {},
    extraField: {},
  },
};

@Component({
  validations,
})
export default class InventoryVoucherUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('inventoryVoucherService') private inventoryVoucherService: () => InventoryVoucherService;
  public inventoryVoucher: IInventoryVoucher = new InventoryVoucher();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.inventoryVoucherId) {
        vm.retrieveInventoryVoucher(to.params.inventoryVoucherId);
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
    if (this.inventoryVoucher.id) {
      this.inventoryVoucherService()
        .update(this.inventoryVoucher)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.inventoryVoucher.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.inventoryVoucherService()
        .create(this.inventoryVoucher)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.inventoryVoucher.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveInventoryVoucher(inventoryVoucherId): void {
    this.inventoryVoucherService()
      .find(inventoryVoucherId)
      .then(res => {
        this.inventoryVoucher = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}

import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import InventoryVoucherService from '../inventory-voucher/inventory-voucher.service';
import { IInventoryVoucher } from '@/shared/model/inventory-voucher.model';

import AlertService from '@/shared/alert/alert.service';
import { IInventoryVoucherEntry, InventoryVoucherEntry } from '@/shared/model/inventory-voucher-entry.model';
import InventoryVoucherEntryService from './inventory-voucher-entry.service';

const validations: any = {
  inventoryVoucherEntry: {
    srItem: {},
    srQuantity: {},
    srRate: {},
    srAmount: {},
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
export default class InventoryVoucherEntryUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('inventoryVoucherEntryService') private inventoryVoucherEntryService: () => InventoryVoucherEntryService;
  public inventoryVoucherEntry: IInventoryVoucherEntry = new InventoryVoucherEntry();

  @Inject('inventoryVoucherService') private inventoryVoucherService: () => InventoryVoucherService;

  public inventoryVouchers: IInventoryVoucher[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.inventoryVoucherEntryId) {
        vm.retrieveInventoryVoucherEntry(to.params.inventoryVoucherEntryId);
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
    if (this.inventoryVoucherEntry.id) {
      this.inventoryVoucherEntryService()
        .update(this.inventoryVoucherEntry)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.inventoryVoucherEntry.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.inventoryVoucherEntryService()
        .create(this.inventoryVoucherEntry)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.inventoryVoucherEntry.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveInventoryVoucherEntry(inventoryVoucherEntryId): void {
    this.inventoryVoucherEntryService()
      .find(inventoryVoucherEntryId)
      .then(res => {
        this.inventoryVoucherEntry = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.inventoryVoucherService()
      .retrieve()
      .then(res => {
        this.inventoryVouchers = res.data;
      });
  }
}

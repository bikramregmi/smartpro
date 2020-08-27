import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IInventoryVoucherEntry } from '@/shared/model/inventory-voucher-entry.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import InventoryVoucherEntryService from './inventory-voucher-entry.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class InventoryVoucherEntry extends mixins(AlertMixin) {
  @Inject('inventoryVoucherEntryService') private inventoryVoucherEntryService: () => InventoryVoucherEntryService;
  private removeId: number = null;

  public inventoryVoucherEntries: IInventoryVoucherEntry[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllInventoryVoucherEntrys();
  }

  public clear(): void {
    this.retrieveAllInventoryVoucherEntrys();
  }

  public retrieveAllInventoryVoucherEntrys(): void {
    this.isFetching = true;

    this.inventoryVoucherEntryService()
      .retrieve()
      .then(
        res => {
          this.inventoryVoucherEntries = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IInventoryVoucherEntry): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeInventoryVoucherEntry(): void {
    this.inventoryVoucherEntryService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('smartproApp.inventoryVoucherEntry.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllInventoryVoucherEntrys();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}

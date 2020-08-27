import { Component, Vue, Inject } from 'vue-property-decorator';

import { IInventoryVoucherEntry } from '@/shared/model/inventory-voucher-entry.model';
import InventoryVoucherEntryService from './inventory-voucher-entry.service';

@Component
export default class InventoryVoucherEntryDetails extends Vue {
  @Inject('inventoryVoucherEntryService') private inventoryVoucherEntryService: () => InventoryVoucherEntryService;
  public inventoryVoucherEntry: IInventoryVoucherEntry = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.inventoryVoucherEntryId) {
        vm.retrieveInventoryVoucherEntry(to.params.inventoryVoucherEntryId);
      }
    });
  }

  public retrieveInventoryVoucherEntry(inventoryVoucherEntryId) {
    this.inventoryVoucherEntryService()
      .find(inventoryVoucherEntryId)
      .then(res => {
        this.inventoryVoucherEntry = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

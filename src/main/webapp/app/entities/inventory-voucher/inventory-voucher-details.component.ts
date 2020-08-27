import { Component, Vue, Inject } from 'vue-property-decorator';

import { IInventoryVoucher } from '@/shared/model/inventory-voucher.model';
import InventoryVoucherService from './inventory-voucher.service';

@Component
export default class InventoryVoucherDetails extends Vue {
  @Inject('inventoryVoucherService') private inventoryVoucherService: () => InventoryVoucherService;
  public inventoryVoucher: IInventoryVoucher = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.inventoryVoucherId) {
        vm.retrieveInventoryVoucher(to.params.inventoryVoucherId);
      }
    });
  }

  public retrieveInventoryVoucher(inventoryVoucherId) {
    this.inventoryVoucherService()
      .find(inventoryVoucherId)
      .then(res => {
        this.inventoryVoucher = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

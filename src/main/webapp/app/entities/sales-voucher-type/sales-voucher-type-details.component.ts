import { Component, Vue, Inject } from 'vue-property-decorator';

import { ISalesVoucherType } from '@/shared/model/sales-voucher-type.model';
import SalesVoucherTypeService from './sales-voucher-type.service';

@Component
export default class SalesVoucherTypeDetails extends Vue {
  @Inject('salesVoucherTypeService') private salesVoucherTypeService: () => SalesVoucherTypeService;
  public salesVoucherType: ISalesVoucherType = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.salesVoucherTypeId) {
        vm.retrieveSalesVoucherType(to.params.salesVoucherTypeId);
      }
    });
  }

  public retrieveSalesVoucherType(salesVoucherTypeId) {
    this.salesVoucherTypeService()
      .find(salesVoucherTypeId)
      .then(res => {
        this.salesVoucherType = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

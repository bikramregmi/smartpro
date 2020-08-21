import { Component, Vue, Inject } from 'vue-property-decorator';

import { ISalesVoucherTypeTotal } from '@/shared/model/sales-voucher-type-total.model';
import SalesVoucherTypeTotalService from './sales-voucher-type-total.service';

@Component
export default class SalesVoucherTypeTotalDetails extends Vue {
  @Inject('salesVoucherTypeTotalService') private salesVoucherTypeTotalService: () => SalesVoucherTypeTotalService;
  public salesVoucherTypeTotal: ISalesVoucherTypeTotal = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.salesVoucherTypeTotalId) {
        vm.retrieveSalesVoucherTypeTotal(to.params.salesVoucherTypeTotalId);
      }
    });
  }

  public retrieveSalesVoucherTypeTotal(salesVoucherTypeTotalId) {
    this.salesVoucherTypeTotalService()
      .find(salesVoucherTypeTotalId)
      .then(res => {
        this.salesVoucherTypeTotal = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

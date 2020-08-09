import { Component, Vue, Inject } from 'vue-property-decorator';

import { IVoucherType } from '@/shared/model/voucher-type.model';
import VoucherTypeService from './voucher-type.service';

@Component
export default class VoucherTypeDetails extends Vue {
  @Inject('voucherTypeService') private voucherTypeService: () => VoucherTypeService;
  public voucherType: IVoucherType = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.voucherTypeId) {
        vm.retrieveVoucherType(to.params.voucherTypeId);
      }
    });
  }

  public retrieveVoucherType(voucherTypeId) {
    this.voucherTypeService()
      .find(voucherTypeId)
      .then(res => {
        this.voucherType = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

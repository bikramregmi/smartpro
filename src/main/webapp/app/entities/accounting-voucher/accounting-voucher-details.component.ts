import { Component, Vue, Inject } from 'vue-property-decorator';

import { IAccountingVoucher } from '@/shared/model/accounting-voucher.model';
import AccountingVoucherService from './accounting-voucher.service';

@Component
export default class AccountingVoucherDetails extends Vue {
  @Inject('accountingVoucherService') private accountingVoucherService: () => AccountingVoucherService;
  public accountingVoucher: IAccountingVoucher = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.accountingVoucherId) {
        vm.retrieveAccountingVoucher(to.params.accountingVoucherId);
      }
    });
  }

  public retrieveAccountingVoucher(accountingVoucherId) {
    this.accountingVoucherService()
      .find(accountingVoucherId)
      .then(res => {
        this.accountingVoucher = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

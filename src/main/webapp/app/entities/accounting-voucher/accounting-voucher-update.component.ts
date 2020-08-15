import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IAccountingVoucher, AccountingVoucher } from '@/shared/model/accounting-voucher.model';
import AccountingVoucherService from './accounting-voucher.service';

const validations: any = {
  accountingVoucher: {
    accountName: {
      required,
    },
    currentBalance: {},
    particulars: {},
    amount: {},
    narration: {},
    total: {},
    grandTotal: {},
  },
};

@Component({
  validations,
})
export default class AccountingVoucherUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('accountingVoucherService') private accountingVoucherService: () => AccountingVoucherService;
  public accountingVoucher: IAccountingVoucher = new AccountingVoucher();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.accountingVoucherId) {
        vm.retrieveAccountingVoucher(to.params.accountingVoucherId);
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
    if (this.accountingVoucher.id) {
      this.accountingVoucherService()
        .update(this.accountingVoucher)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.accountingVoucher.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.accountingVoucherService()
        .create(this.accountingVoucher)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.accountingVoucher.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveAccountingVoucher(accountingVoucherId): void {
    this.accountingVoucherService()
      .find(accountingVoucherId)
      .then(res => {
        this.accountingVoucher = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}

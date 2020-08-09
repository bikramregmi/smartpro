import { Component, Vue, Inject } from 'vue-property-decorator';

import { ILedger } from '@/shared/model/ledger.model';
import LedgerService from './ledger.service';

@Component
export default class LedgerDetails extends Vue {
  @Inject('ledgerService') private ledgerService: () => LedgerService;
  public ledger: ILedger = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.ledgerId) {
        vm.retrieveLedger(to.params.ledgerId);
      }
    });
  }

  public retrieveLedger(ledgerId) {
    this.ledgerService()
      .find(ledgerId)
      .then(res => {
        this.ledger = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

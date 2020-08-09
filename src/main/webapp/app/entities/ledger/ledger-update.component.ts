import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import GroupsService from '../groups/groups.service';
import { IGroups } from '@/shared/model/groups.model';

import AlertService from '@/shared/alert/alert.service';
import { ILedger, Ledger } from '@/shared/model/ledger.model';
import LedgerService from './ledger.service';

const validations: any = {
  ledger: {
    name: {
      required,
    },
    mailingName: {},
    mailingAddress: {},
    panNo: {},
    extraField1: {},
    extraField2: {},
    extraField3: {},
    extraField4: {},
    extraField5: {},
  },
};

@Component({
  validations,
})
export default class LedgerUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('ledgerService') private ledgerService: () => LedgerService;
  public ledger: ILedger = new Ledger();

  @Inject('groupsService') private groupsService: () => GroupsService;

  public groups: IGroups[] = [];

  public ledgers: ILedger[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.ledgerId) {
        vm.retrieveLedger(to.params.ledgerId);
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
    if (this.ledger.id) {
      this.ledgerService()
        .update(this.ledger)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.ledger.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.ledgerService()
        .create(this.ledger)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.ledger.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveLedger(ledgerId): void {
    this.ledgerService()
      .find(ledgerId)
      .then(res => {
        this.ledger = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.groupsService()
      .retrieve()
      .then(res => {
        this.groups = res.data;
      });
    this.ledgerService()
      .retrieve()
      .then(res => {
        this.ledgers = res.data;
      });
  }
}

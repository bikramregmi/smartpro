import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ILedger } from '@/shared/model/ledger.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import LedgerService from './ledger.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Ledger extends mixins(AlertMixin) {
  @Inject('ledgerService') private ledgerService: () => LedgerService;
  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;

  public ledgers: ILedger[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllLedgers();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllLedgers();
  }

  public retrieveAllLedgers(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };
    this.ledgerService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.ledgers = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: ILedger): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeLedger(): void {
    this.ledgerService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('smartproApp.ledger.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllLedgers();
        this.closeDialog();
      });
  }

  public sort(): Array<any> {
    const result = [this.propOrder + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.propOrder !== 'id') {
      result.push('id');
    }
    return result;
  }

  public loadPage(page: number): void {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  public transition(): void {
    this.retrieveAllLedgers();
  }

  public changeOrder(propOrder): void {
    this.propOrder = propOrder;
    this.reverse = !this.reverse;
    this.transition();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}

import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ISalesVoucherType } from '@/shared/model/sales-voucher-type.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import SalesVoucherTypeService from './sales-voucher-type.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class SalesVoucherType extends mixins(AlertMixin) {
  @Inject('salesVoucherTypeService') private salesVoucherTypeService: () => SalesVoucherTypeService;
  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;

  public salesVoucherTypes: ISalesVoucherType[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllSalesVoucherTypes();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllSalesVoucherTypes();
  }

  public retrieveAllSalesVoucherTypes(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };
    this.salesVoucherTypeService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.salesVoucherTypes = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: ISalesVoucherType): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeSalesVoucherType(): void {
    this.salesVoucherTypeService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('smartproApp.salesVoucherType.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllSalesVoucherTypes();
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
    this.retrieveAllSalesVoucherTypes();
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

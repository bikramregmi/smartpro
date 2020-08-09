import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IUnitOfMeasure } from '@/shared/model/unit-of-measure.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import UnitOfMeasureService from './unit-of-measure.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class UnitOfMeasure extends mixins(AlertMixin) {
  @Inject('unitOfMeasureService') private unitOfMeasureService: () => UnitOfMeasureService;
  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;

  public unitOfMeasures: IUnitOfMeasure[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllUnitOfMeasures();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllUnitOfMeasures();
  }

  public retrieveAllUnitOfMeasures(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };
    this.unitOfMeasureService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.unitOfMeasures = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IUnitOfMeasure): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeUnitOfMeasure(): void {
    this.unitOfMeasureService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('smartproApp.unitOfMeasure.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllUnitOfMeasures();
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
    this.retrieveAllUnitOfMeasures();
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

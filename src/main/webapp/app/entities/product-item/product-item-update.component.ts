import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import CompanyService from '../company/company.service';
import { ICompany } from '@/shared/model/company.model';

import AlertService from '@/shared/alert/alert.service';
import { IProductItem, ProductItem } from '@/shared/model/product-item.model';
import ProductItemService from './product-item.service';

const validations: any = {
  productItem: {
    name: {
      required,
    },
    group: {},
    units: {},
    rate: {},
    quantityPerRate: {},
    value: {},
    extraField: {},
  },
};

@Component({
  validations,
})
export default class ProductItemUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('productItemService') private productItemService: () => ProductItemService;
  public productItem: IProductItem = new ProductItem();

  @Inject('companyService') private companyService: () => CompanyService;

  public companies: ICompany[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.productItemId) {
        vm.retrieveProductItem(to.params.productItemId);
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
    if (this.productItem.id) {
      this.productItemService()
        .update(this.productItem)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.productItem.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.productItemService()
        .create(this.productItem)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.productItem.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveProductItem(productItemId): void {
    this.productItemService()
      .find(productItemId)
      .then(res => {
        this.productItem = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.companyService()
      .retrieve()
      .then(res => {
        this.companies = res.data;
      });
  }
}

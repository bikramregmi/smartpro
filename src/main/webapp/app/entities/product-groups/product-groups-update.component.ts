import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import CompanyService from '../company/company.service';
import { ICompany } from '@/shared/model/company.model';

import AlertService from '@/shared/alert/alert.service';
import { IProductGroups, ProductGroups } from '@/shared/model/product-groups.model';
import ProductGroupsService from './product-groups.service';

const validations: any = {
  productGroups: {
    name: {
      required,
    },
    group: {},
    quantity: {},
    extraField: {},
  },
};

@Component({
  validations,
})
export default class ProductGroupsUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('productGroupsService') private productGroupsService: () => ProductGroupsService;
  public productGroups: IProductGroups = new ProductGroups();

  @Inject('companyService') private companyService: () => CompanyService;

  public companies: ICompany[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.productGroupsId) {
        vm.retrieveProductGroups(to.params.productGroupsId);
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
    if (this.productGroups.id) {
      this.productGroupsService()
        .update(this.productGroups)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.productGroups.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.productGroupsService()
        .create(this.productGroups)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.productGroups.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveProductGroups(productGroupsId): void {
    this.productGroupsService()
      .find(productGroupsId)
      .then(res => {
        this.productGroups = res;
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

import { Component, Vue, Inject } from 'vue-property-decorator';

import { IProductGroups } from '@/shared/model/product-groups.model';
import ProductGroupsService from './product-groups.service';

@Component
export default class ProductGroupsDetails extends Vue {
  @Inject('productGroupsService') private productGroupsService: () => ProductGroupsService;
  public productGroups: IProductGroups = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.productGroupsId) {
        vm.retrieveProductGroups(to.params.productGroupsId);
      }
    });
  }

  public retrieveProductGroups(productGroupsId) {
    this.productGroupsService()
      .find(productGroupsId)
      .then(res => {
        this.productGroups = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

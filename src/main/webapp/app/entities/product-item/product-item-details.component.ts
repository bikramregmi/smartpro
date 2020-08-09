import { Component, Vue, Inject } from 'vue-property-decorator';

import { IProductItem } from '@/shared/model/product-item.model';
import ProductItemService from './product-item.service';

@Component
export default class ProductItemDetails extends Vue {
  @Inject('productItemService') private productItemService: () => ProductItemService;
  public productItem: IProductItem = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.productItemId) {
        vm.retrieveProductItem(to.params.productItemId);
      }
    });
  }

  public retrieveProductItem(productItemId) {
    this.productItemService()
      .find(productItemId)
      .then(res => {
        this.productItem = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

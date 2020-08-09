import { Component, Vue, Inject } from 'vue-property-decorator';

import { IUnitOfMeasure } from '@/shared/model/unit-of-measure.model';
import UnitOfMeasureService from './unit-of-measure.service';

@Component
export default class UnitOfMeasureDetails extends Vue {
  @Inject('unitOfMeasureService') private unitOfMeasureService: () => UnitOfMeasureService;
  public unitOfMeasure: IUnitOfMeasure = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.unitOfMeasureId) {
        vm.retrieveUnitOfMeasure(to.params.unitOfMeasureId);
      }
    });
  }

  public retrieveUnitOfMeasure(unitOfMeasureId) {
    this.unitOfMeasureService()
      .find(unitOfMeasureId)
      .then(res => {
        this.unitOfMeasure = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IUnitOfMeasure, UnitOfMeasure } from '@/shared/model/unit-of-measure.model';
import UnitOfMeasureService from './unit-of-measure.service';

const validations: any = {
  unitOfMeasure: {
    formalName: {},
    type: {},
    symbol: {},
    decimalPlaces: {},
    extraField: {},
  },
};

@Component({
  validations,
})
export default class UnitOfMeasureUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('unitOfMeasureService') private unitOfMeasureService: () => UnitOfMeasureService;
  public unitOfMeasure: IUnitOfMeasure = new UnitOfMeasure();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.unitOfMeasureId) {
        vm.retrieveUnitOfMeasure(to.params.unitOfMeasureId);
      }
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
    if (this.unitOfMeasure.id) {
      this.unitOfMeasureService()
        .update(this.unitOfMeasure)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.unitOfMeasure.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.unitOfMeasureService()
        .create(this.unitOfMeasure)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.unitOfMeasure.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveUnitOfMeasure(unitOfMeasureId): void {
    this.unitOfMeasureService()
      .find(unitOfMeasureId)
      .then(res => {
        this.unitOfMeasure = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}

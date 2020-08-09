import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IGroups, Groups } from '@/shared/model/groups.model';
import GroupsService from './groups.service';

const validations: any = {
  groups: {
    name: {
      required,
    },
    group: {},
    extraField1: {},
    extraField2: {},
    extraField3: {},
    extraField4: {},
    extraField5: {},
    extraField6: {},
  },
};

@Component({
  validations,
})
export default class GroupsUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('groupsService') private groupsService: () => GroupsService;
  public groups: IGroups = new Groups();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.groupsId) {
        vm.retrieveGroups(to.params.groupsId);
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
    if (this.groups.id) {
      this.groupsService()
        .update(this.groups)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.groups.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.groupsService()
        .create(this.groups)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('smartproApp.groups.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveGroups(groupsId): void {
    this.groupsService()
      .find(groupsId)
      .then(res => {
        this.groups = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}

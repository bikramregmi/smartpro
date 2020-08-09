/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import GroupDetailComponent from '@/entities/group/group-details.vue';
import GroupClass from '@/entities/group/group-details.component';
import GroupService from '@/entities/group/group.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Group Management Detail Component', () => {
    let wrapper: Wrapper<GroupClass>;
    let comp: GroupClass;
    let groupServiceStub: SinonStubbedInstance<GroupService>;

    beforeEach(() => {
      groupServiceStub = sinon.createStubInstance<GroupService>(GroupService);

      wrapper = shallowMount<GroupClass>(GroupDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { groupService: () => groupServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundGroup = { id: 123 };
        groupServiceStub.find.resolves(foundGroup);

        // WHEN
        comp.retrieveGroup(123);
        await comp.$nextTick();

        // THEN
        expect(comp.group).toBe(foundGroup);
      });
    });
  });
});

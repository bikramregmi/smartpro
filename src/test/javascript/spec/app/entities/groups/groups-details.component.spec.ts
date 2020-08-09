/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import GroupsDetailComponent from '@/entities/groups/groups-details.vue';
import GroupsClass from '@/entities/groups/groups-details.component';
import GroupsService from '@/entities/groups/groups.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Groups Management Detail Component', () => {
    let wrapper: Wrapper<GroupsClass>;
    let comp: GroupsClass;
    let groupsServiceStub: SinonStubbedInstance<GroupsService>;

    beforeEach(() => {
      groupsServiceStub = sinon.createStubInstance<GroupsService>(GroupsService);

      wrapper = shallowMount<GroupsClass>(GroupsDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { groupsService: () => groupsServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundGroups = { id: 123 };
        groupsServiceStub.find.resolves(foundGroups);

        // WHEN
        comp.retrieveGroups(123);
        await comp.$nextTick();

        // THEN
        expect(comp.groups).toBe(foundGroups);
      });
    });
  });
});

/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ProductGroupsDetailComponent from '@/entities/product-groups/product-groups-details.vue';
import ProductGroupsClass from '@/entities/product-groups/product-groups-details.component';
import ProductGroupsService from '@/entities/product-groups/product-groups.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('ProductGroups Management Detail Component', () => {
    let wrapper: Wrapper<ProductGroupsClass>;
    let comp: ProductGroupsClass;
    let productGroupsServiceStub: SinonStubbedInstance<ProductGroupsService>;

    beforeEach(() => {
      productGroupsServiceStub = sinon.createStubInstance<ProductGroupsService>(ProductGroupsService);

      wrapper = shallowMount<ProductGroupsClass>(ProductGroupsDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { productGroupsService: () => productGroupsServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundProductGroups = { id: 123 };
        productGroupsServiceStub.find.resolves(foundProductGroups);

        // WHEN
        comp.retrieveProductGroups(123);
        await comp.$nextTick();

        // THEN
        expect(comp.productGroups).toBe(foundProductGroups);
      });
    });
  });
});

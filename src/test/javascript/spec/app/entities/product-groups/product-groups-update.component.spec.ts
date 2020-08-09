/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import ProductGroupsUpdateComponent from '@/entities/product-groups/product-groups-update.vue';
import ProductGroupsClass from '@/entities/product-groups/product-groups-update.component';
import ProductGroupsService from '@/entities/product-groups/product-groups.service';

import CompanyService from '@/entities/company/company.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('ProductGroups Management Update Component', () => {
    let wrapper: Wrapper<ProductGroupsClass>;
    let comp: ProductGroupsClass;
    let productGroupsServiceStub: SinonStubbedInstance<ProductGroupsService>;

    beforeEach(() => {
      productGroupsServiceStub = sinon.createStubInstance<ProductGroupsService>(ProductGroupsService);

      wrapper = shallowMount<ProductGroupsClass>(ProductGroupsUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          productGroupsService: () => productGroupsServiceStub,

          companyService: () => new CompanyService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.productGroups = entity;
        productGroupsServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(productGroupsServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.productGroups = entity;
        productGroupsServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(productGroupsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});

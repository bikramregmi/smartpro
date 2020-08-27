/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import InventoryVoucherUpdateComponent from '@/entities/inventory-voucher/inventory-voucher-update.vue';
import InventoryVoucherClass from '@/entities/inventory-voucher/inventory-voucher-update.component';
import InventoryVoucherService from '@/entities/inventory-voucher/inventory-voucher.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('InventoryVoucher Management Update Component', () => {
    let wrapper: Wrapper<InventoryVoucherClass>;
    let comp: InventoryVoucherClass;
    let inventoryVoucherServiceStub: SinonStubbedInstance<InventoryVoucherService>;

    beforeEach(() => {
      inventoryVoucherServiceStub = sinon.createStubInstance<InventoryVoucherService>(InventoryVoucherService);

      wrapper = shallowMount<InventoryVoucherClass>(InventoryVoucherUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          inventoryVoucherService: () => inventoryVoucherServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.inventoryVoucher = entity;
        inventoryVoucherServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(inventoryVoucherServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.inventoryVoucher = entity;
        inventoryVoucherServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(inventoryVoucherServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});

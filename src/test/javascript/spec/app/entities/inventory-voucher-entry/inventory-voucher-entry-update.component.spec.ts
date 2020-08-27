/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import InventoryVoucherEntryUpdateComponent from '@/entities/inventory-voucher-entry/inventory-voucher-entry-update.vue';
import InventoryVoucherEntryClass from '@/entities/inventory-voucher-entry/inventory-voucher-entry-update.component';
import InventoryVoucherEntryService from '@/entities/inventory-voucher-entry/inventory-voucher-entry.service';

import InventoryVoucherService from '@/entities/inventory-voucher/inventory-voucher.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('InventoryVoucherEntry Management Update Component', () => {
    let wrapper: Wrapper<InventoryVoucherEntryClass>;
    let comp: InventoryVoucherEntryClass;
    let inventoryVoucherEntryServiceStub: SinonStubbedInstance<InventoryVoucherEntryService>;

    beforeEach(() => {
      inventoryVoucherEntryServiceStub = sinon.createStubInstance<InventoryVoucherEntryService>(InventoryVoucherEntryService);

      wrapper = shallowMount<InventoryVoucherEntryClass>(InventoryVoucherEntryUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          inventoryVoucherEntryService: () => inventoryVoucherEntryServiceStub,

          inventoryVoucherService: () => new InventoryVoucherService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.inventoryVoucherEntry = entity;
        inventoryVoucherEntryServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(inventoryVoucherEntryServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.inventoryVoucherEntry = entity;
        inventoryVoucherEntryServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(inventoryVoucherEntryServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});

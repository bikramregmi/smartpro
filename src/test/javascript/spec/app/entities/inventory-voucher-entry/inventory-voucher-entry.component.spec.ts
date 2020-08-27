/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import InventoryVoucherEntryComponent from '@/entities/inventory-voucher-entry/inventory-voucher-entry.vue';
import InventoryVoucherEntryClass from '@/entities/inventory-voucher-entry/inventory-voucher-entry.component';
import InventoryVoucherEntryService from '@/entities/inventory-voucher-entry/inventory-voucher-entry.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-alert', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('InventoryVoucherEntry Management Component', () => {
    let wrapper: Wrapper<InventoryVoucherEntryClass>;
    let comp: InventoryVoucherEntryClass;
    let inventoryVoucherEntryServiceStub: SinonStubbedInstance<InventoryVoucherEntryService>;

    beforeEach(() => {
      inventoryVoucherEntryServiceStub = sinon.createStubInstance<InventoryVoucherEntryService>(InventoryVoucherEntryService);
      inventoryVoucherEntryServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<InventoryVoucherEntryClass>(InventoryVoucherEntryComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          inventoryVoucherEntryService: () => inventoryVoucherEntryServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      inventoryVoucherEntryServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllInventoryVoucherEntrys();
      await comp.$nextTick();

      // THEN
      expect(inventoryVoucherEntryServiceStub.retrieve.called).toBeTruthy();
      expect(comp.inventoryVoucherEntries[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      inventoryVoucherEntryServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeInventoryVoucherEntry();
      await comp.$nextTick();

      // THEN
      expect(inventoryVoucherEntryServiceStub.delete.called).toBeTruthy();
      expect(inventoryVoucherEntryServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
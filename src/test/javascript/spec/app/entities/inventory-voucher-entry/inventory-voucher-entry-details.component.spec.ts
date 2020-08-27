/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import InventoryVoucherEntryDetailComponent from '@/entities/inventory-voucher-entry/inventory-voucher-entry-details.vue';
import InventoryVoucherEntryClass from '@/entities/inventory-voucher-entry/inventory-voucher-entry-details.component';
import InventoryVoucherEntryService from '@/entities/inventory-voucher-entry/inventory-voucher-entry.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('InventoryVoucherEntry Management Detail Component', () => {
    let wrapper: Wrapper<InventoryVoucherEntryClass>;
    let comp: InventoryVoucherEntryClass;
    let inventoryVoucherEntryServiceStub: SinonStubbedInstance<InventoryVoucherEntryService>;

    beforeEach(() => {
      inventoryVoucherEntryServiceStub = sinon.createStubInstance<InventoryVoucherEntryService>(InventoryVoucherEntryService);

      wrapper = shallowMount<InventoryVoucherEntryClass>(InventoryVoucherEntryDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { inventoryVoucherEntryService: () => inventoryVoucherEntryServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundInventoryVoucherEntry = { id: 123 };
        inventoryVoucherEntryServiceStub.find.resolves(foundInventoryVoucherEntry);

        // WHEN
        comp.retrieveInventoryVoucherEntry(123);
        await comp.$nextTick();

        // THEN
        expect(comp.inventoryVoucherEntry).toBe(foundInventoryVoucherEntry);
      });
    });
  });
});

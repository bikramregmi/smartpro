/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import InventoryVoucherDetailComponent from '@/entities/inventory-voucher/inventory-voucher-details.vue';
import InventoryVoucherClass from '@/entities/inventory-voucher/inventory-voucher-details.component';
import InventoryVoucherService from '@/entities/inventory-voucher/inventory-voucher.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('InventoryVoucher Management Detail Component', () => {
    let wrapper: Wrapper<InventoryVoucherClass>;
    let comp: InventoryVoucherClass;
    let inventoryVoucherServiceStub: SinonStubbedInstance<InventoryVoucherService>;

    beforeEach(() => {
      inventoryVoucherServiceStub = sinon.createStubInstance<InventoryVoucherService>(InventoryVoucherService);

      wrapper = shallowMount<InventoryVoucherClass>(InventoryVoucherDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { inventoryVoucherService: () => inventoryVoucherServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundInventoryVoucher = { id: 123 };
        inventoryVoucherServiceStub.find.resolves(foundInventoryVoucher);

        // WHEN
        comp.retrieveInventoryVoucher(123);
        await comp.$nextTick();

        // THEN
        expect(comp.inventoryVoucher).toBe(foundInventoryVoucher);
      });
    });
  });
});

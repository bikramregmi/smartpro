/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import SalesVoucherTypeDetailComponent from '@/entities/sales-voucher-type/sales-voucher-type-details.vue';
import SalesVoucherTypeClass from '@/entities/sales-voucher-type/sales-voucher-type-details.component';
import SalesVoucherTypeService from '@/entities/sales-voucher-type/sales-voucher-type.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('SalesVoucherType Management Detail Component', () => {
    let wrapper: Wrapper<SalesVoucherTypeClass>;
    let comp: SalesVoucherTypeClass;
    let salesVoucherTypeServiceStub: SinonStubbedInstance<SalesVoucherTypeService>;

    beforeEach(() => {
      salesVoucherTypeServiceStub = sinon.createStubInstance<SalesVoucherTypeService>(SalesVoucherTypeService);

      wrapper = shallowMount<SalesVoucherTypeClass>(SalesVoucherTypeDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { salesVoucherTypeService: () => salesVoucherTypeServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundSalesVoucherType = { id: 123 };
        salesVoucherTypeServiceStub.find.resolves(foundSalesVoucherType);

        // WHEN
        comp.retrieveSalesVoucherType(123);
        await comp.$nextTick();

        // THEN
        expect(comp.salesVoucherType).toBe(foundSalesVoucherType);
      });
    });
  });
});

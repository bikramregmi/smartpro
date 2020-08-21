/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import SalesVoucherTypeTotalDetailComponent from '@/entities/sales-voucher-type-total/sales-voucher-type-total-details.vue';
import SalesVoucherTypeTotalClass from '@/entities/sales-voucher-type-total/sales-voucher-type-total-details.component';
import SalesVoucherTypeTotalService from '@/entities/sales-voucher-type-total/sales-voucher-type-total.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('SalesVoucherTypeTotal Management Detail Component', () => {
    let wrapper: Wrapper<SalesVoucherTypeTotalClass>;
    let comp: SalesVoucherTypeTotalClass;
    let salesVoucherTypeTotalServiceStub: SinonStubbedInstance<SalesVoucherTypeTotalService>;

    beforeEach(() => {
      salesVoucherTypeTotalServiceStub = sinon.createStubInstance<SalesVoucherTypeTotalService>(SalesVoucherTypeTotalService);

      wrapper = shallowMount<SalesVoucherTypeTotalClass>(SalesVoucherTypeTotalDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { salesVoucherTypeTotalService: () => salesVoucherTypeTotalServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundSalesVoucherTypeTotal = { id: 123 };
        salesVoucherTypeTotalServiceStub.find.resolves(foundSalesVoucherTypeTotal);

        // WHEN
        comp.retrieveSalesVoucherTypeTotal(123);
        await comp.$nextTick();

        // THEN
        expect(comp.salesVoucherTypeTotal).toBe(foundSalesVoucherTypeTotal);
      });
    });
  });
});

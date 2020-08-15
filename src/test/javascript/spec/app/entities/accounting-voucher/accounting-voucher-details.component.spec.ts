/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import AccountingVoucherDetailComponent from '@/entities/accounting-voucher/accounting-voucher-details.vue';
import AccountingVoucherClass from '@/entities/accounting-voucher/accounting-voucher-details.component';
import AccountingVoucherService from '@/entities/accounting-voucher/accounting-voucher.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('AccountingVoucher Management Detail Component', () => {
    let wrapper: Wrapper<AccountingVoucherClass>;
    let comp: AccountingVoucherClass;
    let accountingVoucherServiceStub: SinonStubbedInstance<AccountingVoucherService>;

    beforeEach(() => {
      accountingVoucherServiceStub = sinon.createStubInstance<AccountingVoucherService>(AccountingVoucherService);

      wrapper = shallowMount<AccountingVoucherClass>(AccountingVoucherDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { accountingVoucherService: () => accountingVoucherServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundAccountingVoucher = { id: 123 };
        accountingVoucherServiceStub.find.resolves(foundAccountingVoucher);

        // WHEN
        comp.retrieveAccountingVoucher(123);
        await comp.$nextTick();

        // THEN
        expect(comp.accountingVoucher).toBe(foundAccountingVoucher);
      });
    });
  });
});

/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import AccountingVoucherUpdateComponent from '@/entities/accounting-voucher/accounting-voucher-update.vue';
import AccountingVoucherClass from '@/entities/accounting-voucher/accounting-voucher-update.component';
import AccountingVoucherService from '@/entities/accounting-voucher/accounting-voucher.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('AccountingVoucher Management Update Component', () => {
    let wrapper: Wrapper<AccountingVoucherClass>;
    let comp: AccountingVoucherClass;
    let accountingVoucherServiceStub: SinonStubbedInstance<AccountingVoucherService>;

    beforeEach(() => {
      accountingVoucherServiceStub = sinon.createStubInstance<AccountingVoucherService>(AccountingVoucherService);

      wrapper = shallowMount<AccountingVoucherClass>(AccountingVoucherUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          accountingVoucherService: () => accountingVoucherServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.accountingVoucher = entity;
        accountingVoucherServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(accountingVoucherServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.accountingVoucher = entity;
        accountingVoucherServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(accountingVoucherServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});

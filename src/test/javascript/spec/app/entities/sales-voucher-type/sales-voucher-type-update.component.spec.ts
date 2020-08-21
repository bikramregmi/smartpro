/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import SalesVoucherTypeUpdateComponent from '@/entities/sales-voucher-type/sales-voucher-type-update.vue';
import SalesVoucherTypeClass from '@/entities/sales-voucher-type/sales-voucher-type-update.component';
import SalesVoucherTypeService from '@/entities/sales-voucher-type/sales-voucher-type.service';

import AccountingVoucherService from '@/entities/accounting-voucher/accounting-voucher.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('SalesVoucherType Management Update Component', () => {
    let wrapper: Wrapper<SalesVoucherTypeClass>;
    let comp: SalesVoucherTypeClass;
    let salesVoucherTypeServiceStub: SinonStubbedInstance<SalesVoucherTypeService>;

    beforeEach(() => {
      salesVoucherTypeServiceStub = sinon.createStubInstance<SalesVoucherTypeService>(SalesVoucherTypeService);

      wrapper = shallowMount<SalesVoucherTypeClass>(SalesVoucherTypeUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          salesVoucherTypeService: () => salesVoucherTypeServiceStub,

          accountingVoucherService: () => new AccountingVoucherService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.salesVoucherType = entity;
        salesVoucherTypeServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(salesVoucherTypeServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.salesVoucherType = entity;
        salesVoucherTypeServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(salesVoucherTypeServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});

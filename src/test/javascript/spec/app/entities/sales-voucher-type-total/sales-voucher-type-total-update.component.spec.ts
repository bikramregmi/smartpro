/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import SalesVoucherTypeTotalUpdateComponent from '@/entities/sales-voucher-type-total/sales-voucher-type-total-update.vue';
import SalesVoucherTypeTotalClass from '@/entities/sales-voucher-type-total/sales-voucher-type-total-update.component';
import SalesVoucherTypeTotalService from '@/entities/sales-voucher-type-total/sales-voucher-type-total.service';

import SalesVoucherTypeService from '@/entities/sales-voucher-type/sales-voucher-type.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('SalesVoucherTypeTotal Management Update Component', () => {
    let wrapper: Wrapper<SalesVoucherTypeTotalClass>;
    let comp: SalesVoucherTypeTotalClass;
    let salesVoucherTypeTotalServiceStub: SinonStubbedInstance<SalesVoucherTypeTotalService>;

    beforeEach(() => {
      salesVoucherTypeTotalServiceStub = sinon.createStubInstance<SalesVoucherTypeTotalService>(SalesVoucherTypeTotalService);

      wrapper = shallowMount<SalesVoucherTypeTotalClass>(SalesVoucherTypeTotalUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          salesVoucherTypeTotalService: () => salesVoucherTypeTotalServiceStub,

          salesVoucherTypeService: () => new SalesVoucherTypeService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.salesVoucherTypeTotal = entity;
        salesVoucherTypeTotalServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(salesVoucherTypeTotalServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.salesVoucherTypeTotal = entity;
        salesVoucherTypeTotalServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(salesVoucherTypeTotalServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});

/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import UnitOfMeasureUpdateComponent from '@/entities/unit-of-measure/unit-of-measure-update.vue';
import UnitOfMeasureClass from '@/entities/unit-of-measure/unit-of-measure-update.component';
import UnitOfMeasureService from '@/entities/unit-of-measure/unit-of-measure.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('UnitOfMeasure Management Update Component', () => {
    let wrapper: Wrapper<UnitOfMeasureClass>;
    let comp: UnitOfMeasureClass;
    let unitOfMeasureServiceStub: SinonStubbedInstance<UnitOfMeasureService>;

    beforeEach(() => {
      unitOfMeasureServiceStub = sinon.createStubInstance<UnitOfMeasureService>(UnitOfMeasureService);

      wrapper = shallowMount<UnitOfMeasureClass>(UnitOfMeasureUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          unitOfMeasureService: () => unitOfMeasureServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.unitOfMeasure = entity;
        unitOfMeasureServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(unitOfMeasureServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.unitOfMeasure = entity;
        unitOfMeasureServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(unitOfMeasureServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});

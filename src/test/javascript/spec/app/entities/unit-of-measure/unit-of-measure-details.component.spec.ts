/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import UnitOfMeasureDetailComponent from '@/entities/unit-of-measure/unit-of-measure-details.vue';
import UnitOfMeasureClass from '@/entities/unit-of-measure/unit-of-measure-details.component';
import UnitOfMeasureService from '@/entities/unit-of-measure/unit-of-measure.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('UnitOfMeasure Management Detail Component', () => {
    let wrapper: Wrapper<UnitOfMeasureClass>;
    let comp: UnitOfMeasureClass;
    let unitOfMeasureServiceStub: SinonStubbedInstance<UnitOfMeasureService>;

    beforeEach(() => {
      unitOfMeasureServiceStub = sinon.createStubInstance<UnitOfMeasureService>(UnitOfMeasureService);

      wrapper = shallowMount<UnitOfMeasureClass>(UnitOfMeasureDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { unitOfMeasureService: () => unitOfMeasureServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundUnitOfMeasure = { id: 123 };
        unitOfMeasureServiceStub.find.resolves(foundUnitOfMeasure);

        // WHEN
        comp.retrieveUnitOfMeasure(123);
        await comp.$nextTick();

        // THEN
        expect(comp.unitOfMeasure).toBe(foundUnitOfMeasure);
      });
    });
  });
});

/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import AttendanceUpdateComponent from '@/entities/attendance/attendance-update.vue';
import AttendanceClass from '@/entities/attendance/attendance-update.component';
import AttendanceService from '@/entities/attendance/attendance.service';

import EmployeeService from '@/entities/employee/employee.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Attendance Management Update Component', () => {
    let wrapper: Wrapper<AttendanceClass>;
    let comp: AttendanceClass;
    let attendanceServiceStub: SinonStubbedInstance<AttendanceService>;

    beforeEach(() => {
      attendanceServiceStub = sinon.createStubInstance<AttendanceService>(AttendanceService);

      wrapper = shallowMount<AttendanceClass>(AttendanceUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          attendanceService: () => attendanceServiceStub,

          employeeService: () => new EmployeeService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.attendance = entity;
        attendanceServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(attendanceServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.attendance = entity;
        attendanceServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(attendanceServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});

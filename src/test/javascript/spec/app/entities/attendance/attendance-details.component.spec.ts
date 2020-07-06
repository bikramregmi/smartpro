/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import AttendanceDetailComponent from '@/entities/attendance/attendance-details.vue';
import AttendanceClass from '@/entities/attendance/attendance-details.component';
import AttendanceService from '@/entities/attendance/attendance.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Attendance Management Detail Component', () => {
    let wrapper: Wrapper<AttendanceClass>;
    let comp: AttendanceClass;
    let attendanceServiceStub: SinonStubbedInstance<AttendanceService>;

    beforeEach(() => {
      attendanceServiceStub = sinon.createStubInstance<AttendanceService>(AttendanceService);

      wrapper = shallowMount<AttendanceClass>(AttendanceDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { attendanceService: () => attendanceServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundAttendance = { id: 123 };
        attendanceServiceStub.find.resolves(foundAttendance);

        // WHEN
        comp.retrieveAttendance(123);
        await comp.$nextTick();

        // THEN
        expect(comp.attendance).toBe(foundAttendance);
      });
    });
  });
});

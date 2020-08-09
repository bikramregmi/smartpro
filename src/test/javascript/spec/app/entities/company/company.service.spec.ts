/* tslint:disable max-line-length */
import axios from 'axios';
import { format } from 'date-fns';

import * as config from '@/shared/config/config';
import { DATE_FORMAT } from '@/shared/date/filters';
import CompanyService from '@/entities/company/company.service';
import { Company } from '@/shared/model/company.model';

const mockedAxios: any = axios;
const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

jest.mock('axios', () => ({
  get: jest.fn(),
  post: jest.fn(),
  put: jest.fn(),
  delete: jest.fn(),
}));

describe('Service Tests', () => {
  describe('Company Service', () => {
    let service: CompanyService;
    let elemDefault;
    let currentDate: Date;
    beforeEach(() => {
      service = new CompanyService();
      currentDate = new Date();

      elemDefault = new Company(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            fy: format(currentDate, DATE_FORMAT),
            bookDate: format(currentDate, DATE_FORMAT),
          },
          elemDefault
        );
        mockedAxios.get.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        mockedAxios.get.mockReturnValue(Promise.reject(error));
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a Company', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            fy: format(currentDate, DATE_FORMAT),
            bookDate: format(currentDate, DATE_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fy: currentDate,
            bookDate: currentDate,
          },
          returnedFromService
        );

        mockedAxios.post.mockReturnValue(Promise.resolve({ data: returnedFromService }));
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Company', async () => {
        mockedAxios.post.mockReturnValue(Promise.reject(error));

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Company', async () => {
        const returnedFromService = Object.assign(
          {
            companyName: 'BBBBBB',
            address: 'BBBBBB',
            email: 'BBBBBB',
            fy: format(currentDate, DATE_FORMAT),
            bookDate: format(currentDate, DATE_FORMAT),
            currencyString: 'BBBBBB',
            currencySymbol: 'BBBBBB',
            currencySubString: 'BBBBBB',
            dealerType: 'BBBBBB',
            taxRate: 1,
            type: 'BBBBBB',
            extraField: 'BBBBBB',
            extrafield1: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fy: currentDate,
            bookDate: currentDate,
          },
          returnedFromService
        );
        mockedAxios.put.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Company', async () => {
        mockedAxios.put.mockReturnValue(Promise.reject(error));

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Company', async () => {
        const returnedFromService = Object.assign(
          {
            companyName: 'BBBBBB',
            address: 'BBBBBB',
            email: 'BBBBBB',
            fy: format(currentDate, DATE_FORMAT),
            bookDate: format(currentDate, DATE_FORMAT),
            currencyString: 'BBBBBB',
            currencySymbol: 'BBBBBB',
            currencySubString: 'BBBBBB',
            dealerType: 'BBBBBB',
            taxRate: 1,
            type: 'BBBBBB',
            extraField: 'BBBBBB',
            extrafield1: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fy: currentDate,
            bookDate: currentDate,
          },
          returnedFromService
        );
        mockedAxios.get.mockReturnValue(Promise.resolve([returnedFromService]));
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Company', async () => {
        mockedAxios.get.mockReturnValue(Promise.reject(error));

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Company', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Company', async () => {
        mockedAxios.delete.mockReturnValue(Promise.reject(error));

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});

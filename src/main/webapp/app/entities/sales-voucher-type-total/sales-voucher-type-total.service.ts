import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { ISalesVoucherTypeTotal } from '@/shared/model/sales-voucher-type-total.model';

const baseApiUrl = 'api/sales-voucher-type-totals';

export default class SalesVoucherTypeTotalService {
  public find(id: number): Promise<ISalesVoucherTypeTotal> {
    return new Promise<ISalesVoucherTypeTotal>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(paginationQuery?: any): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl + `?${buildPaginationQueryOpts(paginationQuery)}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public delete(id: number): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .delete(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public create(entity: ISalesVoucherTypeTotal): Promise<ISalesVoucherTypeTotal> {
    return new Promise<ISalesVoucherTypeTotal>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public update(entity: ISalesVoucherTypeTotal): Promise<ISalesVoucherTypeTotal> {
    return new Promise<ISalesVoucherTypeTotal>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}

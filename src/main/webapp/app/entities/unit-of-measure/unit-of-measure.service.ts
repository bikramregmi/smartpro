import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IUnitOfMeasure } from '@/shared/model/unit-of-measure.model';

const baseApiUrl = 'api/unit-of-measures';

export default class UnitOfMeasureService {
  public find(id: number): Promise<IUnitOfMeasure> {
    return new Promise<IUnitOfMeasure>((resolve, reject) => {
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

  public create(entity: IUnitOfMeasure): Promise<IUnitOfMeasure> {
    return new Promise<IUnitOfMeasure>((resolve, reject) => {
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

  public update(entity: IUnitOfMeasure): Promise<IUnitOfMeasure> {
    return new Promise<IUnitOfMeasure>((resolve, reject) => {
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

import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IEmployeeSalary } from '@/shared/model/employee-salary.model';

const baseApiUrl = 'api/employee-salaries';

export default class EmployeeSalaryService {
  public find(id: number): Promise<IEmployeeSalary> {
    return new Promise<IEmployeeSalary>((resolve, reject) => {
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

  public create(entity: IEmployeeSalary): Promise<IEmployeeSalary> {
    return new Promise<IEmployeeSalary>((resolve, reject) => {
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

  public update(entity: IEmployeeSalary): Promise<IEmployeeSalary> {
    return new Promise<IEmployeeSalary>((resolve, reject) => {
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
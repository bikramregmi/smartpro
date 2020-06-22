<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('smartproApp.employeeSalary.home.title')" id="employee-salary-heading">Employee Salaries</span>
            <router-link :to="{name: 'EmployeeSalaryCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-employee-salary">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('smartproApp.employeeSalary.home.createLabel')">
                    Create a new Employee Salary
                </span>
            </router-link>
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && employeeSalaries && employeeSalaries.length === 0">
            <span v-text="$t('smartproApp.employeeSalary.home.notFound')">No employeeSalaries found</span>
        </div>
        <div class="table-responsive" v-if="employeeSalaries && employeeSalaries.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('basicSalary')"><span v-text="$t('smartproApp.employeeSalary.basicSalary')">Basic Salary</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'basicSalary'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('allowance')"><span v-text="$t('smartproApp.employeeSalary.allowance')">Allowance</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'allowance'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('ot')"><span v-text="$t('smartproApp.employeeSalary.ot')">Ot</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'ot'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('bonus')"><span v-text="$t('smartproApp.employeeSalary.bonus')">Bonus</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bonus'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('description')"><span v-text="$t('smartproApp.employeeSalary.description')">Description</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'description'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('tax')"><span v-text="$t('smartproApp.employeeSalary.tax')">Tax</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'tax'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('pf')"><span v-text="$t('smartproApp.employeeSalary.pf')">Pf</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'pf'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('extra')"><span v-text="$t('smartproApp.employeeSalary.extra')">Extra</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'extra'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="employeeSalary in employeeSalaries"
                    :key="employeeSalary.id">
                    <td>
                        <router-link :to="{name: 'EmployeeSalaryView', params: {employeeSalaryId: employeeSalary.id}}">{{employeeSalary.id}}</router-link>
                    </td>
                    <td>{{employeeSalary.basicSalary}}</td>
                    <td>{{employeeSalary.allowance}}</td>
                    <td>{{employeeSalary.ot}}</td>
                    <td>{{employeeSalary.bonus}}</td>
                    <td>{{employeeSalary.description}}</td>
                    <td>{{employeeSalary.tax}}</td>
                    <td>{{employeeSalary.pf}}</td>
                    <td>{{employeeSalary.extra}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'EmployeeSalaryView', params: {employeeSalaryId: employeeSalary.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'EmployeeSalaryEdit', params: {employeeSalaryId: employeeSalary.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(employeeSalary)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="smartproApp.employeeSalary.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-employeeSalary-heading" v-text="$t('smartproApp.employeeSalary.delete.question', {'id': removeId})">Are you sure you want to delete this Employee Salary?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-employeeSalary" v-text="$t('entity.action.delete')" v-on:click="removeEmployeeSalary()">Delete</button>
            </div>
        </b-modal>
        <div v-show="employeeSalaries && employeeSalaries.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./employee-salary.component.ts">
</script>

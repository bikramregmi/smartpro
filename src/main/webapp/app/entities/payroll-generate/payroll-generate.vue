<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('smartproApp.payrollGenerate.home.title')" id="payroll-generate-heading">Payroll Generates</span>
            <router-link :to="{name: 'PayrollGenerateCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-payroll-generate">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('smartproApp.payrollGenerate.home.createLabel')">
                    Create a new Payroll Generate
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
        <div class="alert alert-warning" v-if="!isFetching && payrollGenerates && payrollGenerates.length === 0">
            <span v-text="$t('smartproApp.payrollGenerate.home.notFound')">No payrollGenerates found</span>
        </div>
        <div class="table-responsive" v-if="payrollGenerates && payrollGenerates.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('salaryMonth')"><span v-text="$t('smartproApp.payrollGenerate.salaryMonth')">Salary Month</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'salaryMonth'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('description')"><span v-text="$t('smartproApp.payrollGenerate.description')">Description</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'description'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('tax')"><span v-text="$t('smartproApp.payrollGenerate.tax')">Tax</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'tax'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('pf')"><span v-text="$t('smartproApp.payrollGenerate.pf')">Pf</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'pf'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('employeeId')"><span v-text="$t('smartproApp.payrollGenerate.employee')">Employee</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'employeeId'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('employeeSalaryId')"><span v-text="$t('smartproApp.payrollGenerate.employeeSalary')">Employee Salary</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'employeeSalaryId'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="payrollGenerate in payrollGenerates"
                    :key="payrollGenerate.id">
                    <td>
                        <router-link :to="{name: 'PayrollGenerateView', params: {payrollGenerateId: payrollGenerate.id}}">{{payrollGenerate.id}}</router-link>
                    </td>
                    <td>{{payrollGenerate.salaryMonth}}</td>
                    <td>{{payrollGenerate.description}}</td>
                    <td>{{payrollGenerate.tax}}</td>
                    <td>{{payrollGenerate.pf}}</td>
                    <td>
                        <div v-if="payrollGenerate.employeeId">
                            <router-link :to="{name: 'EmployeeView', params: {employeeId: payrollGenerate.employeeId}}">{{payrollGenerate.employeeId}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="payrollGenerate.employeeSalaryId">
                            <router-link :to="{name: 'EmployeeSalaryView', params: {employeeSalaryId: payrollGenerate.employeeSalaryId}}">{{payrollGenerate.employeeSalaryId}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'PayrollGenerateView', params: {payrollGenerateId: payrollGenerate.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'PayrollGenerateEdit', params: {payrollGenerateId: payrollGenerate.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(payrollGenerate)"
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
            <span slot="modal-title"><span id="smartproApp.payrollGenerate.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-payrollGenerate-heading" v-text="$t('smartproApp.payrollGenerate.delete.question', {'id': removeId})">Are you sure you want to delete this Payroll Generate?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-payrollGenerate" v-text="$t('entity.action.delete')" v-on:click="removePayrollGenerate()">Delete</button>
            </div>
        </b-modal>
        <div v-show="payrollGenerates && payrollGenerates.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./payroll-generate.component.ts">
</script>

<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="payrollGenerate">
                <h2 class="jh-entity-heading"><span v-text="$t('smartproApp.payrollGenerate.detail.title')">PayrollGenerate</span> {{payrollGenerate.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('smartproApp.payrollGenerate.salaryMonth')">Salary Month</span>
                    </dt>
                    <dd>
                        <span>{{payrollGenerate.salaryMonth}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.payrollGenerate.description')">Description</span>
                    </dt>
                    <dd>
                        <span>{{payrollGenerate.description}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.payrollGenerate.tax')">Tax</span>
                    </dt>
                    <dd>
                        <span>{{payrollGenerate.tax}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.payrollGenerate.pf')">Pf</span>
                    </dt>
                    <dd>
                        <span>{{payrollGenerate.pf}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.payrollGenerate.employee')">Employee</span>
                    </dt>
                    <dd>
                        <div v-if="payrollGenerate.employeeId">
                            <router-link :to="{name: 'EmployeeView', params: {employeeId: payrollGenerate.employeeId}}">{{payrollGenerate.employeeId}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.payrollGenerate.employeeSalary')">Employee Salary</span>
                    </dt>
                    <dd>
                        <div v-if="payrollGenerate.employeeSalaryId">
                            <router-link :to="{name: 'EmployeeSalaryView', params: {employeeSalaryId: payrollGenerate.employeeSalaryId}}">{{payrollGenerate.employeeSalaryId}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="payrollGenerate.id" :to="{name: 'PayrollGenerateEdit', params: {payrollGenerateId: payrollGenerate.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./payroll-generate-details.component.ts">
</script>

<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="employeeSalary">
                <h2 class="jh-entity-heading"><span v-text="$t('smartproApp.employeeSalary.detail.title')">EmployeeSalary</span> {{employeeSalary.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('smartproApp.employeeSalary.basicSalary')">Basic Salary</span>
                    </dt>
                    <dd>
                        <span>{{employeeSalary.basicSalary}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.employeeSalary.allowance')">Allowance</span>
                    </dt>
                    <dd>
                        <span>{{employeeSalary.allowance}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.employeeSalary.ot')">Ot</span>
                    </dt>
                    <dd>
                        <span>{{employeeSalary.ot}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.employeeSalary.bonus')">Bonus</span>
                    </dt>
                    <dd>
                        <span>{{employeeSalary.bonus}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.employeeSalary.description')">Description</span>
                    </dt>
                    <dd>
                        <span>{{employeeSalary.description}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.employeeSalary.tax')">Tax</span>
                    </dt>
                    <dd>
                        <span>{{employeeSalary.tax}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.employeeSalary.pf')">Pf</span>
                    </dt>
                    <dd>
                        <span>{{employeeSalary.pf}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.employeeSalary.extra')">Extra</span>
                    </dt>
                    <dd>
                        <span>{{employeeSalary.extra}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="employeeSalary.id" :to="{name: 'EmployeeSalaryEdit', params: {employeeSalaryId: employeeSalary.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./employee-salary-details.component.ts">
</script>

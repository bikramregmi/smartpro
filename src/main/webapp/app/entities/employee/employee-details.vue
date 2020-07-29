<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="employee">
                <h2 class="jh-entity-heading"><span v-text="$t('smartproApp.employee.detail.title')">Employee</span> {{employee.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('smartproApp.employee.fullName')">Full Name</span>
                    </dt>
                    <dd>
                        <span>{{employee.fullName}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.employee.email')">Email</span>
                    </dt>
                    <dd>
                        <span>{{employee.email}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.employee.eCode')">E Code</span>
                    </dt>
                    <dd>
                        <span>{{employee.eCode}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.employee.isActive')">Is Active</span>
                    </dt>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="employee.id" :to="{name: 'EmployeeEdit', params: {employeeId: employee.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./employee-details.component.ts">
</script>

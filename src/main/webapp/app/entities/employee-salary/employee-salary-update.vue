<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="smartproApp.employeeSalary.home.createOrEditLabel" v-text="$t('smartproApp.employeeSalary.home.createOrEditLabel')">Create or edit a EmployeeSalary</h2>
                <div>
                    <div class="form-group" v-if="employeeSalary.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="employeeSalary.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employeeSalary.basicSalary')" for="employee-salary-basicSalary">Basic Salary</label>
                        <input type="text" class="form-control" name="basicSalary" id="employee-salary-basicSalary"
                            :class="{'valid': !$v.employeeSalary.basicSalary.$invalid, 'invalid': $v.employeeSalary.basicSalary.$invalid }" v-model="$v.employeeSalary.basicSalary.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employeeSalary.allowance')" for="employee-salary-allowance">Allowance</label>
                        <input type="text" class="form-control" name="allowance" id="employee-salary-allowance"
                            :class="{'valid': !$v.employeeSalary.allowance.$invalid, 'invalid': $v.employeeSalary.allowance.$invalid }" v-model="$v.employeeSalary.allowance.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employeeSalary.ot')" for="employee-salary-ot">Ot</label>
                        <input type="text" class="form-control" name="ot" id="employee-salary-ot"
                            :class="{'valid': !$v.employeeSalary.ot.$invalid, 'invalid': $v.employeeSalary.ot.$invalid }" v-model="$v.employeeSalary.ot.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employeeSalary.bonus')" for="employee-salary-bonus">Bonus</label>
                        <input type="text" class="form-control" name="bonus" id="employee-salary-bonus"
                            :class="{'valid': !$v.employeeSalary.bonus.$invalid, 'invalid': $v.employeeSalary.bonus.$invalid }" v-model="$v.employeeSalary.bonus.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employeeSalary.description')" for="employee-salary-description">Description</label>
                        <input type="text" class="form-control" name="description" id="employee-salary-description"
                            :class="{'valid': !$v.employeeSalary.description.$invalid, 'invalid': $v.employeeSalary.description.$invalid }" v-model="$v.employeeSalary.description.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employeeSalary.tax')" for="employee-salary-tax">Tax</label>
                        <input type="text" class="form-control" name="tax" id="employee-salary-tax"
                            :class="{'valid': !$v.employeeSalary.tax.$invalid, 'invalid': $v.employeeSalary.tax.$invalid }" v-model="$v.employeeSalary.tax.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employeeSalary.pf')" for="employee-salary-pf">Pf</label>
                        <input type="text" class="form-control" name="pf" id="employee-salary-pf"
                            :class="{'valid': !$v.employeeSalary.pf.$invalid, 'invalid': $v.employeeSalary.pf.$invalid }" v-model="$v.employeeSalary.pf.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employeeSalary.extra')" for="employee-salary-extra">Extra</label>
                        <input type="text" class="form-control" name="extra" id="employee-salary-extra"
                            :class="{'valid': !$v.employeeSalary.extra.$invalid, 'invalid': $v.employeeSalary.extra.$invalid }" v-model="$v.employeeSalary.extra.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.employeeSalary.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./employee-salary-update.component.ts">
</script>

<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="smartproApp.attendance.home.createOrEditLabel" v-text="$t('smartproApp.attendance.home.createOrEditLabel')">Create or edit a Attendance</h2>
                <div>
                    <div class="form-group" v-if="attendance.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="attendance.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.attendance.checkIn')" for="attendance-checkIn">Check In</label>
                        <b-input-group class="mb-3">
                            <b-input-group-prepend>
                                <b-form-datepicker
                                    aria-controls="attendance-checkIn"
                                    v-model="$v.attendance.checkIn.$model"
                                    name="checkIn"
                                    class="form-control"
                                    :locale="currentLanguage"
                                    button-only
                                    today-button
                                    reset-button
                                    close-button
                                >
                                </b-form-datepicker>
                            </b-input-group-prepend>
                            <b-form-input id="attendance-checkIn" type="text" class="form-control" name="checkIn"  :class="{'valid': !$v.attendance.checkIn.$invalid, 'invalid': $v.attendance.checkIn.$invalid }"
                            v-model="$v.attendance.checkIn.$model"  required />
                        </b-input-group>
                        <div v-if="$v.attendance.checkIn.$anyDirty && $v.attendance.checkIn.$invalid">
                            <small class="form-text text-danger" v-if="!$v.attendance.checkIn.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.attendance.checkOut')" for="attendance-checkOut">Check Out</label>
                        <b-input-group class="mb-3">
                            <b-input-group-prepend>
                                <b-form-datepicker
                                    aria-controls="attendance-checkOut"
                                    v-model="$v.attendance.checkOut.$model"
                                    name="checkOut"
                                    class="form-control"
                                    :locale="currentLanguage"
                                    button-only
                                    today-button
                                    reset-button
                                    close-button
                                >
                                </b-form-datepicker>
                            </b-input-group-prepend>
                            <b-form-input id="attendance-checkOut" type="text" class="form-control" name="checkOut"  :class="{'valid': !$v.attendance.checkOut.$invalid, 'invalid': $v.attendance.checkOut.$invalid }"
                            v-model="$v.attendance.checkOut.$model"  />
                        </b-input-group>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.attendance.description')" for="attendance-description">Description</label>
                        <input type="text" class="form-control" name="description" id="attendance-description"
                            :class="{'valid': !$v.attendance.description.$invalid, 'invalid': $v.attendance.description.$invalid }" v-model="$v.attendance.description.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.attendance.employee')" for="attendance-employee">Employee</label>
                        <select class="form-control" id="attendance-employee" name="employee" v-model="attendance.employeeId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="employeeOption.id" v-for="employeeOption in employees" :key="employeeOption.id">{{employeeOption.fullName}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.attendance.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./attendance-update.component.ts">
</script>

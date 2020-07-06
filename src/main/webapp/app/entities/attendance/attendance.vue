<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('smartproApp.attendance.home.title')" id="attendance-heading">Attendances</span>
            <router-link :to="{name: 'AttendanceCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-attendance">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('smartproApp.attendance.home.createLabel')">
                    Create a new Attendance
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
        <div class="alert alert-warning" v-if="!isFetching && attendances && attendances.length === 0">
            <span v-text="$t('smartproApp.attendance.home.notFound')">No attendances found</span>
        </div>
        <div class="table-responsive" v-if="attendances && attendances.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('checkIn')"><span v-text="$t('smartproApp.attendance.checkIn')">Check In</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'checkIn'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('checkOut')"><span v-text="$t('smartproApp.attendance.checkOut')">Check Out</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'checkOut'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('description')"><span v-text="$t('smartproApp.attendance.description')">Description</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'description'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('employeeFullName')"><span v-text="$t('smartproApp.attendance.employee')">Employee</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'employeeFullName'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="attendance in attendances"
                    :key="attendance.id">
                    <td>
                        <router-link :to="{name: 'AttendanceView', params: {attendanceId: attendance.id}}">{{attendance.id}}</router-link>
                    </td>
                    <td>{{attendance.checkIn}}</td>
                    <td>{{attendance.checkOut}}</td>
                    <td>{{attendance.description}}</td>
                    <td>
                        <div v-if="attendance.employeeId">
                            <router-link :to="{name: 'EmployeeView', params: {employeeId: attendance.employeeId}}">{{attendance.employeeFullName}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'AttendanceView', params: {attendanceId: attendance.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'AttendanceEdit', params: {attendanceId: attendance.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(attendance)"
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
            <span slot="modal-title"><span id="smartproApp.attendance.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-attendance-heading" v-text="$t('smartproApp.attendance.delete.question', {'id': removeId})">Are you sure you want to delete this Attendance?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-attendance" v-text="$t('entity.action.delete')" v-on:click="removeAttendance()">Delete</button>
            </div>
        </b-modal>
        <div v-show="attendances && attendances.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./attendance.component.ts">
</script>

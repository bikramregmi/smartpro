<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="attendance">
                <h2 class="jh-entity-heading"><span v-text="$t('smartproApp.attendance.detail.title')">Attendance</span> {{attendance.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('smartproApp.attendance.checkIn')">Check In</span>
                    </dt>
                    <dd>
                        <span>{{attendance.checkIn}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.attendance.checkOut')">Check Out</span>
                    </dt>
                    <dd>
                        <span>{{attendance.checkOut}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.attendance.description')">Description</span>
                    </dt>
                    <dd>
                        <span>{{attendance.description}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.attendance.employee')">Employee</span>
                    </dt>
                    <dd>
                        <div v-if="attendance.employeeId">
                            <router-link :to="{name: 'EmployeeView', params: {employeeId: attendance.employeeId}}">{{attendance.employeeFullName}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="attendance.id" :to="{name: 'AttendanceEdit', params: {attendanceId: attendance.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./attendance-details.component.ts">
</script>

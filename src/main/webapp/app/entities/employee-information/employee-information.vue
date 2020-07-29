<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('smartproApp.employeeInformation.home.title')" id="employee-information-heading">Employee Informations</span>
           <!-- <router-link :to="{name: 'EmployeeInformationCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-employee-information">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('smartproApp.employeeInformation.home.createLabel')">
                    Create a new Employee Information
                </span>
            </router-link>-->
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && employeeInformations && employeeInformations.length === 0">
            <span v-text="$t('smartproApp.employeeInformation.home.notFound')">No employeeInformations found</span>
        </div>
        <div class="table-responsive" v-if="employeeInformations && employeeInformations.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('dob')"><span v-text="$t('smartproApp.employeeInformation.dob')">Dob</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'dob'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('gender')"><span v-text="$t('smartproApp.employeeInformation.gender')">Gender</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'gender'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('isMarried')"><span v-text="$t('smartproApp.employeeInformation.isMarried')">Is Married</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'isMarried'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('bloodGroup')"><span v-text="$t('smartproApp.employeeInformation.bloodGroup')">Blood Group</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bloodGroup'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('parentName')"><span v-text="$t('smartproApp.employeeInformation.parentName')">Parent Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'parentName'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('addressline1')"><span v-text="$t('smartproApp.employeeInformation.addressline1')">Addressline 1</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'addressline1'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('addressline2')"><span v-text="$t('smartproApp.employeeInformation.addressline2')">Addressline 2</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'addressline2'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('phoneNumber')"><span v-text="$t('smartproApp.employeeInformation.phoneNumber')">Phone Number</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'phoneNumber'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('mobileNumber')"><span v-text="$t('smartproApp.employeeInformation.mobileNumber')">Mobile Number</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mobileNumber'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('emergencyContactNumber')"><span v-text="$t('smartproApp.employeeInformation.emergencyContactNumber')">Emergency Contact Number</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'emergencyContactNumber'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('joiningDate')"><span v-text="$t('smartproApp.employeeInformation.joiningDate')">Joining Date</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'joiningDate'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('designation')"><span v-text="$t('smartproApp.employeeInformation.designation')">Designation</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'designation'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('panNumber')"><span v-text="$t('smartproApp.employeeInformation.panNumber')">Pan Number</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'panNumber'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('documentNumber')"><span v-text="$t('smartproApp.employeeInformation.documentNumber')">Document Number</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'documentNumber'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('extraField')"><span v-text="$t('smartproApp.employeeInformation.extraField')">Extra Field</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'extraField'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="employeeInformation in employeeInformations"
                    :key="employeeInformation.id">
                    <td>
                        <router-link :to="{name: 'EmployeeInformationView', params: {employeeInformationId: employeeInformation.id}}">{{employeeInformation.id}}</router-link>
                    </td>
                    <td>{{employeeInformation.dob}}</td>
                    <td>{{employeeInformation.gender}}</td>
                    <td>{{employeeInformation.isMarried}}</td>
                    <td>{{employeeInformation.bloodGroup}}</td>
                    <td>{{employeeInformation.parentName}}</td>
                    <td>{{employeeInformation.addressline1}}</td>
                    <td>{{employeeInformation.addressline2}}</td>
                    <td>{{employeeInformation.phoneNumber}}</td>
                    <td>{{employeeInformation.mobileNumber}}</td>
                    <td>{{employeeInformation.emergencyContactNumber}}</td>
                    <td>{{employeeInformation.joiningDate}}</td>
                    <td>{{employeeInformation.designation}}</td>
                    <td>{{employeeInformation.panNumber}}</td>
                    <td>{{employeeInformation.documentNumber}}</td>
                    <td>{{employeeInformation.extraField}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'EmployeeInformationView', params: {employeeInformationId: employeeInformation.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'EmployeeInformationEdit', params: {employeeInformationId: employeeInformation.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(employeeInformation)"
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
            <span slot="modal-title"><span id="smartproApp.employeeInformation.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-employeeInformation-heading" v-text="$t('smartproApp.employeeInformation.delete.question', {'id': removeId})">Are you sure you want to delete this Employee Information?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-employeeInformation" v-text="$t('entity.action.delete')" v-on:click="removeEmployeeInformation()">Delete</button>
            </div>
        </b-modal>
        <div v-show="employeeInformations && employeeInformations.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./employee-information.component.ts">
</script>

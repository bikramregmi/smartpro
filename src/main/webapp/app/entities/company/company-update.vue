<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="smartproApp.company.home.createOrEditLabel" v-text="$t('smartproApp.company.home.createOrEditLabel')">Create or edit a Company</h2>
                <div>
                    <div class="form-group" v-if="company.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="company.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.company.companyName')" for="company-companyName">Company Name</label>
                        <input type="text" class="form-control" name="companyName" id="company-companyName"
                            :class="{'valid': !$v.company.companyName.$invalid, 'invalid': $v.company.companyName.$invalid }" v-model="$v.company.companyName.$model"  required/>
                        <div v-if="$v.company.companyName.$anyDirty && $v.company.companyName.$invalid">
                            <small class="form-text text-danger" v-if="!$v.company.companyName.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.company.address')" for="company-address">Address</label>
                        <input type="text" class="form-control" name="address" id="company-address"
                            :class="{'valid': !$v.company.address.$invalid, 'invalid': $v.company.address.$invalid }" v-model="$v.company.address.$model"  required/>
                        <div v-if="$v.company.address.$anyDirty && $v.company.address.$invalid">
                            <small class="form-text text-danger" v-if="!$v.company.address.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.company.email')" for="company-email">Email</label>
                        <input type="text" class="form-control" name="email" id="company-email"
                            :class="{'valid': !$v.company.email.$invalid, 'invalid': $v.company.email.$invalid }" v-model="$v.company.email.$model"  required/>
                        <div v-if="$v.company.email.$anyDirty && $v.company.email.$invalid">
                            <small class="form-text text-danger" v-if="!$v.company.email.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.company.fy')" for="company-fy">Fy</label>
                        <b-input-group class="mb-3">
                            <b-input-group-prepend>
                                <b-form-datepicker
                                    aria-controls="company-fy"
                                    v-model="$v.company.fy.$model"
                                    name="fy"
                                    class="form-control"
                                    :locale="currentLanguage"
                                    button-only
                                    today-button
                                    reset-button
                                    close-button
                                >
                                </b-form-datepicker>
                            </b-input-group-prepend>
                            <b-form-input id="company-fy" type="text" class="form-control" name="fy"  :class="{'valid': !$v.company.fy.$invalid, 'invalid': $v.company.fy.$invalid }"
                            v-model="$v.company.fy.$model"  required />
                        </b-input-group>
                        <div v-if="$v.company.fy.$anyDirty && $v.company.fy.$invalid">
                            <small class="form-text text-danger" v-if="!$v.company.fy.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.company.bookDate')" for="company-bookDate">Book Date</label>
                        <b-input-group class="mb-3">
                            <b-input-group-prepend>
                                <b-form-datepicker
                                    aria-controls="company-bookDate"
                                    v-model="$v.company.bookDate.$model"
                                    name="bookDate"
                                    class="form-control"
                                    :locale="currentLanguage"
                                    button-only
                                    today-button
                                    reset-button
                                    close-button
                                >
                                </b-form-datepicker>
                            </b-input-group-prepend>
                            <b-form-input id="company-bookDate" type="text" class="form-control" name="bookDate"  :class="{'valid': !$v.company.bookDate.$invalid, 'invalid': $v.company.bookDate.$invalid }"
                            v-model="$v.company.bookDate.$model"  required />
                        </b-input-group>
                        <div v-if="$v.company.bookDate.$anyDirty && $v.company.bookDate.$invalid">
                            <small class="form-text text-danger" v-if="!$v.company.bookDate.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.company.currencyString')" for="company-currencyString">Currency String</label>
                        <input type="text" class="form-control" name="currencyString" id="company-currencyString"
                            :class="{'valid': !$v.company.currencyString.$invalid, 'invalid': $v.company.currencyString.$invalid }" v-model="$v.company.currencyString.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.company.currencySymbol')" for="company-currencySymbol">Currency Symbol</label>
                        <input type="text" class="form-control" name="currencySymbol" id="company-currencySymbol"
                            :class="{'valid': !$v.company.currencySymbol.$invalid, 'invalid': $v.company.currencySymbol.$invalid }" v-model="$v.company.currencySymbol.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.company.currencySubString')" for="company-currencySubString">Currency Sub String</label>
                        <input type="text" class="form-control" name="currencySubString" id="company-currencySubString"
                            :class="{'valid': !$v.company.currencySubString.$invalid, 'invalid': $v.company.currencySubString.$invalid }" v-model="$v.company.currencySubString.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.company.dealerType')" for="company-dealerType">Dealer Type</label>
                        <input type="text" class="form-control" name="dealerType" id="company-dealerType"
                            :class="{'valid': !$v.company.dealerType.$invalid, 'invalid': $v.company.dealerType.$invalid }" v-model="$v.company.dealerType.$model"  required/>
                        <div v-if="$v.company.dealerType.$anyDirty && $v.company.dealerType.$invalid">
                            <small class="form-text text-danger" v-if="!$v.company.dealerType.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.company.taxRate')" for="company-taxRate">Tax Rate</label>
                        <input type="number" class="form-control" name="taxRate" id="company-taxRate"
                            :class="{'valid': !$v.company.taxRate.$invalid, 'invalid': $v.company.taxRate.$invalid }" v-model.number="$v.company.taxRate.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.company.type')" for="company-type">Type</label>
                        <input type="text" class="form-control" name="type" id="company-type"
                            :class="{'valid': !$v.company.type.$invalid, 'invalid': $v.company.type.$invalid }" v-model="$v.company.type.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.company.extraField')" for="company-extraField">Extra Field</label>
                        <input type="text" class="form-control" name="extraField" id="company-extraField"
                            :class="{'valid': !$v.company.extraField.$invalid, 'invalid': $v.company.extraField.$invalid }" v-model="$v.company.extraField.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.company.extrafield1')" for="company-extrafield1">Extrafield 1</label>
                        <input type="text" class="form-control" name="extrafield1" id="company-extrafield1"
                            :class="{'valid': !$v.company.extrafield1.$invalid, 'invalid': $v.company.extrafield1.$invalid }" v-model="$v.company.extrafield1.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.company.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./company-update.component.ts">
</script>

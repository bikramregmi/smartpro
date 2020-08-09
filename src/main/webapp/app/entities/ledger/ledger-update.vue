<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="smartproApp.ledger.home.createOrEditLabel" v-text="$t('smartproApp.ledger.home.createOrEditLabel')">Create or edit a Ledger</h2>
                <div>
                    <div class="form-group" v-if="ledger.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="ledger.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.ledger.name')" for="ledger-name">Name</label>
                        <input type="text" class="form-control" name="name" id="ledger-name"
                            :class="{'valid': !$v.ledger.name.$invalid, 'invalid': $v.ledger.name.$invalid }" v-model="$v.ledger.name.$model"  required/>
                        <div v-if="$v.ledger.name.$anyDirty && $v.ledger.name.$invalid">
                            <small class="form-text text-danger" v-if="!$v.ledger.name.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.ledger.mailingName')" for="ledger-mailingName">Mailing Name</label>
                        <input type="text" class="form-control" name="mailingName" id="ledger-mailingName"
                            :class="{'valid': !$v.ledger.mailingName.$invalid, 'invalid': $v.ledger.mailingName.$invalid }" v-model="$v.ledger.mailingName.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.ledger.mailingAddress')" for="ledger-mailingAddress">Mailing Address</label>
                        <input type="text" class="form-control" name="mailingAddress" id="ledger-mailingAddress"
                            :class="{'valid': !$v.ledger.mailingAddress.$invalid, 'invalid': $v.ledger.mailingAddress.$invalid }" v-model="$v.ledger.mailingAddress.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.ledger.panNo')" for="ledger-panNo">Pan No</label>
                        <input type="text" class="form-control" name="panNo" id="ledger-panNo"
                            :class="{'valid': !$v.ledger.panNo.$invalid, 'invalid': $v.ledger.panNo.$invalid }" v-model="$v.ledger.panNo.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.ledger.extraField1')" for="ledger-extraField1">Extra Field 1</label>
                        <input type="checkbox" class="form-check" name="extraField1" id="ledger-extraField1"
                            :class="{'valid': !$v.ledger.extraField1.$invalid, 'invalid': $v.ledger.extraField1.$invalid }" v-model="$v.ledger.extraField1.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.ledger.extraField2')" for="ledger-extraField2">Extra Field 2</label>
                        <input type="checkbox" class="form-check" name="extraField2" id="ledger-extraField2"
                            :class="{'valid': !$v.ledger.extraField2.$invalid, 'invalid': $v.ledger.extraField2.$invalid }" v-model="$v.ledger.extraField2.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.ledger.extraField3')" for="ledger-extraField3">Extra Field 3</label>
                        <input type="checkbox" class="form-check" name="extraField3" id="ledger-extraField3"
                            :class="{'valid': !$v.ledger.extraField3.$invalid, 'invalid': $v.ledger.extraField3.$invalid }" v-model="$v.ledger.extraField3.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.ledger.extraField4')" for="ledger-extraField4">Extra Field 4</label>
                        <input type="text" class="form-control" name="extraField4" id="ledger-extraField4"
                            :class="{'valid': !$v.ledger.extraField4.$invalid, 'invalid': $v.ledger.extraField4.$invalid }" v-model="$v.ledger.extraField4.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.ledger.extraField5')" for="ledger-extraField5">Extra Field 5</label>
                        <input type="text" class="form-control" name="extraField5" id="ledger-extraField5"
                            :class="{'valid': !$v.ledger.extraField5.$invalid, 'invalid': $v.ledger.extraField5.$invalid }" v-model="$v.ledger.extraField5.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.ledger.ledger')" for="ledger-ledger">Ledger</label>
                        <select class="form-control" id="ledger-ledger" name="ledger" v-model="ledger.ledgerId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="ledgerOption.id" v-for="ledgerOption in ledgers" :key="ledgerOption.id">{{ledgerOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.ledger.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./ledger-update.component.ts">
</script>

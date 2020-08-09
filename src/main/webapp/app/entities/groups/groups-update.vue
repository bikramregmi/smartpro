<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="smartproApp.groups.home.createOrEditLabel" v-text="$t('smartproApp.groups.home.createOrEditLabel')">Create or edit a Groups</h2>
                <div>
                    <div class="form-group" v-if="groups.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="groups.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.groups.name')" for="groups-name">Name</label>
                        <input type="text" class="form-control" name="name" id="groups-name"
                            :class="{'valid': !$v.groups.name.$invalid, 'invalid': $v.groups.name.$invalid }" v-model="$v.groups.name.$model"  required/>
                        <div v-if="$v.groups.name.$anyDirty && $v.groups.name.$invalid">
                            <small class="form-text text-danger" v-if="!$v.groups.name.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.groups.group')" for="groups-group">Group</label>
                        <input type="text" class="form-control" name="group" id="groups-group"
                            :class="{'valid': !$v.groups.group.$invalid, 'invalid': $v.groups.group.$invalid }" v-model="$v.groups.group.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.groups.extraField1')" for="groups-extraField1">Extra Field 1</label>
                        <input type="checkbox" class="form-check" name="extraField1" id="groups-extraField1"
                            :class="{'valid': !$v.groups.extraField1.$invalid, 'invalid': $v.groups.extraField1.$invalid }" v-model="$v.groups.extraField1.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.groups.extraField2')" for="groups-extraField2">Extra Field 2</label>
                        <input type="checkbox" class="form-check" name="extraField2" id="groups-extraField2"
                            :class="{'valid': !$v.groups.extraField2.$invalid, 'invalid': $v.groups.extraField2.$invalid }" v-model="$v.groups.extraField2.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.groups.extraField3')" for="groups-extraField3">Extra Field 3</label>
                        <input type="checkbox" class="form-check" name="extraField3" id="groups-extraField3"
                            :class="{'valid': !$v.groups.extraField3.$invalid, 'invalid': $v.groups.extraField3.$invalid }" v-model="$v.groups.extraField3.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.groups.extraField4')" for="groups-extraField4">Extra Field 4</label>
                        <input type="checkbox" class="form-check" name="extraField4" id="groups-extraField4"
                            :class="{'valid': !$v.groups.extraField4.$invalid, 'invalid': $v.groups.extraField4.$invalid }" v-model="$v.groups.extraField4.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.groups.extraField5')" for="groups-extraField5">Extra Field 5</label>
                        <input type="text" class="form-control" name="extraField5" id="groups-extraField5"
                            :class="{'valid': !$v.groups.extraField5.$invalid, 'invalid': $v.groups.extraField5.$invalid }" v-model="$v.groups.extraField5.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.groups.extraField6')" for="groups-extraField6">Extra Field 6</label>
                        <input type="text" class="form-control" name="extraField6" id="groups-extraField6"
                            :class="{'valid': !$v.groups.extraField6.$invalid, 'invalid': $v.groups.extraField6.$invalid }" v-model="$v.groups.extraField6.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.groups.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./groups-update.component.ts">
</script>

<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="groups">
                <h2 class="jh-entity-heading"><span v-text="$t('smartproApp.groups.detail.title')">Groups</span> {{groups.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('smartproApp.groups.name')">Name</span>
                    </dt>
                    <dd>
                        <span>{{groups.name}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.groups.group')">Group</span>
                    </dt>
                    <dd>
                        <span>{{groups.group}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.groups.extraField1')">Extra Field 1</span>
                    </dt>
                    <dd>
                        <span>{{groups.extraField1}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.groups.extraField2')">Extra Field 2</span>
                    </dt>
                    <dd>
                        <span>{{groups.extraField2}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.groups.extraField3')">Extra Field 3</span>
                    </dt>
                    <dd>
                        <span>{{groups.extraField3}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.groups.extraField4')">Extra Field 4</span>
                    </dt>
                    <dd>
                        <span>{{groups.extraField4}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.groups.extraField5')">Extra Field 5</span>
                    </dt>
                    <dd>
                        <span>{{groups.extraField5}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.groups.extraField6')">Extra Field 6</span>
                    </dt>
                    <dd>
                        <span>{{groups.extraField6}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="groups.id" :to="{name: 'GroupsEdit', params: {groupsId: groups.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./groups-details.component.ts">
</script>

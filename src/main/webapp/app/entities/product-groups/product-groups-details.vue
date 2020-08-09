<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="productGroups">
                <h2 class="jh-entity-heading"><span v-text="$t('smartproApp.productGroups.detail.title')">ProductGroups</span> {{productGroups.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('smartproApp.productGroups.name')">Name</span>
                    </dt>
                    <dd>
                        <span>{{productGroups.name}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.productGroups.group')">Group</span>
                    </dt>
                    <dd>
                        <span>{{productGroups.group}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.productGroups.quantity')">Quantity</span>
                    </dt>
                    <dd>
                        <span>{{productGroups.quantity}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.productGroups.extraField')">Extra Field</span>
                    </dt>
                    <dd>
                        <span>{{productGroups.extraField}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.productGroups.company')">Company</span>
                    </dt>
                    <dd>
                        <div v-if="productGroups.companyId">
                            <router-link :to="{name: 'CompanyView', params: {companyId: productGroups.companyId}}">{{productGroups.companyId}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="productGroups.id" :to="{name: 'ProductGroupsEdit', params: {productGroupsId: productGroups.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./product-groups-details.component.ts">
</script>

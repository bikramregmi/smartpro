<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="voucherType">
                <h2 class="jh-entity-heading"><span v-text="$t('smartproApp.voucherType.detail.title')">VoucherType</span> {{voucherType.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('smartproApp.voucherType.name')">Name</span>
                    </dt>
                    <dd>
                        <span>{{voucherType.name}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.voucherType.type')">Type</span>
                    </dt>
                    <dd>
                        <span>{{voucherType.type}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.voucherType.method')">Method</span>
                    </dt>
                    <dd>
                        <span>{{voucherType.method}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.voucherType.description')">Description</span>
                    </dt>
                    <dd>
                        <span>{{voucherType.description}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.voucherType.extraField')">Extra Field</span>
                    </dt>
                    <dd>
                        <span>{{voucherType.extraField}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.voucherType.company')">Company</span>
                    </dt>
                    <dd>
                        <div v-if="voucherType.companyId">
                            <router-link :to="{name: 'CompanyView', params: {companyId: voucherType.companyId}}">{{voucherType.companyId}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="voucherType.id" :to="{name: 'VoucherTypeEdit', params: {voucherTypeId: voucherType.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./voucher-type-details.component.ts">
</script>

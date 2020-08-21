<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="salesVoucherTypeTotal">
                <h2 class="jh-entity-heading"><span v-text="$t('smartproApp.salesVoucherTypeTotal.detail.title')">SalesVoucherTypeTotal</span> {{salesVoucherTypeTotal.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('smartproApp.salesVoucherTypeTotal.item')">Item</span>
                    </dt>
                    <dd>
                        <span>{{salesVoucherTypeTotal.item}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.salesVoucherTypeTotal.quantityTotal')">Quantity Total</span>
                    </dt>
                    <dd>
                        <span>{{salesVoucherTypeTotal.quantityTotal}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.salesVoucherTypeTotal.rateTotal')">Rate Total</span>
                    </dt>
                    <dd>
                        <span>{{salesVoucherTypeTotal.rateTotal}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.salesVoucherTypeTotal.amountTotal')">Amount Total</span>
                    </dt>
                    <dd>
                        <span>{{salesVoucherTypeTotal.amountTotal}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.salesVoucherTypeTotal.referenceNumber')">Reference Number</span>
                    </dt>
                    <dd>
                        <span>{{salesVoucherTypeTotal.referenceNumber}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.salesVoucherTypeTotal.extraField')">Extra Field</span>
                    </dt>
                    <dd>
                        <span>{{salesVoucherTypeTotal.extraField}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="salesVoucherTypeTotal.id" :to="{name: 'SalesVoucherTypeTotalEdit', params: {salesVoucherTypeTotalId: salesVoucherTypeTotal.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./sales-voucher-type-total-details.component.ts">
</script>

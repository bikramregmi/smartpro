<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="salesVoucherType">
                <h2 class="jh-entity-heading"><span v-text="$t('smartproApp.salesVoucherType.detail.title')">SalesVoucherType</span> {{salesVoucherType.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('smartproApp.salesVoucherType.item')">Item</span>
                    </dt>
                    <dd>
                        <span>{{salesVoucherType.item}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.salesVoucherType.quantity')">Quantity</span>
                    </dt>
                    <dd>
                        <span>{{salesVoucherType.quantity}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.salesVoucherType.rate')">Rate</span>
                    </dt>
                    <dd>
                        <span>{{salesVoucherType.rate}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.salesVoucherType.amount')">Amount</span>
                    </dt>
                    <dd>
                        <span>{{salesVoucherType.amount}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.salesVoucherType.extra')">Extra</span>
                    </dt>
                    <dd>
                        <span>{{salesVoucherType.extra}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.salesVoucherType.accountingVoucher')">Accounting Voucher</span>
                    </dt>
                    <dd>
                        <div v-if="salesVoucherType.accountingVoucherId">
                            <router-link :to="{name: 'AccountingVoucherView', params: {accountingVoucherId: salesVoucherType.accountingVoucherId}}">{{salesVoucherType.accountingVoucherId}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="salesVoucherType.id" :to="{name: 'SalesVoucherTypeEdit', params: {salesVoucherTypeId: salesVoucherType.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./sales-voucher-type-details.component.ts">
</script>

<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="accountingVoucher">
                <h2 class="jh-entity-heading"><span v-text="$t('smartproApp.accountingVoucher.detail.title')">AccountingVoucher</span> {{accountingVoucher.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('smartproApp.accountingVoucher.accountName')">Account Name</span>
                    </dt>
                    <dd>
                        <span>{{accountingVoucher.accountName}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.accountingVoucher.currentBalance')">Current Balance</span>
                    </dt>
                    <dd>
                        <span>{{accountingVoucher.currentBalance}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.accountingVoucher.particulars')">Particulars</span>
                    </dt>
                    <dd>
                        <span>{{accountingVoucher.particulars}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.accountingVoucher.amount')">Amount</span>
                    </dt>
                    <dd>
                        <span>{{accountingVoucher.amount}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.accountingVoucher.narration')">Narration</span>
                    </dt>
                    <dd>
                        <span>{{accountingVoucher.narration}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.accountingVoucher.total')">Total</span>
                    </dt>
                    <dd>
                        <span>{{accountingVoucher.total}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.accountingVoucher.grandTotal')">Grand Total</span>
                    </dt>
                    <dd>
                        <span>{{accountingVoucher.grandTotal}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="accountingVoucher.id" :to="{name: 'AccountingVoucherEdit', params: {accountingVoucherId: accountingVoucher.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./accounting-voucher-details.component.ts">
</script>

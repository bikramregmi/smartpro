<template>
    <div>
        <h5 class="card-title">Welcome To Payroll</h5>
        <hr>&nbsp;&nbsp;&nbsp;
        <a href="#" class="btn btn-success">Active Employee</a>
        <a href="#" class="btn btn-primary">{{employeeCount}}</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <img style="width:45%;" src=" ../content/images/dashboardnew.png">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="#" class="btn btn-success">Total Employee</a>
        <a href="#" class="btn btn-primary">{{employeeCount}}</a><br>
    </div>
</template>
<script>
    import SH from '../../app/core/backend/backend'

    export default {
        name: 'Home',
        data() {
            return {
                employeeCount: ''
            }
        },
        created() {
            this.getTotalEmployee()
        },
        methods: {
            getTotalEmployee: function () {
                SH.ajax.callRemote(`http://127.0.0.1:8080/api/employees/count`, '', 'GET', function (data) {
                    this.employeeCount = data
                }.bind(this))
            }
        }
    }
</script>
<style>

</style>

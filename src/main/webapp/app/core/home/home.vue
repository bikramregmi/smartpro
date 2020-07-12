<template>
    <div class="login-form">
        <section>
            <div class="avatar">
                <img src="https://www.talkwalker.com/images/2020/blog-headers/image-analysis.png" alt="Avatar">
            </div>
            <h2 class="text-center">Member Login</h2>
            <form v-on:submit.prevent="doLogin()">
                <div class="form-group">
                    <input type="text" class="form-control" name="username" placeholder="Username" v-model="username" required="required">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="password" placeholder="Password" v-model="password" required="required">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-lg btn-block">Sign in</button>
                </div>
                <div class="clearfix">
                    <label class="pull-left checkbox-inline"><input type="checkbox"> Remember me</label>
                    <a href="#" class="pull-right">Forgot Password?</a>
                </div>
            </form>
        </section>
        <!--        <p class="text-center small">Don't have an account? <button @click="register()">Sign up here!</button></p>-->
    </div>
</template>

<script lang="ts" src="./home.component.ts">
</script>

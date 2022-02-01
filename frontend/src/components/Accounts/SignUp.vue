<template>
  <div>
    <!-- 페이지 제목 -->
    <page-title title="회원가입" step="1" total="3"></page-title>

    <!-- 이메일 입력 -->
    <label for="email" class="label-text">이메일</label>
    <input
      type="email"
      id="email"
      class="input-text"
      name="email"
      v-model="email"
      autocapitalize="none"
    />
    <span class="alert" v-show="valid.emailType"
      >이메일 형식이 올바르지 않습니다.</span
    >
    <span class="alert" v-show="valid.email"
      >이미 가입된 이메일 주소입니다.</span
    >

    <!-- 비밀번호 입력 -->
    <label for="password" class="label-text">비밀번호</label>
    <input-password
      :password="password"
      :placetext="'영문, 특수문자 포함 8자리 이상'"
      id="password"
      @inputVal="updatePassword"
    ></input-password>
    <span class="alert" v-show="valid.password"
      >영문, 특수문자 포함 8자리 이상 입력해주세요.</span
    >

    <!-- 비밀번호 확인 -->
    <label for="checkPwd" class="label-text">비밀번호 확인</label>
    <input-password
      :password="checkPwd"
      :placetext="'비밀번호를 다시 입력해주세요.'"
      id="checkPwd"
      @inputVal="updatecheckPwd"
    ></input-password>
    <span class="alert" v-show="valid.checkPwd"
      >비밀번호가 일치하지 않습니다.</span
    >

    <!-- 개인정보 및 이용약관 동의 -->
    <div class="agree-box">
      <input type="checkbox" id="agree" name="agree" v-model="agree" />
      <label for="agree">개인정보 및 이용약관에 동의합니다.</label>
    </div>

    <!-- 버튼 -->
    <button
      :class="{ disabled: !isCompleted }"
      :disabled="!isCompleted"
      @click="moveConfirmEmail"
    >
      다음
    </button>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import PageTitle from "@/components/Accounts/child/PageTitle.vue";
import InputPassword from "@/components/Accounts/child/InputPassword.vue";

export default defineComponent({
  name: "SignUp",
  components: {
    PageTitle,
    InputPassword,
  },
  data() {
    return {
      email: "",
      password: "",
      checkPwd: "",
      isShowPwd: false,
      agree: "",
      valid: {
        emailType: false,
        email: false,
        password: false,
        checkPwd: false,
      },
      isCompleted: true,
    };
  },
  methods: {
    // 비밀번호 컴포넌트에 입력된 텍스트 가져오기
    updatePassword(value: string) {
      this.password = value;
    },
    updatecheckPwd(value: string) {
      this.checkPwd = value;
    },
    // 다음 단계(이메일 인증)로 이동
    moveConfirmEmail() {
      this.$router.push({ name: "ConfirmEmail" });
    },
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/reset.scss";
@import "@/assets/css/common.scss";
@import "@/assets/css/accounts.scss";

.agree-box {
  text-align: center;
  margin: $size-big 0 $size-regular;
}
</style>

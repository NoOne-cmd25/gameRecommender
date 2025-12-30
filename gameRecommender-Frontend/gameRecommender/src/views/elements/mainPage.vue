<template>
  <div class="common-layout">
    <el-container>
      <el-header class="page-header">steam游戏ai推荐系统</el-header>
      <el-main>
        <div class="main-content">
          <el-form :model="form" label-width="auto" class="recommend-form">
            <el-form-item label="Steam ID">
              <el-input placeholder="请输入您的Steam ID" v-model="form.steamId" />
            </el-form-item>
            <el-form-item label="游戏类型偏好">
              <el-checkbox-group v-model="form.gameType">
                <el-checkbox value="角色扮演游戏" name="type"> 角色扮演游戏 </el-checkbox>
                <el-checkbox value="动作游戏" name="type"> 动作游戏 </el-checkbox>
                <el-checkbox value="策略游戏" name="type"> 策略游戏 </el-checkbox>
                <el-checkbox value="肉鸽游戏" name="type"> 肉鸽游戏 </el-checkbox>
              </el-checkbox-group>
            </el-form-item>
            <el-form-item label="回复内容">
              <el-input v-model="form.reply" type="textarea" />
            </el-form-item>
            <div class="form-actions">
              <el-button type="primary" @click="onSubmit" class="primary-btn">提交</el-button>
            </div>
          </el-form>

          <!-- <div class="reply-card">
            <h3 class="reply-title">推荐结果</h3>
            <div class="reply-box">{{ form.reply }}</div>
          </div> -->
        </div>
      </el-main>
      <el-footer class="page-footer"></el-footer>
    </el-container>
  </div>
</template>

<style scoped>
:root {
  --bg: #f5f7fb;
  --card: #ffffff;
  --accent: #409eff; /* element-plus primary */
  --muted: #6b7280;
}

.common-layout {
  min-height: 100vh;
  background: linear-gradient(180deg, #eef2ff 0%, #f7fbff 100%);
  padding: 24px;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
}

.page-header {
  background: linear-gradient(90deg, #2b6cb0, #4f46e5);
  color: #fff;
  font-weight: 700;
  font-size: 18px;
  display: flex;
  align-items: center;
  padding: 12px 20px;
  border-radius: 8px;
  box-shadow: 0 6px 18px rgba(79, 70, 229, 0.12);
}

.main-content {
  display: flex;
  width: 100%;
  align-self: stretch;
  gap: 20px;
  align-items: center;
  /* align-items: flex-start; */
  margin-top: 18px;
  /* flex-wrap: wrap; */
}

.recommend-form {
  background: var(--card);
  padding: 18px;
  border-radius: 12px;
  width: 100%;
  height: 100%;
  box-shadow: 0 6px 20px rgba(16, 24, 40, 0.06);
  transition:
    transform 0.18s ease,
    box-shadow 0.18s ease;
}
.recommend-form:hover {
  transform: translateY(-4px);
  box-shadow: 0 12px 30px rgba(16, 24, 40, 0.08);
}

.form-actions {
  width: 100%;
  display: flex;
  justify-content: center;
  margin-top: 12px;
}

.primary-btn {
  transition:
    transform 0.12s ease,
    box-shadow 0.12s ease;
}
.primary-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 20px rgba(64, 158, 255, 0.18);
}

.reply-card {
  flex: 1 1 380px;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0.6), rgba(255, 255, 255, 0.9));
  border-radius: 12px;
  padding: 16px;
  box-shadow: 0 6px 18px rgba(2, 6, 23, 0.04);
  min-height: 180px;
  align-self: stretch;
}
.reply-title {
  margin: 0 0 8px 0;
  font-size: 16px;
  color: var(--muted);
}
.reply-box {
  background: #fbfdff;
  border-left: 3px solid var(--accent);
  padding: 12px;
  border-radius: 8px;
  min-height: 120px;
  white-space: pre-wrap;
  color: #111827;
  transition: background 0.12s ease;
}
.reply-box:hover {
  background: #f3f8ff;
}

.page-footer {
  margin-top: 18px;
  padding: 12px;
  text-align: center;
  color: var(--muted);
}

@media (max-width: 880px) {
  .main-content {
    flex-direction: column;
  }
  .recommend-form {
    max-width: 100%;
  }
}
</style>

<script lang="ts" setup>
import { reactive } from 'vue'
import axios from 'axios'

// do not use same name with ref
const form = reactive({
  steamId: '',
  gameType: [],
  reply: '',
})

const onSubmit = () => {
  // console.log('submit!')
  console.log(form)
  console.log(form.steamId)
  console.log(form.gameType.join(', '))
  form.reply = '正在生成推荐结果，请稍候...'
  axios
    .post(
      '/doRecommendation',
      {
        userId: form.steamId,
        userPrompt: form.gameType.join(', '),
        // reply: form.reply,
      },
      {
        headers: {
          'Content-Type': 'application/json',
        },
      },
    )
    .then((response) => {
      console.log('Response:', response.data)
      form.reply = response.data
      // Handle success (e.g., show a success message)
    })
    .catch((error) => {
      console.error('Error submitting form:', error)
      // Handle error (e.g., show an error message)
    })
}
</script>

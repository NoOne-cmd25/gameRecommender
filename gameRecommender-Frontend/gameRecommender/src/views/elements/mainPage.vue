<template>
  <div class="common-layout">
    <el-container>
      <el-header>Header</el-header>
      <el-main style="display: flex">
        <el-form :model="form" label-width="auto" style="width: 50%; max-width: 3000px">
          <el-form-item label="Steam ID">
            <el-input v-model="form.steamId" />
          </el-form-item>
          <el-form-item label="Preferences">
            <el-input v-model="form.preferences" />
          </el-form-item>

          <el-form-item label="Activity type">
            <el-checkbox-group v-model="form.gameType">
              <el-checkbox value="Online activities" name="type"> Online activities </el-checkbox>
              <el-checkbox value="Promotion activities" name="type">
                Promotion activities
              </el-checkbox>
              <el-checkbox value="Offline activities" name="type"> Offline activities </el-checkbox>
              <el-checkbox value="Simple brand exposure" name="type">
                Simple brand exposure
              </el-checkbox>
            </el-checkbox-group>
          </el-form-item>
          <el-form-item label="Analysis or Recommendation">
            <el-radio-group v-model="form.choice">
              <el-radio value="Sponsor">Sponsor</el-radio>
              <el-radio value="Venue">Venue</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="Reply">
            <el-input v-model="form.reply" type="textarea" />
          </el-form-item>
          <el-form-item style="display: flex; justify-content: center; align-items: center">
            <el-button type="primary" @click="onSubmit">Create</el-button>
            <el-button>Cancel</el-button>
          </el-form-item>
        </el-form>
      </el-main>
      <el-footer>Footer</el-footer>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import { reactive } from 'vue'

// do not use same name with ref
const form = reactive({
  steamId: '',
  preferences: '',
  gameType: [],
  choice: '',
  reply: '',
})

const onSubmit = () => {
  console.log('submit!')
}
</script>

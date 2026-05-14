# Todo App

本地待办事项管理 Android 应用，无需后端，单用户离线使用。

## 功能

- **首页** — 显示星期、阳历日期、农历日期
- **任务管理**
  - 新增任务（默认未完成）
  - 完成自动移至列表底部
  - 短期任务：仅持续一日，可设置过期后保留或删除
  - 长期任务：支持设置持续天数（空=永久）
- **提醒日**
  - 设置提醒日期
  - 自动提前3天发送通知
- **额外计划**
  - 新增多个计划
  - 随机选择一个计划
  - 完成后删除

## 技术栈

| 组件 | 用途 |
|------|------|
| Kotlin | 编程语言 |
| Jetpack Compose + Material3 | UI |
| Room | 本地数据库 |
| WorkManager | 后台任务调度 |
| Hilt | 依赖注入 |
| Navigation Compose | 页面导航 |

## 项目结构

```
app/src/main/java/com/example/todoapp/
├── data/
│   ├── local/        # Room 数据库、DAO、实体
│   └── repository/   # 数据仓库
├── domain/
│   └── usecase/      # 业务用例
├── presentation/
│   ├── screen/       # UI 页面和 ViewModel
│   └── theme/        # 主题配色
├── util/             # 工具类（农历、通知等）
└── worker/           # WorkManager Worker

app/src/main/res/     # 资源文件
```

## 构建

```bash
# Debug APK
./gradlew assembleDebug

# Release APK（需要签名配置）
./gradlew assembleRelease
```

也可用 Android Studio 打开项目根目录，等待 Gradle 同步后直接运行。

## 环境要求

- Android SDK 24+
- JDK 17
- Android Studio Hedgehog 或更新版本

## License

MIT

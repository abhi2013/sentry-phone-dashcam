# 📱 Sentry Phone Dashcam

**Sentry Phone Dashcam** is a smart, AI-powered dashcam and vehicle sentry system that transforms any spare smartphone into a powerful security camera for your car. 

I was inspired to write it when someone left an anyonymous note on my car, telling me to not park in any of the available public 'first-come-first-serve' parking spots closest to my house. As parking is a scracity in my neighbourhood, I wish to find out who the entitled neighbour is, and to guard myself against any escalations or damages to my car. As a software engineer wishing to polish up my multiplatform app development skills, this app seemed a great opportunity to practise writing up a modern, MVVM architecture, multiplatform app. 

![Sentry Mode Screenshot](./screenshots/sentry_mode_ui.png)

---

## 🚗 What is it?

Sentry Phone Dashcam is a multi-platform mobile app that runs on a secondary phone placed in your vehicle. It passively monitors your surroundings and automatically records when motion or human presence is detected. The footage can be synced to the cloud and accessed remotely from your main phone via the companion Admin Mode.

It’s like Tesla Sentry Mode — for everyone.

---

## 🎯 Features

- 🎥 **AI-Powered Recording** – Automatically detects motion or people near your car
- 🔒 **Offline-First** – Stores video locally and syncs when a connection is available
- ☁️ **Optional Cloud Backup** – Securely uploads clips to your private AWS S3 storage
- 📱 **Admin Mode App** – Get real-time alerts and stream/download events
- 📦 **Local + Cloud Storage** – Flexible storage options for both free and premium users
- ⚙️ **Customizable Sensitivity** – Adjust detection levels to suit your parking environment
- 🚘 **Multi-Car Support** – Pair multiple dashcam phones to a single admin device
- 🔔 **Smart Notifications** – Rich push alerts with event type and preview image
- 🧠 **Built with Kotlin Multiplatform & Compose** – Seamless cross-platform performance

---

## 📱 Screenshots

| Dashcam Mode  | Admin Mode  | Event Viewer  | History Feed |
|---------------|-------------|---------------|--------------|
| ![](./screenshots/dashcam_mode.png) | ![](./screenshots/admin_mode.png) | ![](./screenshots/event_viewer.png) | ![](./screenshots/history_feed.png) |

---

## 🔧 How It Works

1. **Install** the app on a spare phone, grant permissions, and place it in your car.
2. **Enable Sentry Mode** — the app will now monitor surroundings while parked.
3. **Install App** on your main phone, login using the admin mode and pair it using the QR code or code input.
4. **Receive Alerts** whenever motion or people are detected.
5. **View and manage clips** — delete, download, or share footage.

---

## 🔐 Privacy & Security

- End-to-end encrypted cloud uploads
- All footage is viewable only by the paired admin user

---

## 💸 Pricing

| Plan         | Local Storage | Cloud Sync | Event History | Price |
|--------------|---------------|------------|----------------|-------|
| Free         | ✅             | ❌         | 24 hours        | £0/mo |
| Premium      | ✅             | ✅         | 30 days         | £4.99/mo |

---

## 🚀 Coming Soon

- GPS tagging + route history
- Real-time video streaming
- License plate blurring for privacy
- Multi-car pairing

---

## 📦 Tech Stack

- **Frontend:** Kotlin Multiplatform Mobile (Compose Multiplatform UI)
- **Backend:** FastAPI
- **Cloud Storage:** AWS S3 (user-owned)
- **Notifications:** Firebase Cloud Messaging (FCM)
- **Payments:** Stripe via RevenueCat

---

## 👨‍💻 Contributing

Want to contribute or fork your own version?
Pull requests welcome! Check out our open issues for tasks to tackle.

### Running Tests

The project includes JVM unit tests and Android instrumentation tests using Cucumber. To run them locally you need the Android SDK and an emulator:

```
gradle jvmTest --no-daemon
gradle connectedAndroidTest --no-daemon
```

If the Gradle wrapper jar is missing, generate it with `gradle wrapper` before running the tests.

---

## 🧭 Roadmap

- [x] Dashcam device mode with AI detection
- [x] Admin app with event viewer and push alerts
- [ ] Cloud sync with quota management
- [ ] Live stream feature
- [ ] GPS tracking

---

## 📬 Contact

Questions or ideas?  
Email us at **support@[Coming Soon].app** or visit [Coming Soon]

---

© 2025 Sentry Phone Dashcam. All rights reserved.


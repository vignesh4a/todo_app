# My Todos – Android app

A simple todo list app (Kotlin + Jetpack Compose). Add tasks, tick them off, delete them,
and clear completed ones. Tasks are saved on the phone.

## 1. Build it

**Option A – GitHub (no setup on your computer)**
1. Create a new repository on GitHub and push this folder to it.
2. Open the repo's **Actions** tab → the "Build Android app" run → download the
   `todo-debug-apk` artifact (a zip containing `app-debug.apk`).

**Option B – Android Studio**
Open this folder in Android Studio and press ▶ Run with your phone plugged in (USB debugging on),
or use *Build → Build APK(s)*.

## 2. Install on your phone (no store needed)
1. Copy `app-debug.apk` to your phone (Google Drive, email, USB…).
2. Tap it. Android will ask you to allow installs from that app ("Install unknown apps") – allow it.
3. Tap **Install**.

## 3. Publish on Google Play
1. Change `applicationId` in `app/build.gradle.kts` to something unique, e.g. `com.yourname.todo`.
2. Create an upload key (once; keep the file and passwords safe – you need them for every update):
   ```
   keytool -genkey -v -keystore release.jks -keyalg RSA -keysize 2048 -validity 10000 -alias upload
   ```
3. In GitHub → Settings → Secrets and variables → Actions, add:
   - `KEYSTORE_BASE64` – output of `base64 -w0 release.jks`
   - `KEYSTORE_PASSWORD`, `KEY_ALIAS` (`upload`), `KEY_PASSWORD`
4. Push again; download the `todo-release-aab` artifact (`app-release.aab`).
5. Sign up at https://play.google.com/console (one-time US$25 fee, identity verification).
6. Create the app, fill in the store listing (description, icon 512×512, screenshots),
   privacy policy URL, content rating and data-safety form, then upload the `.aab`.
7. New personal accounts must run a **closed test with at least 12 testers for 14 days**
   before Google lets you publish to production. After that, submit for review.

For each update, bump `versionCode` (and `versionName`) in `app/build.gradle.kts`.

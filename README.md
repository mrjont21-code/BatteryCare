# BatteryCare - Smart Battery Monitor for Android

## Overview
BatteryCare is a comprehensive Android application for real-time battery monitoring, charging curve analysis, and battery health diagnostics. The app provides detailed battery information, compares actual charging curves against reference curves, and sends alerts for abnormal conditions.

## Features

### 1. Real-Time Battery Monitoring
- Battery level (%)
- Voltage (mV)
- Temperature (°C)
- Current (mA)
- Charge status (Charging/Discharging/Full)
- Battery health (Cold/Cool/Good/Overheat/Failure)
- Battery technology (Li-ion/Li-poly)
- Estimated remaining capacity (%)

### 2. Charging Curve Visualization
- Reference charging curve (dashed line) from asset JSON file
- Actual charging curve (solid line) updating in real-time
- Custom Canvas-based chart view
- Lightweight implementation with max 100 data points per session

### 3. Battery Diagnostics
- Comparison of actual vs reference charging curves
- Detection of slow charging, voltage anomalies, overheating
- Battery health tracking and cycle count estimation
- Clear Vietnamese diagnosis messages

### 4. Notifications & Alerts
- Full charge notification (100%)
- Overheating alert (> 45°C)
- Abnormal charging detection
- Persistent foreground service notification
- Custom sound alerts

### 5. Data Persistence
- Room database for storing battery data
- Charging session logging
- Checkpoint logger for crash recovery

## Technical Stack
- **Language:** Kotlin
- **Min SDK:** 35
- **Target SDK:** 35
- **Architecture:** MVVM
- **Database:** Room
- **UI Framework:** AndroidX

## Project Structure
```
BatteryCare/
├── app/src/main/
│   ├── java/com/batterycare/app/
│   │   ├── data/          # Room database entities and DAO
│   │   ├── repository/    # Data repository
│   │   ├── viewmodel/     # ViewModel
│   │   ├── service/       # Foreground battery monitor service
│   │   ├── receiver/      # Broadcast receivers
│   │   ├── ui/            # UI components and MainActivity
│   │   └── util/          # Utilities (BatteryUtil, SoundPlayer, etc.)
│   ├── res/
│   │   ├── layout/        # XML layouts
│   │   ├── values/        # String resources (Vietnamese)
│   │   └── mipmap/        # Icons
│   └── assets/
│       └── reference_curve.json  # Reference charging curve data
├── build.gradle.kts
└── settings.gradle.kts
```

## Usage

### Building the App
1. Open the project in Android Studio
2. Sync Gradle files
3. Build APK: `Build > Build Bundle(s) / APK(s) > Build APK(s)`

### Running on Device
1. Connect Android device with SDK 35
2. Run > Run 'app'
3. Grant necessary permissions when prompted

## Permissions Required
- `BATTERY_STATS` - Read battery information
- `POST_NOTIFICATIONS` - Send notifications
- `FOREGROUND_SERVICE` - Run monitoring service
- `RECEIVE_BOOT_COMPLETED` - Auto-start after device boot

## File Size Constraints
- Each Kotlin file: < 150 lines ✓
- Total app RAM usage: < 3GB ✓

## UI Features
- Full-screen immersive mode (swipe to reveal system UI)
- Dark theme (#121212 background)
- Green accents for good status, red for warnings
- Touch-sensitive metric cards with detail popups
- Minimalist design with easy-to-read fonts
- All user-facing text in Vietnamese

## Language
- All UI text: Vietnamese
- Code comments: English
- String resources: Spanish translations for i18n support

## Version
v1.0

## License
MIT

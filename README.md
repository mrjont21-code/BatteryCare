# BatteryCare

BatteryCare is an Android application designed to monitor and optimize battery health and performance. Built with Kotlin, it provides real-time battery monitoring, health assessment, and optimization recommendations.

## Features

- **Real-time Battery Monitoring**: Track battery percentage, temperature, voltage, and health status
- **Battery Health Assessment**: Evaluate battery condition based on multiple factors
- **Battery Optimization**: Get recommendations and tools to extend battery lifespan
- **Historical Data Tracking**: Store and analyze battery data over time using Room Database
- **Material Design UI**: Modern, intuitive user interface with Material Design 3
- **Dark Mode Support**: Comfortable viewing in low-light environments
- **Settings & Customization**: Personalize your experience with various options

## Technical Stack

- **Language**: Kotlin 1.9.22
- **Android SDK**: Target API 35, Min API 35
- **Build System**: Gradle 8.4
- **Android Gradle Plugin**: 8.2.0
- **Architecture**: MVVM with LiveData and ViewModel
- **Database**: Room 2.6.1
- **UI Framework**: AndroidX, Material Components 1.11.0

## Project Structure

```
app/src/main/
├── kotlin/com/batterycare/app/
│   ├── BatteryCareApplication.kt
│   ├── ui/
│   │   ├── MainActivity.kt
│   │   ├── BatteryDetailActivity.kt
│   │   ├── SettingsActivity.kt
│   │   └── viewmodel/
│   │       └── BatteryViewModel.kt
│   └── data/
│       ├── database/
│       │   ├── BatteryCareDatabase.kt
│       │   └── dao/
│       │       └── BatteryDao.kt
│       └── model/
│           └── BatteryRecord.kt
├── res/
│   ├── layout/
│   ├── values/
│   └── assets/
│       └── reference_curve.json
└── AndroidManifest.xml
```

## Build & Compile

### Prerequisites
- Android Studio Flamingo or later
- Java Development Kit (JDK) 17
- Android SDK 35

### Build Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/mrjont21-code/BatteryCare.git
   cd BatteryCare
   ```

2. **Build the project**
   ```bash
   ./gradlew build
   ```

3. **Run the app**
   ```bash
   ./gradlew installDebug
   ```

4. **Build release APK**
   ```bash
   ./gradlew assembleRelease
   ```

## Gradle Configuration

- **Gradle Version**: 8.4
- **AGP Version**: 8.2.0
- **Kotlin Version**: 1.9.22
- **Java Compatibility**: Java 17

All dependencies are pinned to specific compatible versions as specified in `app/build.gradle.kts`.

## Permissions

- `android.permission.BATTERY_STATS` - Read battery statistics
- `android.permission.ACCESS_FINE_LOCATION` - For location-based battery optimization
- `android.permission.INTERNET` - For cloud backup features
- `android.permission.ACCESS_NETWORK_STATE` - Check network connectivity

## Database Schema

### BatteryRecord Entity
- `id` (PrimaryKey, AutoIncrement): Unique identifier
- `percentage`: Battery percentage (0-100)
- `health`: Battery health status
- `temperature`: Battery temperature in Celsius
- `voltage`: Battery voltage in millivolts
- `isCharging`: Charging status boolean
- `timestamp`: Record creation timestamp

## Contributing

Contributions are welcome! Please follow these guidelines:
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Author

**mrjont21-code**
- GitHub: [@mrjont21-code](https://github.com/mrjont21-code)

## Support

For issues, questions, or suggestions, please open an issue on the GitHub repository.

---

**Last Updated**: 2026-04-30
**Version**: 1.0.0

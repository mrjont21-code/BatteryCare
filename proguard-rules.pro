# Keep Room database classes
-keep class androidx.room.** { *; }
-keepclassmembers class * extends androidx.room.RoomDatabase { *; }

# Keep ViewModel
-keep class androidx.lifecycle.** { *; }

# Keep data classes
-keep class com.batterycare.app.data.** { *; }
-keep class com.batterycare.app.util.** { *; }

# Keep service and receivers
-keep class com.batterycare.app.service.** { *; }
-keep class com.batterycare.app.receiver.** { *; }

# Preserve line numbers for crash reporting
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

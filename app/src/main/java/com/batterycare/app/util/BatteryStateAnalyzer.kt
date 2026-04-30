package com.batterycare.app.util

data class DiagnosisResult(
    val status: String,
    val recommendation: String,
    val severity: Int // 0=info, 1=warning, 2=critical
)

class BatteryStateAnalyzer {
    fun analyze(info: BatteryInfo, history: List<BatteryInfo>): DiagnosisResult {
        return when {
            info.temperature > 45 ->
                DiagnosisResult(
                    "Nhiệt độ pin cao",
                    "Hãy rút sạc ngay",
                    2
                )
            info.health == "Overheat" ->
                DiagnosisResult(
                    "Pin quá nóng",
                    "Hãy rút sạc và để pin nguội",
                    2
                )
            info.health == "Failure" ->
                DiagnosisResult(
                    "Pin bị hỏng",
                    "Liên hệ hỗ trợ kỹ thuật",
                    2
                )
            info.voltage < 3000 || info.voltage > 4300 ->
                DiagnosisResult(
                    "Điện áp bất thường",
                    "Kiểm tra cáp sạc",
                    2
                )
            info.health == "Good" ->
                DiagnosisResult(
                    "Pin còn tốt",
                    "Sạc pin bình thường",
                    0
                )
            else ->
                DiagnosisResult(
                    "Pin yếu",
                    "Nên xem xét thay pin",
                    1
                )
        }
    }
}

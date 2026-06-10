package ddum.com.mobile.week09.naverretrofitsample.data.util

import android.content.Context
import android.os.Environment
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class FileManager(val context: Context) {
    companion object {
        // 외부저장소 쓰기 사용 가능 여부 확인
        fun isExternalStorageWritable(): Boolean {
            return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
        }

        // 외부저장소 읽기 사용 가능 여부 확인
        fun isExternalStorageReadable(): Boolean {
            return Environment.getExternalStorageState() in
                    setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
        }

        // path의 마지막에서 파일명 추출, 확장자 추가 필요
        fun getImageFileName(path: String) : String {
            val fileName = path.slice(IntRange( path.lastIndexOf("/")+1, path.length-1))
            return fileName
        }

        // 현재시간으로 파일명 추출, 확장자 추가 필요
        fun getCurrentTime() : String {
            return SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        }

        // baseDirectory 하위에 subDirectory 가 없을 경우 생성
        fun createSubDirectory(baseDirectory: File, subDirectory: String) : Boolean {
            val directory = File(baseDirectory, subDirectory)
            if (!directory.exists()) {
                directory.mkdir()
                return true
            }
            return false
        }
    }
}
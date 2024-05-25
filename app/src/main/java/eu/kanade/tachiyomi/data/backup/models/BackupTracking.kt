package eu.kanade.tachiyomi.data.backup.models

import eu.kanade.tachiyomi.data.database.models.Track
import eu.kanade.tachiyomi.data.database.models.TrackImpl
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
data class BackupTracking(
    @ProtoNumber(1) var syncId: Int,
    @ProtoNumber(2) var libraryId: Long,
    // Replace deprecated mediaIdInt with mediaId
    @ProtoNumber(3) var mediaId: Long = 0,
    @ProtoNumber(4) var trackingUrl: String = "",
    @ProtoNumber(5) var title: String = "",
    @ProtoNumber(6) var lastChapterRead: Float = 0F,
    @ProtoNumber(7) var totalChapters: Int = 0,
    @ProtoNumber(8) var score: Float = 0F,
    @ProtoNumber(9) var status: Int = 0,
    @ProtoNumber(10) var startedReadingDate: Long = 0,
    @ProtoNumber(11) var finishedReadingDate: Long = 0,
) {
    // Function to get TrackImpl from BackupTracking
    fun getTrackingImpl(): TrackImpl {
        return TrackImpl().apply {
            sync_id = this@BackupTracking.syncId
            media_id = this@BackupTracking.mediaId
            library_id = this@BackupTracking.libraryId
            title = this@BackupTracking.title
            last_chapter_read = this@BackupTracking.lastChapterRead
            total_chapters = this@BackupTracking.totalChapters
            score = this@BackupTracking.score
            status = this@BackupTracking.status
            started_reading_date = this@BackupTracking.startedReadingDate
            finished_reading_date = this@BackupTracking.finishedReadingDate
            tracking_url = this@BackupTracking.trackingUrl
        }
    }

    companion object {
        // Function to create BackupTracking from Track
        fun copyFrom(track: Track): BackupTracking {
            return BackupTracking(
                syncId = track.sync_id,
                mediaId = track.media_id,
                libraryId = track.library_id!!,
                title = track.title,
                lastChapterRead = track.last_chapter_read,
                totalChapters = track.total_chapters,
                score = track.score,
                status = track.status,
                startedReadingDate = track.started_reading_date,
                finishedReadingDate = track.finished_reading_date,
                trackingUrl = track.tracking_url,
            )
        }
    }
}

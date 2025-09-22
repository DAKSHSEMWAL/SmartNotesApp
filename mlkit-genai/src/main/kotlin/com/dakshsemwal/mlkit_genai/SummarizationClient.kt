package com.dakshsemwal.mlkit_genai

import android.content.Context
import com.google.mlkit.genai.common.DownloadCallback
import com.google.mlkit.genai.common.FeatureStatus
import com.google.mlkit.genai.common.GenAiException
import com.google.mlkit.genai.summarization.Summarization
import com.google.mlkit.genai.summarization.SummarizationRequest
import com.google.mlkit.genai.summarization.Summarizer
import com.google.mlkit.genai.summarization.SummarizerOptions
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.guava.await
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine

class SummarizationClient @Inject constructor(
    @ApplicationContext private val context: Context
) : AutoCloseable {

    private val options = SummarizerOptions.builder(context)
        .setInputType(SummarizerOptions.InputType.ARTICLE)
        .setOutputType(SummarizerOptions.OutputType.ONE_BULLET)
        .setLanguage(SummarizerOptions.Language.ENGLISH)
        .build()

    private val summarizer: Summarizer = Summarization.getClient(options)

    /** Ensures the feature is downloaded and ready before inference. */
    private suspend fun ensureAvailable() {
        when (val status = summarizer.checkFeatureStatus().await()) {
            FeatureStatus.AVAILABLE -> return
            FeatureStatus.DOWNLOADABLE -> {
                // Suspend until download finishes (or fails)
                suspendCancellableCoroutine { cont ->
                    summarizer.downloadFeature(object : DownloadCallback {
                        override fun onDownloadStarted(bytesToDownload: Long) {}
                        override fun onDownloadProgress(totalBytesDownloaded: Long) {}
                        override fun onDownloadCompleted() { cont.resumeWith(Result.success(Unit)) }
                        override fun onDownloadFailed(e: GenAiException) {
                            cont.resumeWith(Result.failure(e))
                        }
                    })
                }
            }
            FeatureStatus.DOWNLOADING -> {
                // Poll until AVAILABLE (simple + reliable)
                // You can replace with a listener/callback API if provided by SDK
                while (true) {
                    val s = summarizer.checkFeatureStatus().await()
                    if (s == FeatureStatus.AVAILABLE) break
                    kotlinx.coroutines.delay(250)
                }
            }
            FeatureStatus.UNAVAILABLE -> error("Summarization feature unavailable on this device")
        }
    }

    /** One-shot summarization; returns full summary text. */
    suspend fun summarise(text: String): Result<String> = runCatching {
        ensureAvailable()
        val req = SummarizationRequest.builder(text).build()
        summarizer.runInference(req).get().summary
    }

    /** Streaming summarization as a Flow of partial text chunks. */
    @OptIn(DelicateCoroutinesApi::class)
    fun summariseStream(text: String): Flow<String> = callbackFlow {
        val req = SummarizationRequest.builder(text).build()
        // Ensure download first, then start streaming
        kotlinx.coroutines.GlobalScope.launch {
            try {
                ensureAvailable()
                summarizer.runInference(req) { newText ->
                    trySend(newText).isSuccess
                }
            } catch (t: Throwable) {
                close(t)
            }
        }
        awaitClose { /* nothing, summarizer closed externally */ }
    }

    override fun close() {
        summarizer.close()
    }
}


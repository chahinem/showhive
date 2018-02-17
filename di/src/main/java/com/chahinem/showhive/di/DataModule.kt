package com.chahinem.showhive.di

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import com.chahinem.showhive.qualifiers.CacheSize
import com.chahinem.showhive.qualifiers.PerApp
import com.chahinem.trakt.api.ZonedDateTimeConverter
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.picasso.LruCache
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import timber.log.Timber
import java.io.File

@Module
class DataModule {
  companion object {
    private const val DISK_CACHE_SIZE = 50L * 1024 * 1024 // 50MB
    private const val PICASSO_CACHE_DIR = "picasso"
  }

  @Provides
  @PerApp
  @CacheSize
  fun getCacheSize(app: Application): Int {
    val am = app.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val memoryClass = am.memoryClass
    // Target ~25% of the available heap.
    return 1024 * 1024 * memoryClass / 4
  }

  @Provides
  @PerApp
  fun provideMoshi(): Moshi = Moshi.Builder()
      .add(KotlinJsonAdapterFactory())
      .add(ZonedDateTimeConverter())
      .build()

  @Provides
  @PerApp
  fun providePicasso(
      app: Application,
      client: OkHttpClient,
      @CacheSize cacheSize: Int
  ): Picasso {
    return Picasso.Builder(app)
        .downloader(OkHttp3Downloader(client
            .newBuilder()
            .cache(Cache(File(app.cacheDir, PICASSO_CACHE_DIR), DISK_CACHE_SIZE))
            .build()))
        .memoryCache(LruCache(cacheSize))
        .listener { _, uri, e -> Timber.e(e, "Failed to load image: %s", uri) }
        .build()
  }
}

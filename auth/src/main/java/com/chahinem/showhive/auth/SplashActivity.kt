package com.chahinem.showhive.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.chahinem.showhive.base.BaseActivity
import com.chahinem.showhive.base.Router
import com.chahinem.showhive.base.Router.Companion.REDIRECT_URL
import com.chahinem.trakt.api.TraktApi
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.activity_splash.connectBtn
import kotlinx.android.synthetic.main.activity_splash.skipBtn
import timber.log.Timber
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject

class SplashActivity : BaseActivity() {

  @Inject lateinit var router: Router
  @Inject lateinit var traktApi: TraktApi

  override fun getLayoutId() = R.layout.activity_splash

  override fun setUpDependencyInjection() {
    val component = DaggerActivityComponent.builder()
        .activity(this)
        .activityModule(ActivityModule())
        .appComponent(appComponent)
        .build()

    component.inject(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // FIXME: dispose of these
    connectBtn
        .clicks()
        .throttleFirst(300, MILLISECONDS)
        .subscribe({ router.connectWithTrakt() }, Timber::e)

    skipBtn
        .clicks()
        .throttleFirst(300, MILLISECONDS)
        .subscribe({ router.home() }, Timber::e)
  }

  override fun onNewIntent(intent: Intent?) {
    super.onNewIntent(intent)
    setIntent(intent)
    intent?.data?.let {
      val uri = Uri.parse(it.toString())
      if (uri.queryParameterNames.contains("code")) {
        val code = uri.getQueryParameter("code")
        // TODO: refactor into viewmodel+interactor+repo
        traktApi
            .exchangeCodeForAccessToken(
                "authorization_code",
                code,
                BuildConfig.TRAKT_CLIENT_ID,
                BuildConfig.TRAKT_CLIENT_SECRET,
                REDIRECT_URL)
            // TODO: save access and refresh token into sharedPrefs
            .subscribe({ router.home() }, Timber::e)
      }
    }
  }
}

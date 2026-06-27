package com.snapshot.weatherwallpaper.wallpaper

import android.os.Handler
import android.os.Looper
import android.service.wallpaper.WallpaperService
import android.view.SurfaceHolder
import com.snapshot.weatherwallpaper.model.WeatherSceneState
import com.snapshot.weatherwallpaper.model.WeatherStore
import com.snapshot.weatherwallpaper.scene.WeatherSceneRenderer

class WeatherWallpaperService : WallpaperService() {
    override fun onCreateEngine(): Engine = WeatherEngine()

    inner class WeatherEngine : Engine() {
        private val handler = Handler(Looper.getMainLooper())
        private val renderer = WeatherSceneRenderer()
        private var visible = false
        private var state: WeatherSceneState = WeatherSceneState.fallback()
        private var lastStateRefresh = 0L

        private val drawRunnable = object : Runnable {
            override fun run() {
                drawFrame()
                if (visible) handler.postDelayed(this, 1000L / 24L)
            }
        }

        override fun onVisibilityChanged(visible: Boolean) {
            this.visible = visible
            if (visible) {
                refreshState(force = true)
                handler.post(drawRunnable)
            } else {
                handler.removeCallbacks(drawRunnable)
            }
        }

        override fun onSurfaceDestroyed(holder: SurfaceHolder) {
            visible = false
            handler.removeCallbacks(drawRunnable)
            super.onSurfaceDestroyed(holder)
        }

        private fun refreshState(force: Boolean = false) {
            val now = System.currentTimeMillis()
            if (force || now - lastStateRefresh > 30_000L) {
                state = WeatherStore.load(applicationContext)
                lastStateRefresh = now
            }
        }

        private fun drawFrame() {
            refreshState()
            val holder = surfaceHolder
            val canvas = runCatching { holder.lockCanvas() }.getOrNull() ?: return
            try {
                renderer.draw(canvas, state, System.currentTimeMillis(), includeText = false)
            } finally {
                runCatching { holder.unlockCanvasAndPost(canvas) }
            }
        }
    }
}

package ris58h.galaxyfinder.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews

class FinderWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }
}

internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
    val views = RemoteViews(context.packageName, R.layout.widget)

    val launchIntent: Intent? =
        if (Build.VERSION.SDK_INT >= 34) {
            Intent().setClassName("com.sec.android.app.launcher", "com.sec.android.app.launcher.search.SearchActivity")
        }
        else {
            context.packageManager.getLaunchIntentForPackage("com.samsung.android.app.galaxyfinder")
        }
    val pendingIntent = PendingIntent.getActivity(context, 0, launchIntent, PendingIntent.FLAG_IMMUTABLE)
    views.setOnClickPendingIntent(R.id.widget_input, pendingIntent)

    appWidgetManager.updateAppWidget(appWidgetId, views)
}
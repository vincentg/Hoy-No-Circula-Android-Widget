package com.vg.hnc.widget;
import java.util.Calendar;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.Build;
import android.widget.RemoteViews;

import com.vg.hnc.rules.HNCCompute;
import com.vg.hnc.rules.HNCRestriction;

public class HNCWidget extends AppWidgetProvider {

	private static final boolean isAdvanced = Build.VERSION.SDK_INT > 7;
	private HNCCompute c;
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		c = new HNCCompute(Calendar.getInstance());
		RemoteViews updateView;
		updateView = new RemoteViews(context.getPackageName(), R.layout.main);
		
		updateRestriction(updateView, R.id.hnc_hoy, c.getDay());
		updateRestriction(updateView, R.id.hnc_mna, c.getTomorrow());
		updateRestriction(updateView, R.id.hnc_prox, c.getNextSaturday());
		/*
		Intent clickservice = new Intent(context, ClickService.class);
		clickservice.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds);
		
		PendingIntent p = PendingIntent.getService(context, 0, clickservice , 0);
		updateView.setOnClickPendingIntent(R.id.otro, p);	
		*/
		appWidgetManager.updateAppWidget(appWidgetIds, updateView);

	}
	
	private void updateRestriction(RemoteViews rv, int viewId, HNCRestriction restriction) {
		rv.setTextViewText(viewId, restriction.getLastDigits());
		setTextBGColor(rv, viewId  , restriction.getColor());
	}
	
	
	private void setTextBGColor(RemoteViews rv, int viewId, int color) {
		if (isAdvanced) {
			rv.setInt(viewId, "setBackgroundColor", color);
		} else {
			// It is painful in android <2.2 to change a background color.
			rv.setTextColor(viewId, color);
		}
	}

}

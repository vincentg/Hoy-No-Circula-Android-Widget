package com.vg.hnc.widget;
import java.util.Calendar;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.Build;
import android.widget.RemoteViews;

import com.vg.hnc.rules.HNCCompute;
import com.vg.hnc.rules.HNCRestriction;

/**
 * Main Widget Entry point class
 * 
 * @author vincent
 *
 */
public class HNCWidget extends AppWidgetProvider {

	/**
	 * True if Android >= 2.2
	 */
	private static final boolean isAdvanced = Build.VERSION.SDK_INT > 7;
	
	/** 
	 * Update the widget contents periodically
	 * @see android.appwidget.AppWidgetProvider#onUpdate(android.content.Context, android.appwidget.AppWidgetManager, int[])
	 */
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		final HNCCompute c = new HNCCompute(Calendar.getInstance());
		final RemoteViews updateView = 
				new RemoteViews(context.getPackageName(), R.layout.main);
		// Update for Today, Tomorrow, NextSaturday
		updateRestriction(updateView, R.id.hnc_hoy, c.getDay());
		updateRestriction(updateView, R.id.hnc_mna, c.getTomorrow());
		updateRestriction(updateView, R.id.hnc_prox, c.getNextSaturday());
		// Apply
		appWidgetManager.updateAppWidget(appWidgetIds, updateView);

	}
	
	/**
	 * Sets the text and background of the view with the provided restriction data
	 * @param rv Remote View
	 * @param viewId View to update
	 * @param restriction Restriction to be applied
	 */
	private void updateRestriction(RemoteViews rv, int viewId, HNCRestriction restriction) {
		rv.setTextViewText(viewId, restriction.getLastDigits());
		setTextBGColor(rv, viewId  , restriction.getColor());
	}
	
	
	/**
	 * Sets the Background color on Android >= 2.2, elsewise, the text color.
	 * @param rv RemoteView
	 * @param viewId TextViewId to update
	 * @param color Color
	 */
	private void setTextBGColor(RemoteViews rv, int viewId, int color) {
		if (isAdvanced) {
			rv.setInt(viewId, "setBackgroundColor", color);
		} else {
			// It is painful in android <2.2 to change a background color.
			rv.setTextColor(viewId, color);
		}
	}

}

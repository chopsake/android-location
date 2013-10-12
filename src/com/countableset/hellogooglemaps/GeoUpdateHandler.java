package com.countableset.hellogooglemaps;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class GeoUpdateHandler implements LocationListener
{
	MapController mc;

	@Override
	public void onLocationChanged(Location location)
	{
		int lat = (int) (location.getLatitude() * 1E6);
		int lng = (int) (location.getLongitude() * 1E6);
		GeoPoint point = new GeoPoint(lat, lng);
		mc.animateTo(point);
		
//		// Adding the overlay marker (image)
//        OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");
//        List<Overlay> mapOverlays = mapView.getOverlays();
//        Drawable drawable = this.getResources().getDrawable(R.drawable.marker);
//        HelloItemizedOverlay itemizedoverlay = new HelloItemizedOverlay(drawable);
//        itemizedoverlay.addOverlay(overlayitem);
//        mapOverlays.add(itemizedoverlay);
	}

	@Override
	public void onProviderDisabled(String provider)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras)
	{
		// TODO Auto-generated method stub

	}

}

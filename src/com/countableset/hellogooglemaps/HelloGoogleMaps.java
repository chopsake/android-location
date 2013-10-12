package com.countableset.hellogooglemaps;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class HelloGoogleMaps extends MapActivity 
{
	// Overlay items
	private List<Overlay> mapOverlays;
	private Drawable drawable;
	private HelloItemizedOverlay itemizedOverlay;
	private OverlayItem overlayitem;
	
	// Map Items
	private MapController mapController;
	private MapView mapView;
	private LocationManager locationManager;
	private GeoPoint point;
	
	// Menu Items
	private static final int LOCATION_ID = Menu.FIRST;

	public void onCreate(Bundle bundle) 
	{
		super.onCreate(bundle);
		setContentView(R.layout.main); // bind the layout to the activity

		// create a map view
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapController = mapView.getController();
		mapController.setZoom(18); // Zoon 1 is world view
	} // end onCreate()
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // When you press the menu button
        menu.add(0, LOCATION_ID, 0, R.string.get_location);
        return true;
    } // end Menu Button
    
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()) 
        {
            case LOCATION_ID:
                getLocation();
                return true;
        }
        return super.onMenuItemSelected(featureId, item);
    } // end Menu Selection

	@Override
	protected boolean isRouteDisplayed() 
	{
		return false;
	}
	
	protected void getLocation()
	{
		// Get Location
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000,
				100f, new GeoUpdateHandler());
		
		// Overlay Stuff
		mapOverlays = mapView.getOverlays();
		drawable = this.getResources().getDrawable(R.drawable.androidmarker);
		itemizedOverlay = new HelloItemizedOverlay(drawable);
	} // end getLocation()

	public class GeoUpdateHandler implements LocationListener 
	{

		@Override
		public void onLocationChanged(Location location) 
		{
			mapView.getOverlays().clear(); // clears the map from the previous overlay
			int lat = (int) (location.getLatitude() * 1E6);
			int lng = (int) (location.getLongitude() * 1E6);
			point = new GeoPoint(lat, lng);
			overlayitem = new OverlayItem(point, "", "");
			mapController.animateTo(point); //	mapController.setCenter(point);
			itemizedOverlay.addOverlay(overlayitem);
			mapOverlays.add(itemizedOverlay);
		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	} // end GeoUpdateHandler Class
} // end HelloGoogleMaps Class
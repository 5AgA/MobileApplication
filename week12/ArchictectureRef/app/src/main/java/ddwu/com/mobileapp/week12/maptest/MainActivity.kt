package ddwu.com.mobileapp.week12.maptest

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import ddwu.com.mobileapp.week12.maptest.databinding.ActivityMainBinding
import ddwu.com.mobileapp.week12.maptest.ui.MapViewModel
import ddwu.com.mobileapp.week12.maptest.ui.MapViewModelFactory

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivityTag"

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var locationCallback: LocationCallback

//    private lateinit var googleMap: GoogleMap
//    private val mapReadyCallback =

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        val mapFragment: SupportMapFragment =
//        mapFragment.getMapAsync(mapReadyCallback)


        val mapViewModel : MapViewModel by viewModels {
            MapViewModelFactory( (application as MapApplication).mapRepository )
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        locationRequest = LocationRequest.Builder(3000)
            .setMinUpdateIntervalMillis(5000)
            .setPriority(Priority.PRIORITY_BALANCED_POWER_ACCURACY)
            .build()

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                val currentLocation: Location = locationResult.locations[0]
                Log.d(TAG, "위도: ${currentLocation.latitude}, " +
                        "경도: ${currentLocation.longitude}")
            }
        }

        binding.btnStart.setOnClickListener {
            checkPermissions()
            startLocationRequest()
        }

        binding.btnStop.setOnClickListener {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }

        binding.btnGetLast.setOnClickListener {

        }

        binding.btnClear.setOnClickListener {

        }
    }

//    private lateinit var markerOptions: MarkerOptions
//    private var centerMarker: Marker? = null

//    fun addMarker(targetLoc: LatLng) {
//
//    }



//    lateinit var polylineOptions : PolylineOptions  // 선의 옵션, 멤버변수로 지정
//    lateinit var currentLine : Polyline     // 지도에 추가한 선, 멤버변수로 지정

//    fun drawLine(latLng: LatLng) {
//        val newLatLng = LatLng(latLng.latitude, latLng.longitude)
//        val points = currentLine.points // 선을 구성하는 점 집합
//        points.add(newLatLng)   // 점 집합에 새로운 점 추가
//        currentLine.points = points
//        Log.d(TAG, "add line")
//    }


    override fun onPause() {
        super.onPause()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private fun startLocationRequest() {
        fusedLocationClient.requestLocationUpdates(locationRequest,
            locationCallback, Looper.getMainLooper())
    }


    // Permission 확인
    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions() ) { permissions ->
        when {
            permissions.getOrDefault(ACCESS_FINE_LOCATION, false) ->
                Log.d(TAG, "정확한 위치 사용")
            permissions.getOrDefault(ACCESS_COARSE_LOCATION, false) ->
                Log.d(TAG, "근사 위치 사용")
            else ->
                Log.d(TAG, "권한 미승인")
        }
    }


    private fun checkPermissions() {
        if ( checkSelfPermission(ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            && checkSelfPermission(ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
            Log.d(TAG, "필요 권한 있음")
//            startLocationRequest()
        } else {
            locationPermissionRequest.launch(
                arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)
            )
        }
    }

}
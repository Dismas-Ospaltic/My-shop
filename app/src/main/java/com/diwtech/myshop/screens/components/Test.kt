//class MainActivity : ComponentActivity() {
//    @RequiresApi(Build.VERSION_CODES.S)
//    @OptIn(ExperimentalAnimationApi::class)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // Initialize UI first (always safe)
//        setContent {
//            val navController = rememberAnimatedNavController()
//
//            Scaffold { paddingValues ->
//                AppNavHost(navController, Modifier.padding(paddingValues))
//            }
//
//            SideEffect {
//                // Ensure full-screen layout
//                WindowCompat.setDecorFitsSystemWindows(window, false)
//            }
//        }
//
//        // Then handle consent + ads asynchronously
//        ConsentManager.requestConsent(this) {
//            MobileAds.initialize(this) {
//                Log.d("AdsInit", "Mobile Ads SDK initialized.")
//            }
//
//            MobileAds.setRequestConfiguration(
//                RequestConfiguration.Builder()
//                    .setTestDeviceIds(listOf("5EEDD4839E298F38292B35ECD2035259"))
//                    .build()
//            )
//        }
//    }
//}

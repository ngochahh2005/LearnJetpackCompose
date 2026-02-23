package com.frank.jetpackcomposeyoutube

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.frank.jetpackcomposeyoutube.ui.catalog.category.CategoryScreen
import com.frank.jetpackcomposeyoutube.ui.catalog.product.ProductDetailScreen
import com.frank.jetpackcomposeyoutube.ui.checkout.CheckoutScreen
import com.frank.jetpackcomposeyoutube.ui.checkout.CheckoutSuccessScreen
import com.frank.jetpackcomposeyoutube.ui.customer.AddressDetailScreen
import com.frank.jetpackcomposeyoutube.ui.customer.CustomerInfoScreen
import com.frank.jetpackcomposeyoutube.ui.customer.MyAccountScreen
import com.frank.jetpackcomposeyoutube.ui.home.HomeScreen
import com.frank.jetpackcomposeyoutube.ui.theme.JetpackComposeYoutubeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            MainApp()
        }
    }
}


@Composable
fun MainApp() {
    val navController = rememberNavController()
    JetpackComposeYoutubeTheme {
        // navHost dùng để chứa tất cả các Composable khác
        // navController giúp navigate (chuyển đổi) giữa các màn hình khác nhau
        // startDestination là màn hình đầu tiên khi mở app
        NavHost(navController = navController, startDestination = "home") {
            // định nghĩa 1 route muốn mở (home)
            // với route đó thì muốn mở ra màn hình gì
            composable("home") {
                HomeScreen(
                    openCategoryAction = {
                        navController.navigate("category")
                    },
                    openMyAccountScreen = {
                        navController.navigate("myAccount")
                    },
                    editCustomerInfo = {
                        navController.navigate("customer")
                    }
                )
            }

            // route: category
            composable("category") {
                CategoryScreen(openProductDetail = {
                    productId ->
                    navController.navigate("productDetail/$productId")  // gửi productId sang cho productDetail
                })
            }

            // route: product detail
            composable(
                route = "productDetail/{productId}",
                arguments = listOf(     // route nhận vào 1 mảng các tham sô
                    navArgument(name = "productId") {   // trong mảng này thì có 1 phần từ có tên là productId
                        type = NavType.StringType   // kiểu của productId là String
                    }
                )
            ){
                backStackEntry ->
                val productId = backStackEntry.arguments?.getString("productId")
                requireNotNull(productId)
                ProductDetailScreen(
                    productId = productId,
                    checkout = {
                        cartId, customerId ->
                        navController.navigate("checkout/$cartId/$customerId")
                    },
                    backAction = {
                        navController.popBackStack()
                    }
                )
            }

            composable(
                route = "checkout/{cartId}/{customerId}",
                arguments = listOf(
                    navArgument(name = "cartId") {
                        type = NavType.StringType
                    },
                    navArgument(name = "customerId") {
                        type = NavType.StringType
                    }
                )
            ) {
                backStackEntry ->
                val cartId = backStackEntry.arguments?.getString("cartId")
                val customerId = backStackEntry.arguments?.getString("customerId")
                requireNotNull(cartId)
                requireNotNull(customerId)
                CheckoutScreen(cartId = cartId, customerId = customerId) {
                    navController.navigate("checkoutSuccess")
                }
            }

            composable(route = "checkoutSuccess") {
                CheckoutSuccessScreen(
                    goHomeAction = {
                        // back thẳng về trang home và bỏ qua các trang khác (nếu có)
                        navController.popBackStack("home", inclusive = false, saveState = true)
                    },
                    viewOrderDetailAction = {}
                )
            }

            // nested navigation - nhóm các features của customer vào đây
            // có thể gọi đến route nhóm (customer) hoặc route cụ thể (customerInfo)
            navigation(route = "customer", startDestination = "myAccount") {
                composable(route = "myAccount") {
                    MyAccountScreen(navController = navController, openAddressScreen = {
                            addressId ->
                        val route = if (addressId == null) "addressDetail" else "addressDetail?addressId=$addressId"
                        navController.navigate(route)
                    })
                }

                composable(route = "customerInfo") {
                    CustomerInfoScreen(onClickBack = {
                        navController.popBackStack()
                    })
                }

                composable(
                    route = "addressDetail?addressId={addressId}",  // nullable
                    arguments = listOf(
                        navArgument(name = "addressId") {
                            type = NavType.StringType
                            nullable = true
                        }
                    )
                ) {
                        backStackEntry ->
                    val addressId = backStackEntry.arguments?.getString("addressId")
                    AddressDetailScreen(addressId = addressId, saveAddressAndBack = {
                            addressId ->
                        navController.previousBackStackEntry?.savedStateHandle?.set("new_address_id", addressId)
                        navController.popBackStack()
                    })
                }
            }


        }
    }
}

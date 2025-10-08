package com.example.examplekursproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.examplekursproject.interfaces.Home
import com.example.examplekursproject.ui.theme.ExampleKursProjectTheme
import com.example.examplekursproject.viewmodels.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            ExampleKursProjectTheme {
                Main(viewModel)
            }
        }
    }
}

@Composable
fun Main(viewModel: HomeViewModel){
    val navController= rememberNavController()
    Column(Modifier.padding(top = 24.dp, bottom = 8.dp,)){
        NavHost(navController, startDestination = NavRoutes.Home.route, modifier = Modifier.weight(1f)){
            composable(NavRoutes.Home.route){ Home(viewModel) }
            composable(NavRoutes.Contacts.route){Contacts()}
            composable(NavRoutes.About.route){About()}
        }
        BottomNavigationBar(navController=navController)
    }
}
@Composable
fun BottomNavigationBar(navController: NavController){
    NavigationBar {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute=backStackEntry?.destination?.route
        NavBarItems.BarItems.forEach { navItem->
            NavigationBarItem(
                selected = currentRoute==navItem.route,
                onClick = {
                    navController.navigate(navItem.route){
                        popUpTo(navController.graph.findStartDestination().id){saveState=true}
                        launchSingleTop=true
                        restoreState=true
                    }
                } ,
                icon = { Icon(imageVector = navItem.image,
                    contentDescription = navItem.title) },
                label = {
                    Text(text = navItem.title)
                }
            )
        }
    }
}
object NavBarItems{
    val BarItems=listOf(
        BarItem(title = "Home", image = Icons.Filled.Home, route = "home"),
        BarItem(title = "Contacts", image = Icons.Filled.Face, route = "contacts"),
        BarItem(title = "About", image = Icons.Filled.Info, route = "about"),
    )
}

data class BarItem(
    val title: String,
    val image: ImageVector,
    val route: String
)

@Composable
fun Contacts(){
    Text("Contact page", fontSize = 30.sp)
}
@Composable
fun About(){
    Text("About page", fontSize = 30.sp)
}
sealed class NavRoutes(val route: String){
    object Home: NavRoutes("home")
    object Contacts: NavRoutes("contacts")
    object About: NavRoutes("about")
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExampleKursProjectTheme {
    //    Main()
    }
}

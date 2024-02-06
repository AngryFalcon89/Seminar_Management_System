package com.example.seminarmanagementsystem.presentation.bookNavigator

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.seminarmanagementsystem.R
import com.example.seminarmanagementsystem.data.model.bookModel.Book
import com.example.seminarmanagementsystem.presentation.bookData.IssueScreen.IssueScreen
import com.example.seminarmanagementsystem.presentation.bookData.IssueScreen.IssueViewModel
import com.example.seminarmanagementsystem.presentation.bookData.addScreen.AddScreen
import com.example.seminarmanagementsystem.presentation.bookData.addScreen.AddScreenViewModel
import com.example.seminarmanagementsystem.presentation.bookData.detailsScreen.DetailsScreen
import com.example.seminarmanagementsystem.presentation.bookData.detailsScreen.DetailsViewModel
import com.example.seminarmanagementsystem.presentation.bookData.homeScreen.HomeScreen
import com.example.seminarmanagementsystem.presentation.bookData.homeScreen.HomeViewModel
import com.example.seminarmanagementsystem.presentation.bookData.issueTheBook.IssueTheBookScreen
import com.example.seminarmanagementsystem.presentation.bookData.issueTheBook.IssueTheBookViewModel
import com.example.seminarmanagementsystem.presentation.bookData.searchScreen.SearchScreen
import com.example.seminarmanagementsystem.presentation.bookData.searchScreen.SearchViewModel
import com.example.seminarmanagementsystem.presentation.bookData.updateBook.UpdateScreen
import com.example.seminarmanagementsystem.presentation.bookData.updateBook.UpdateScreenViewModel
import com.example.seminarmanagementsystem.presentation.bookNavigator.component.BookBottomNavigation
import com.example.seminarmanagementsystem.presentation.bookNavigator.component.BottomNavigationItem
import com.example.seminarmanagementsystem.presentation.navGraph.Route
import com.example.seminarmanagementsystem.presentation.userAuth.signup.SignUpViewModel
import com.example.seminarmanagementsystem.presentation.userAuth.signup.SignupScreen
import com.example.seminarmanagementsystem.utils.Constants.TAG


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_book, text = "Issue"),
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.IssueScreen.route -> 2
        else -> 0
    }

    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.IssueScreen.route
    }

    val isFloatingIconVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route
    }
    var searchQuery by remember { mutableStateOf(TextFieldValue()) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                BookBottomNavigation(
                    items = bottomNavigationItems,
                    selectedItem = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = Route.HomeScreen.route
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = Route.SearchScreen.route
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = Route.IssueScreen.route
                            )
                        }
                    }
                )
            }
        },
        floatingActionButton = {
            if (isFloatingIconVisible) {
                FloatingActionButton(
                    onClick = {
                        navigateToTab(
                            navController = navController,
                            route = Route.AddScreen.route
                        )
                    },
                    shape = CircleShape,
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(8.dp),
                    contentColor = MaterialTheme.colorScheme.primary,
                    content = {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = null
                        )
                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) { backStackEntry ->
                val viewModel: HomeViewModel = hiltViewModel()
                val books = viewModel.book.collectAsLazyPagingItems()
                HomeScreen(
                    navController = navController,
                    bookList = books,
                    navigateToDetails = { book ->
                        navigateToDetails(
                            navController = navController,
                            book = book
                        )
                    }
                )
            }

            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = { book ->
                        navigateToDetails(
                            navController = navController,
                            book = book
                        )
                    }
                )
            }

            composable(route = Route.IssueScreen.route) {
                val viewModel: IssueViewModel = hiltViewModel()
                val issueList = viewModel.issueBook.collectAsLazyPagingItems()
                OnBackClickStateSaver(navController = navController)
                IssueScreen(
                    issuedBookList = issueList,
                    navigateToIssueDetails = { book ->
                        navigateToDetails(
                            navController = navController,
                            book = book
                        )
                    }
                )
            }

            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()

                navController.previousBackStackEntry?.savedStateHandle?.get<Book?>("book")
                    ?.let { book ->
                        navController.currentBackStackEntry?.savedStateHandle?.set("book", book)
                        DetailsScreen(
                            book = book,
                            onDeleteClicked = {
                                viewModel.deleteBook(book)
                            },
                            navigateUp = { navController.navigateUp() },
                            onUpdateClicked = {
                                navController.navigate(Route.UpdateScreen.route)
                            },
                            onReturnClicked = {
                                viewModel.returnBook(book)
                            },
                            onIssueClicked = {
                                navController.navigate(Route.IssueTheBookScreen.route)
                            }
                        )
                    }
            }

            composable(route = Route.AddScreen.route) {
                val viewModel: AddScreenViewModel = hiltViewModel()
                AddScreen(
                    viewModel = viewModel,
                    onAddClicked = {
                        viewModel.addBook()
                    })
            }

            composable(route = Route.UpdateScreen.route) {
                val viewModel: UpdateScreenViewModel = hiltViewModel()

                navController.previousBackStackEntry?.savedStateHandle?.get<Book?>("book")
                    ?.let { book ->
                        try {
                            viewModel.setSelectedBook(book)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        UpdateScreen(
                            viewModel = viewModel,
                            book = book
                        )
                    }
            }

            composable(route = Route.SignupScreen.route) {
                val viewModel: SignUpViewModel = hiltViewModel()
                SignupScreen(
                    viewModel = viewModel,
                    onRegisterClicked = {
                        viewModel.generateOtp()
                    },
                    onVerifyOtpClicked = {
                        viewModel.verifyOtp()
                    }
                )
            }

            composable(route = Route.IssueTheBookScreen.route) {
                val viewModel: IssueTheBookViewModel = hiltViewModel()
                navController.previousBackStackEntry?.savedStateHandle?.get<Book?>("book")
                    ?.let { book ->
                        IssueTheBookScreen(
                            viewModel = viewModel,
                            onIssueClicked = {
                                viewModel.issueTheBook(book._id)
                            }
                        )
                    }
            }

        }
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Route.HomeScreen.route
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetails(navController: NavController, book: Book) {
    Log.d(TAG, "navigateToDetails: $book")
    try {
        navController.currentBackStackEntry?.savedStateHandle?.set("book", book)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}

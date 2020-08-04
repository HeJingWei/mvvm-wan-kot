package com.example.mvvm_wan_kot.common.di

import com.example.mvvm_wan_kot.ui.collect.CollectRepository
import com.example.mvvm_wan_kot.ui.collect.CollectViewModel
import com.example.mvvm_wan_kot.ui.common.CollectOperateRepository
import com.example.mvvm_wan_kot.ui.common.webview.WebViewRepository
import com.example.mvvm_wan_kot.ui.common.webview.WebViewViewModel
import com.example.mvvm_wan_kot.ui.history.HistoryRepository
import com.example.mvvm_wan_kot.ui.history.HistoryViewModel
import com.example.mvvm_wan_kot.ui.integral.IntegralRepository
import com.example.mvvm_wan_kot.ui.integral.IntegralViewModel
import com.example.mvvm_wan_kot.ui.login.LoginRepository
import com.example.mvvm_wan_kot.ui.login.LoginViewModel
import com.example.mvvm_wan_kot.ui.login.register.RegisterRepository
import com.example.mvvm_wan_kot.ui.login.register.RegisterViewModel
import com.example.mvvm_wan_kot.ui.main.home.article.ArticleRepository
import com.example.mvvm_wan_kot.ui.main.home.article.ArticleViewModel
import com.example.mvvm_wan_kot.ui.main.home.popular.PopularRepository
import com.example.mvvm_wan_kot.ui.main.home.popular.PopularViewModel
import com.example.mvvm_wan_kot.ui.main.home.project.ProjectRepository
import com.example.mvvm_wan_kot.ui.main.home.project.ProjectViewModel
import com.example.mvvm_wan_kot.ui.main.home.square.SquareRepository
import com.example.mvvm_wan_kot.ui.main.home.square.SquareViewModel
import com.example.mvvm_wan_kot.ui.main.home.wxproject.WxProjectRepository
import com.example.mvvm_wan_kot.ui.main.home.wxproject.WxProjectViewModel
import com.example.mvvm_wan_kot.ui.main.mine.MineRepository
import com.example.mvvm_wan_kot.ui.main.mine.MineViewModel
import com.example.mvvm_wan_kot.ui.main.navigation.NavigationRepository
import com.example.mvvm_wan_kot.ui.main.navigation.NavigationViewModel
import com.example.mvvm_wan_kot.ui.main.system.SystemRepository
import com.example.mvvm_wan_kot.ui.main.system.SystemViewModel
import com.example.mvvm_wan_kot.ui.search.SearchRepository
import com.example.mvvm_wan_kot.ui.search.SearchViewModel
import com.example.mvvm_wan_kot.ui.setting.SettingRepository
import com.example.mvvm_wan_kot.ui.setting.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MineViewModel(get())
    }
    viewModel {
        NavigationViewModel(get())
    }
    viewModel {
        SystemViewModel(get(),get())
    }
    viewModel {
        PopularViewModel(get(),get())
    }
    viewModel {
        SquareViewModel(get(),get())
    }
    viewModel {
        LoginViewModel(get())
    }
    viewModel {
        RegisterViewModel(get())
    }
    viewModel {
        SettingViewModel(get())
    }
    viewModel {
        IntegralViewModel(get())
    }
    viewModel {
        CollectViewModel(get(),get())
    }
    viewModel {
        WxProjectViewModel(get(),get())
    }
    viewModel {
        ProjectViewModel(get())
    }
    viewModel {
        ArticleViewModel(get(),get())
    }
    viewModel {
        SearchViewModel(get())
    }
    viewModel {
        HistoryViewModel(get())
    }
    viewModel {
        WebViewViewModel(get())
    }
}

val repositoryModule = module {
    single {
        MineRepository()
    }
    single {
        NavigationRepository()
    }
    single {
        SystemRepository()
    }
    single {
        PopularRepository()
    }
    single {
        SquareRepository()
    }
    single {
        LoginRepository()
    }
    single {
        RegisterRepository()
    }
    single {
        SettingRepository()
    }
    single {
        IntegralRepository()
    }
    single {
        CollectOperateRepository()
    }
    single {
        CollectRepository()
    }
    single {
        WxProjectRepository()
    }
    single {
        ProjectRepository()
    }
    single {
        ArticleRepository()
    }
    single {
        SearchRepository()
    }
    single {
        HistoryRepository()
    }
    single {
        WebViewRepository()
    }
}

val appModule = listOf(viewModelModule, repositoryModule)
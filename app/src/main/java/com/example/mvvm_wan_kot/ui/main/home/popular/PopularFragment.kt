package com.example.mvvm_wan_kot.ui.main.home.popular

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.base.BaseVMFragment
import com.example.mvvm_wan_kot.common.custom.GlideImageLoader
import com.example.mvvm_wan_kot.common.ext.showToast
import com.example.mvvm_wan_kot.common.ext.toIntPx
import com.example.mvvm_wan_kot.model.bean.Banner
import com.example.mvvm_wan_kot.ui.main.adapter.HomePopularAdapter
import com.youth.banner.BannerConfig
import org.koin.androidx.viewmodel.ext.android.getViewModel

class PopularFragment : BaseVMFragment<PopularViewModel>() {
    override fun getLayoutResId(): Int = R.layout.fragment_popular

    override fun initVM(): PopularViewModel = getViewModel()
    private val homePopularAdapter by lazy { HomePopularAdapter() }
    private val bannerImages = mutableListOf<String>()
    private val bannerTitles = mutableListOf<String>()
    private val bannerUrls = mutableListOf<String>()
    private val banner by lazy { com.youth.banner.Banner(activity) }

    override fun initView() {
        mBinding.run {
            setVariable(BR.vm, mViewModel)
            setVariable(BR.adapter, homePopularAdapter)
        }
        homePopularAdapter.run {
            setOnItemClickListener { _, _, position ->
                val link = homePopularAdapter.data[position].link
                this@PopularFragment.activity?.showToast(link)
            }
            loadMoreModule.isEnableLoadMore = false
            addHeaderView(banner)
            setOnItemChildClickListener { _, view, position ->
                if (view.id == R.id.collect) {
                    mViewModel.collect(data[position].collect, data[position].id)
                    data[position].collect = !data[position].collect
                    notifyItemChanged(position)
                }
            }
        }
        initBanner()
    }

    override fun initData() {
        mViewModel.refreshPopularList(true)
    }

    override fun startObserve() {
        mViewModel.uiState.observe(this@PopularFragment, Observer {
            if (it.showDialogLoading) showProgressDialog() else hideProgressDialog()
            it.banner?.let { list ->
                setBanner(list)
            }
            it.article?.let { list ->
                homePopularAdapter.run {
                    if (it.isRefresh) {
                        setList(list)
                    }
                }
            }
            it.collectToast?.let { collectToast ->
                activity?.showToast(collectToast)
            }
        })
    }

    private fun initBanner() {
        banner.run {
            layoutParams =
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200.toIntPx())
            setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
            setImageLoader(GlideImageLoader())
            setOnBannerListener { position ->
                run {
                    val url = bannerUrls[position]
                    this@PopularFragment.activity?.showToast(url)
                }
            }
        }
    }

    private fun setBanner(bannerList: List<Banner>) {
        for (banner in bannerList) {
            bannerImages.add(banner.imagePath)
            bannerTitles.add(banner.title)
            bannerUrls.add(banner.url)
        }
        banner.setImages(bannerImages)
            .setBannerTitles(bannerTitles)
            .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
            .setDelayTime(3000)
        banner.start()
    }
}
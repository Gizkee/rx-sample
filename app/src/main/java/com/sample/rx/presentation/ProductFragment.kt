package com.sample.rx.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sample.rx.domain.model.Product
import com.sample.rx.presentation.ShowcaseViewState.Content
import com.sample.rx.presentation.ShowcaseViewState.ShowcaseContainerState.ProductsState
import kotlinx.android.synthetic.main.fragment_showcase_products.*
import javax.inject.Inject

class ProductFragment(
        override val layoutRes: Int = R.layout.fragment_showcase_products
) : BaseFragment(), ProductsAdapter.OnProductClickListener {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private lateinit var showcaseViewModel: ShowcaseViewModel

    private lateinit var productsAdapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presentationComponent.inject(this)

        showcaseViewModel = ViewModelProvider(this.requireParentFragment(), viewModelFactory)
                .get(ShowcaseViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productsAdapter = ProductsAdapter(this)
        productsRecyclerView.layoutManager = GridLayoutManager(context, 3)
        productsRecyclerView.adapter = productsAdapter
    }

    override fun onProductClick(product: Product) {
        showcaseViewModel.onProductClick(product)
    }

    override fun onStart() {
        super.onStart()
        showcaseViewModel.viewState.observe(this, Observer {
            val viewState = it ?: return@Observer

            if (viewState is Content && viewState.containerState is ProductsState) {
                categoryName.text = viewState.currentCategory?.name
                productsAdapter.products = (viewState.containerState as ProductsState).products
                productsAdapter.notifyDataSetChanged()
            }
        })
    }
}